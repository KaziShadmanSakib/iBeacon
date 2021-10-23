package com.example.ibeacon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val pairDeviceBtn = findViewById<Button>(R.id.pairDeviceBtn)
        pairDeviceBtn.setOnClickListener(){

            val intent = Intent(this, PairANewDeviceActivity::class.java)
            startActivity(intent)

        }

    }
}