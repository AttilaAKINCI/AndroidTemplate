package com.akinci.androidtemplate.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 *  ConnectionManager provides info regarding network connectivity for valid network types
 *   - Wifi
 *   - Cellular
 * **/
@Singleton
class ConnectionManager @Inject constructor(
    @ApplicationContext val context: Context,
) {
    // ConnectionManager updates connectionStatusFlow on IO dispatcher
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    // connectionStatusFlow will replay last recent network status for each subscriber
    private val _connectionStatusFlow = MutableSharedFlow<ConnectionStatus>(replay = 1)
    private val connectionStatusFlow = _connectionStatusFlow.asSharedFlow()

    // native connectivityManager bind
    private val connectivityManager by lazy {
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
    }

    // subscribe option for getting continuous updates on network status changes
    fun subscribe() = connectionStatusFlow
    private fun update(status: ConnectionStatus) {
        coroutineScope.launch { _connectionStatusFlow.emit(status) }
    }

    // to get last recent connection status at that moment
    suspend fun getStatus() = _connectionStatusFlow.firstOrNull()

    init {
        initialize()
    }

    private fun initialize() {
        // set initial status on initialization
        update(getInitialStatus())

        // register to network status changes
        val request = NetworkRequest.Builder().apply {
            validTransportTypes.onEach { addTransportType(it) }
        }

        // register devices connectivity manager
        connectivityManager.registerNetworkCallback(
            request.build(),
            object : ConnectivityManager.NetworkCallback() {
                // network is available for use
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    update(ConnectionStatus.Connected)
                }

                // lost network connection
                override fun onLost(network: Network) {
                    super.onLost(network)
                    update(ConnectionStatus.Disconnected)
                }
            }
        )
    }

    private fun getInitialStatus(): ConnectionStatus {
        val capabilities = with(connectivityManager) { getNetworkCapabilities(activeNetwork) }
            ?: return ConnectionStatus.Unknown

        return if (validTransportTypes.any { capabilities.hasTransport(it) }) {
            ConnectionStatus.Connected
        } else {
            ConnectionStatus.Disconnected
        }
    }

    // For android apps general, they require network connection via Wifi or Cellular.
    companion object {
        val validTransportTypes = listOf(
            NetworkCapabilities.TRANSPORT_WIFI,
            NetworkCapabilities.TRANSPORT_CELLULAR
        )
    }
}
