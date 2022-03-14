package com.mparee.smsapp

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {
   private  var txtMobile: EditText? = null
   private  var txtMessage: EditText? = null
    private var btnSms: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtMobile = findViewById<View>(R.id.mblTxt) as EditText
        txtMessage = findViewById<View>(R.id.msgTxt) as EditText
        btnSms = findViewById<View>(R.id.btnSend) as Button
       // ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS,) != PackageManager.PERMISSION_GRANTED
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),200)
        }
        btnSms!!.setOnClickListener{
            try{
                val phonenumber = txtMobile?.text.toString()
                val sms = txtMessage?.text.toString()
                var smsManager= SmsManager.getDefault() as SmsManager
                smsManager.sendTextMessage(phonenumber, null, sms, null,null)
                Toast.makeText(this@MainActivity, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
            }
            catch (e: Exception){
                Toast.makeText(this@MainActivity, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

