package edu.alsjava.courses.demoluis

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import edu.alsjava.courses.demoluis.ui.IntroActivity
import edu.alsjava.courses.demoluis.ui.LoginActivity
import edu.alsjava.courses.demoluis.ui.PrivacyActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postAtTime(Runnable {
            // Abro la actividad correcta
            // Compruebo que la app se abra por primera vez.
            // Compruebo que me aceptaron el privacy
            // Compruebo si voy al login o la app, es decir, si guardo session
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

    fun openLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    fun myFunction() {
        val texto = "dgodkogfkogkdofkgd"
        var cambioDato: String? = null
    }

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun voidFuntion() {
        println("Hola soy void")
    }
}