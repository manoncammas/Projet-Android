package fr.isen.cammas.androiderestaurant

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.cammas.androiderestaurant.databinding.CellCategoryBinding
import java.lang.StringBuilder

class CategoryAdapter(private val categories: List<Item>, private val onItemClickListener: (Item) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.cellCategoryTitle)
        val layout = view.findViewById<View>(R.id.cellLayout)
        val image : ImageView = view.findViewById(R.id.imageItem)
        val prix: TextView = view.findViewById(R.id.prix)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.CategoryViewHolder {
        val binding = CellCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        holder.title.text = categories[position].name
        holder.layout.setOnClickListener{
            onItemClickListener(categories[position])
        }
        if(categories[position].images[0].isNullOrEmpty()){
            Picasso.get().load("https://img.icons8.com/carbon-copy/2x/no-image.png").into(holder.image)
        }else{
            Picasso.get().load(categories[position].images[0]).into(holder.image)
        }
        holder.prix.text = categories[position].prices[0].price + "€"
    }
}