package com.example.ibeacon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PairANewDeviceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pair_anew_device)

        val actionBar = supportActionBar

        actionBar!!.title = "Pair A New Device"
        actionBar.setDisplayHomeAsUpEnabled(true)

    }
}