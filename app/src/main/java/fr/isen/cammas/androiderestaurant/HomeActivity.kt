package fr.isen.cammas.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.cammas.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val myTag = "Destroy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val monIntent : Intent = Intent(this,CategoryActivity::class.java)

        binding.entreesAction.setOnClickListener {
            monIntent.putExtra("category", "Entrées")
            startActivity(monIntent)
        }
        binding.platsAction.setOnClickListener {
            monIntent.putExtra("category", "Plats")
            startActivity(monIntent)
        }
        binding.dessertsAction.setOnClickListener {
            monIntent.putExtra("category", "Desserts")
            startActivity(monIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(myTag, "onDestroy")
    }
}