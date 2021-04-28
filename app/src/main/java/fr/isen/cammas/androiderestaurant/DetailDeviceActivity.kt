package fr.isen.cammas.androiderestaurant

import android.bluetooth.BluetoothDevice
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.cammas.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.cammas.androiderestaurant.databinding.ActivityDetailDeviceBinding

class DetailDeviceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDeviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDeviceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var deviceName = intent.getStringExtra("deviceName")

        if(deviceName==null)
            binding.titleDetailDevice.text="Unknown device"
        else
            binding.titleDetailDevice.text=deviceName.toString()
    }
}