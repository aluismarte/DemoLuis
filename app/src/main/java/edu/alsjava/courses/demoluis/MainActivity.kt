package edu.alsjava.courses.demoluis

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import edu.alsjava.courses.demoluis.ui.AppActivity
import edu.alsjava.courses.demoluis.ui.IntroActivity
import edu.alsjava.courses.demoluis.ui.LoginActivity
import edu.alsjava.courses.demoluis.ui.PrivacyActivity
import edu.alsjava.courses.demoluis.utils.SharedPreferencesTool

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferencesTool = SharedPreferencesTool(this)
        Handler().postAtTime(Runnable {
            if (sharedPreferencesTool.showIntro()) {
                openIntro()
            } else if (sharedPreferencesTool.showPolicy()) {
                openPrivacy()
            } else if (sharedPreferencesTool.session.equals("")) {
                openLogin()
            } else {
                openApp()
            }
        }, 1200)
    }

    fun openIntro() {
        val intent = Intent(this, IntroActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    fun openPrivacy() {
        val intent = Intent(this, PrivacyActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    fun openApp() {
        val intent = Intent(this, AppActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    fun openLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}