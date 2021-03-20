package fr.isen.cammas.androiderestaurant

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fr.isen.cammas.androiderestaurant.databinding.CellCategoryBinding
import fr.isen.cammas.androiderestaurant.databinding.CellDeviceBinding

class DeviceListAdapter(private val devices: MutableList<BluetoothDevice>) : RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val binding = CellDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(binding.root)
    }

    class DeviceViewHolder(binding: ConstraintLayout) : RecyclerView.ViewHolder(binding) {
        val titleDevice: TextView = binding.findViewById(R.id.titleDevice)
        val layout = binding.findViewById<View>(R.id.cellDeviceLayout)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.titleDevice.text = devices[position].toString()
         holder.layout.setOnClickListener {
            //onDeviceClickListener(devices[position])
        }
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    fun addDevice(device: BluetoothDevice) {
        if (!devices.contains(device)) {
            devices.add(device)
        }
    }
}