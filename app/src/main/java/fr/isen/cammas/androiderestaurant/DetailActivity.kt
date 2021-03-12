package fr.isen.cammas.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.cammas.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.cammas.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.cammas.androiderestaurant.databinding.ActivityHomeBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemName = intent.getStringExtra("itemName")

        binding.detailTitle.text = itemName
    }
}