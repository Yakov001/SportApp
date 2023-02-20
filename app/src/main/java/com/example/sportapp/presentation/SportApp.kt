package com.example.sportapp.presentation

import android.app.Application
import com.onesignal.OneSignal

class SportApp : Application() {

    companion object {
        private const val ONESIGNAL_APP_ID = "935dea5c-76a6-4d3c-a28f-e70c49940bff"
    }

    override fun onCreate() {
        super.onCreate()

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications()
    }
}