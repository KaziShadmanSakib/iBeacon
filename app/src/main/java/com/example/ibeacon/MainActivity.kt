package com.example.ibeacon

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //bluetooth adapter
    lateinit var bluetoothAdapter:BluetoothAdapter

    private val REQUEST_CODE_ENABLE_BT:Int = 1
    private val REQUEST_CODE_DISCOVERABLE_BT:Int = 2


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

    fun turnOnBluetooth(){

        //turn on bluetooth
        val turnOnBtn = findViewById<Button>(R.id.turnOnBtn)

        turnOnBtn.setOnClickListener(){
            if(bluetoothAdapter.isEnabled){
                //already enabled
                Toast.makeText(this, "Already On",Toast.LENGTH_LONG).show()
            }
            else{
                //turn on bluetooth
                val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(intent, REQUEST_CODE_ENABLE_BT)
            }
        }

    }

    fun turnOffBluetooth(){

        val bluetoothIv = findViewById<ImageView>(R.id.bluetoothIv)
        //turn off bluetooth
        val turnOffBtn = findViewById<Button>(R.id.turnOffBtn)

        turnOffBtn.setOnClickListener(){
            if(!bluetoothAdapter.isEnabled){
                //already disabled
                Toast.makeText(this, "Already Off",Toast.LENGTH_LONG).show()
            }
            else{
                //turn off bluetooth
                bluetoothAdapter.disable()
                bluetoothIv.setImageResource(R.drawable.ic_bluetooth_off)
                Toast.makeText(this, "Bluetooth turned off",Toast.LENGTH_LONG).show()

            }
        }

    }

    fun discovarable(){

        //Make Bluetooth Visible/Discoverable for other devices
        val visibleBtn = findViewById<Button>(R.id.visibleBtn)

        visibleBtn.setOnClickListener(){

            if(!bluetoothAdapter.isDiscovering){
                Toast.makeText(this, "Making your device discoverable",Toast.LENGTH_LONG).show()
                val intent = Intent(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE))
                startActivityForResult(intent, REQUEST_CODE_DISCOVERABLE_BT)
            }

        }

    }

    fun pairedDevices(){

        //get list of paired devices
        val pairedBtn = findViewById<Button>(R.id.pairedBtn)
        val pairedTv = findViewById<TextView>(R.id.pairedTv)

        pairedBtn.setOnClickListener(){
            if(bluetoothAdapter.isEnabled){

                pairedTv.text = "Paired Devices"

                //get list of paired devices
                val devices = bluetoothAdapter.bondedDevices

                for(device in devices){
                    val deviceName = device.name
                    val deviceAddress = device
                    pairedTv.append("\nDevice: $deviceName, $device")
                }
            }
            else{
                Toast.makeText(this, "Turn on bluetooth first",Toast.LENGTH_LONG).show()
            }
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

        //turn on bluetooth
        turnOnBluetooth()

        //turn off bluetooth
        turnOffBluetooth()

        //Make Bluetooth Visible/Discoverable for other devices
        discovarable()

        //get list of paired devices
        pairedDevices()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val bluetoothIv = findViewById<ImageView>(R.id.bluetoothIv)
        when(requestCode){
            REQUEST_CODE_ENABLE_BT ->
                if(resultCode == Activity.RESULT_OK){
                    bluetoothIv.setImageResource(R.drawable.ic_bluetooth_on)
                    Toast.makeText(this, "Bluetooth turned on",Toast.LENGTH_LONG).show()
                } else{
                    Toast.makeText(this, "Could not turn on bluetooth",Toast.LENGTH_LONG).show()
                }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}