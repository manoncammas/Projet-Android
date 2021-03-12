package fr.isen.cammas.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.cammas.androiderestaurant.databinding.CellCategoryBinding

class CategoryAdapter(private val categories: List<String>, private val onItemClickListener: (String) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       val title: TextView = view.findViewById(R.id.cellCategoryTitle)
        val layout = view.findViewById<View>(R.id.cellLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.CategoryViewHolder {
        val binding = CellCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CategoryViewHolder(binding.root)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        holder.title.text = categories[position]
        holder.layout.setOnClickListener{
            onItemClickListener(categories[position])
        }
    }
}