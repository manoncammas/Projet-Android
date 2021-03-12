package fr.isen.cammas.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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
        binding.listMenu.layoutManager = LinearLayoutManager(this)
        binding.listMenu.adapter = CategoryAdapter(listOf("Pates carbonara", "Salade", "Burger maison")) {item ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", item)
            startActivity(intent)
            getAPI(categoryName ?: "")
        }
    }

    private fun getAPI(category: String){
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val JSONData = JSONObject()
        JSONData.put("id_shop", 1)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, JSONData,
                Response.Listener { response ->
                    val rep = response.toString()
                    Log.i("test", "response : $rep")
                },
                Response.ErrorListener { error ->
                    Log.e("erreur", "response : ${error.message}")
                }
        )
        Volley.newRequestQueue(this).add(jsonObjectRequest)
    }
}