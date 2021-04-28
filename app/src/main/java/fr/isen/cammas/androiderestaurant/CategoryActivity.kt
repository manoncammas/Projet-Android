package fr.isen.cammas.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.cammas.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryName = intent.getStringExtra("category")

        binding.category.text = categoryName

        //binding.listMenu.layoutManager = LinearLayoutManager(this)
        /*binding.listMenu.adapter = CategoryAdapter(listOf("Pates carbonara", "Salade", "Burger maison")) {item ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", item)
            startActivity(intent)
        }*/

        loadData(categoryName ?: "")
    }

    private fun loadData(category: String){
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val JSONData = JSONObject()
        JSONData.put("id_shop", 1)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, JSONData,
                Response.Listener { response ->
                    val rep = response.toString()
                    Log.i("test", "response : $rep")
                    parsingResult(rep, category)
                },
                Response.ErrorListener { error ->
                    Log.e("erreur", "response : ${error.message}")
                }
        )
        Volley.newRequestQueue(this).add(jsonObjectRequest)
    }

    private fun parsingResult (resultJSON: String, category: String){
        val result = GsonBuilder().create().fromJson(resultJSON, DataResult::class.java)
        val itemsCat = result.data.firstOrNull { it.name == category } //on recupere la premiere ou null dont le nom est egal a celui en parametre
        loadMenu(itemsCat?.items) //on affiche les items de la categorie correspondante
    }

    private fun loadMenu(itemsCat: List<Item>?) {
        itemsCat?.let {//si la liste est pas vide, alors (= if itemsCat != null)
            val adapter = CategoryAdapter(it) { item ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("itemName", item.name)
                intent.putExtra("item", item)
                startActivity(intent)
            }
            binding.listMenu.layoutManager = LinearLayoutManager(this)
            binding.listMenu.adapter = adapter
        }
    }
}