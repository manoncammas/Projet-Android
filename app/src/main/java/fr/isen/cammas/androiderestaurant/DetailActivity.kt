package fr.isen.cammas.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener
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

        var imageListener : ImageListener = object : ImageListener {
            override fun setImageForPosition(position: Int, imageView: ImageView){
                if(item.images[0].isNullOrEmpty()){
                    Picasso.get().load("https://www.allyoucanpost.com/bundles/aycpfront/front/assets/img/empty.png").into(imageView)
                }else{
                    Picasso.get().load(item.images[position]).into(imageView)
                }
            }
        }
        binding.carouselView.pageCount = item.images.size
        binding.carouselView.setImageListener(imageListener)

        var quantite = 0
        val prix = item.prices[0].price.toFloat()
        var totalPrice : Float = 0F

        binding.removeButton.setOnClickListener {
            quantite -= 1
            if (quantite < 0) {
                quantite=0
            }
            totalPrice = quantite*prix
            binding.quantite.text = quantite.toString()
            binding.totalButton.text = "TOTAL ${totalPrice} €"
        }

        binding.addButton.setOnClickListener {
            quantite += 1
            totalPrice = quantite*prix
            binding.quantite.text = quantite.toString()
            binding.totalButton.text = "TOTAL ${totalPrice} €"
        }

    }
}