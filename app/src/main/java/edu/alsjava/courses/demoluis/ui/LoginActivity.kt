package edu.alsjava.courses.demoluis.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.alsjava.courses.demoluis.R
import edu.alsjava.courses.demoluis.utils.SharedPreferencesTool
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreferencesTool = SharedPreferencesTool(this)
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPaassword.text.toString()
            if (username.equals("text") && password.equals("123456")) {
                sharedPreferencesTool.session = "smdasmdokasodkoaskd"
                openApp()
            }
        }
    }

    fun openApp() {
        val intent = Intent(this, AppActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}