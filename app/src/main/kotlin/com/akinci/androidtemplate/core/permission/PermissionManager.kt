package com.akinci.androidtemplate.core.permission

import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PermissionManager @Inject constructor(
    @ApplicationContext val context: Context,
) {
    fun isGranted(permission: Permission) =
        ContextCompat.checkSelfPermission(context, permission.value) == PERMISSION_GRANTED

    fun isCalendarPermissionGranted(): Boolean {
        return isGranted(Permission.CalendarRead) && isGranted(Permission.CalenderWrite)
    }
}