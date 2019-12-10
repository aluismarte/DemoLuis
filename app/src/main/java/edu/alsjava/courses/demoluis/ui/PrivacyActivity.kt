package edu.alsjava.courses.demoluis.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import edu.alsjava.courses.demoluis.R
import edu.alsjava.courses.demoluis.utils.SharedPreferencesTool
import kotlinx.android.synthetic.main.activity_privacy.*
import java.io.BufferedReader
import java.io.InputStream

class PrivacyActivity : AppCompatActivity() {

    private val contactPermissionRequest = 521

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        tvPrivacy.text = loadPrivacy(resources.openRawResource(R.raw.privacy))
        switchAccept.setOnCheckedChangeListener { _, isChecked ->
            btnOK.isEnabled = isChecked
        }
        btnOK.setOnClickListener {
            openLogin()
        }
        btnReadContacts.setOnClickListener {
            readAllContacts()
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), contactPermissionRequest)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), contactPermissionRequest)
            }
        } else {
            btnReadContacts.isEnabled = true
        }
        println("Me ejecuto justo despues de la linea, pero me veo primero en la consola antes que la solicitud del permiso en pantalla")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            contactPermissionRequest -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Wiiiiiiiiiii me dieron el permiso
                    btnReadContacts.isEnabled = true
                    println("No lo estoy!, estto es para estudiar -_-")
                } else {
                    btnReadContacts.isEnabled = false
                    // Me negaron el permiso
                    // -_- usuario que quiere proteger su privacidad???
                }
                return
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    private fun readAllContacts() {
        // No me ejecutes sino es necesario, pues debo tener los permisos
        val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        while (phones!!.moveToNext()) {
            val name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            println(String.format("Contact: %s -- Phone: %s", name, phoneNumber))
        }
        phones.close()
    }

    private fun openLogin() {
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
        reader.use {
            content = reader.readText()
        }
        return content
    }
}