package fr.isen.cammas.androiderestaurant

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.cammas.androiderestaurant.databinding.ActivityBleScanBinding

class BLEScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBleScanBinding
    private var isScanning = false
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothLeScanner: BluetoothLeScanner? = null
    private var scanning = false
    private val handler = Handler()

    // Stops scanning after 10 seconds.
    private val SCAN_PERIOD: Long = 10000
    private lateinit var adapter: DeviceListAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityBleScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bluetoothAdapter = getSystemService(BluetoothManager::class.java)?.adapter

        startBLEIfPossible()
        isDeviceHasBLESupport()

        binding.bleScanPlayPauseAction.setOnClickListener {
            togglePlayPauseAction()
            //isDeviceHasBLESupport()
        }
    }

    private fun startBLEIfPossible() {
        when {
            !isDeviceHasBLESupport() || bluetoothAdapter == null -> {
                Toast.makeText(this, "Cet appareil n'est pas compatible avec le Bluetooth Low Energy", Toast.LENGTH_SHORT).show()
            }
            !(bluetoothAdapter?.isEnabled ?: false)-> { //bluetoothAdapter.isEnabled est a false donc pas activé
                //je dois activer le bluetooth
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE) //lance une activité qui permet de gerer l'activation du bluetooth
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT) //on attend un resultat quand il a fini
            }
            ActivityCompat.checkSelfPermission
            (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED -> {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSION_LOCATION)
            }
            else -> {
                bluetoothLeScanner = bluetoothAdapter?.bluetoothLeScanner
                initRecycler()
            }
        }
    }

    private fun isDeviceHasBLESupport(): Boolean =
        packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)

    private fun togglePlayPauseAction() {
        isScanning = !isScanning

        if(isScanning) {
            binding.textScan.text = getString(R.string.text_scan_pause)
            binding.bleScanPlayPauseAction.setImageResource(R.drawable.ic_baseline_pause_24)
            binding.loadingProgress.isVisible = true
            binding.divider.isVisible = false
            scanLeDevice()
        }
        else {
            binding.textScan.text = getString(R.string.text_scan_play)
            binding.bleScanPlayPauseAction.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            binding.loadingProgress.visibility = View.INVISIBLE
            binding.divider.isVisible = true
        }
    }

    private fun initRecycler(){
        adapter= DeviceListAdapter((mutableListOf()))
        binding.recyclerViewBle.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewBle.adapter = adapter
    }

    private fun scanLeDevice() {
        bluetoothLeScanner?.let { scanner ->
            if (!scanning) { // Stops scanning after a pre-defined scan period.
                handler.postDelayed({
                    scanning = false
                    scanner.stopScan(leScanCallback)
                }, SCAN_PERIOD)
                scanning = true
                scanner.startScan(leScanCallback)
            } else {
                scanning = false
                scanner.stopScan(leScanCallback)
            }
        }
    }

    // Device scan callback.
    private val leScanCallback: ScanCallback = object : ScanCallback() {

        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            //Log.i("test Bluetooth", "${result.device}")
            adapter.addDevice(result.device)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK)
            startBLEIfPossible()
    }

    /*private fun displayDevice(device: List<BluetoothDevice>?){
        device?.let {
            val adapter = DeviceListAdapter(it) { device ->
                val intent = Intent(this, DetailDeviceActivity::class.java)
                startActivity(intent)
            }
            binding.recyclerViewBle.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewBle.adapter = adapter
        }
    }*/

    companion object {
        const private val REQUEST_ENABLE_BT = 33
        const private val REQUEST_PERMISSION_LOCATION = 22
    }
}