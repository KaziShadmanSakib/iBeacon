package com.example.ibeacon

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //bluetooth adapter
    lateinit var bluetoothAdapter:BluetoothAdapter

    fun checkIfBluetoothIsAvailableOrNot(){

        val bluetoothStatusTv = findViewById<TextView>(R.id.bluetoothStatusTv)

        //init bluetooth adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()


        //check if bluetooth is available or not
        if(bluetoothAdapter == null){
            bluetoothStatusTv.text = "Bluetooth is not available"
        }

        else{
            bluetoothStatusTv.text = "Bluetooth is available"
        }
    }

    fun pairANewDeviceWindow(){

        val pairDeviceBtn = findViewById<Button>(R.id.pairDeviceBtn)
        //pair a new device button window
        pairDeviceBtn.setOnClickListener(){
            val intent = Intent(this, PairANewDeviceActivity::class.java)
            startActivity(intent)
        }
    }

    fun setImageAsBluetoothStatus(){

        //set image according to bluetooth status(on/off)
        val bluetoothIv = findViewById<ImageView>(R.id.bluetoothIv)
        if(bluetoothAdapter.isEnabled){
            //bluetooth is on
            bluetoothIv.setImageResource(R.drawable.ic_bluetooth_on)
        }

        else{
            //bluetooth is off
            bluetoothIv.setImageResource(R.drawable.ic_bluetooth_off)
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pair a new device
        pairANewDeviceWindow()

        //check if bluetooth is available or not
        checkIfBluetoothIsAvailableOrNot()

        //set image according to bluetooth status(on/off)
        setImageAsBluetoothStatus()

    }
}