package com.akinci.androidtemplate.ui.features.permission.multiple

import com.akinci.androidtemplate.core.mvi.MviViewModel
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.Action
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.Effect
import com.akinci.androidtemplate.ui.features.permission.multiple.MultiplePermissionRequestViewContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MultiplePermissionRequestViewModel @Inject constructor(

) : MviViewModel<State, Action, Effect>(State()) {

    // All UI actions will be passed to VM first to be logged (analytics proposes) or further
    // business logic requirements
    override fun onAction(action: Action) {
        when (action) {
            Action.OnRequestPermissionButtonClick -> {}
            Action.OnNavigateBackButtonClick -> sendEffect(Effect.NavigateBack)
            Action.OnOpenAppSettingsButtonClick -> {}
        }
    }


}
