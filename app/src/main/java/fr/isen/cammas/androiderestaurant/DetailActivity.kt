package fr.isen.cammas.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.cammas.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.cammas.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.cammas.androiderestaurant.databinding.ActivityHomeBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val itemName = intent.getStringExtra("itemName")
        val item = intent.getSerializableExtra("item") as Item

        binding.detailTitle.text = item.name
        binding.ingredients.text = item.ingredients.map{it.name}.joinToString(", ")

        if(item.images[0].isNullOrEmpty()){
            Picasso.get().load("https://img.icons8.com/carbon-copy/2x/no-image.png").into(binding.imageDetail)
        }else{
            Picasso.get().load(item.images[0]).into(binding.imageDetail)
        }
    }
}