package com.project.assignmentdiksha

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


class MainApplication : Application(), LifecycleObserver {

    companion object {
        lateinit var instance: MainApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result



        })
    }



}