package com.synjones.androidwebviewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.synjones.androidwebviewproject.event.LoginEvent
import kotlinx.android.synthetic.main.activity_login.*
import org.greenrobot.eventbus.EventBus

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener {
            EventBus.getDefault().post(LoginEvent("test name."))
            finish()
        }
    }
}