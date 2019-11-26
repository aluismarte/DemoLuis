package edu.alsjava.courses.demoluis.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.alsjava.courses.demoluis.R
import edu.alsjava.courses.demoluis.utils.SharedPreferencesTool
import kotlinx.android.synthetic.main.activity_privacy.*
import java.io.BufferedReader
import java.io.InputStream

class PrivacyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        tvPrivacy.setText(loadPrivacy(resources.openRawResource(R.raw.privacy)))
        switchAccept.setOnCheckedChangeListener { buttonView, isChecked ->
                btnOK.isEnabled  = isChecked
        }
        btnOK.setOnClickListener {
            openLogin()
        }
    }

    fun openLogin() {
        val sharedPreferencesTool = SharedPreferencesTool(this)
        sharedPreferencesTool.showPolicy(false)
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun loadPrivacy(inputStream: InputStream): String {
        val reader = BufferedReader(inputStream.reader())
        var content = ""
        try {
            content = reader.readText()
        } finally {
            reader.close()
        }
        return content;
    }
}