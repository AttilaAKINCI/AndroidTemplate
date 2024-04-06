package com.akinci.androidtemplate.core.permission

import android.Manifest

/**
 *  Permission holds used permission by the application
 * **/
enum class Permission(val value: String) {
    CalendarRead(Manifest.permission.READ_CALENDAR),
    CalenderWrite(Manifest.permission.WRITE_CALENDAR),
    Camera(Manifest.permission.CAMERA),
}
