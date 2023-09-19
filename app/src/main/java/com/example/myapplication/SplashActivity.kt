package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplication.prefence.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        installSplashScreen().apply {
            setKeepOnScreenCondition { true }
        }

        if (sessionManager.getLogin() == null){
            Intent(this,LoginActivity::class.java).apply {
                startActivity(this)
            }
        }else {
            Intent(this,MainActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}