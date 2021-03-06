package fr.isen.cammas.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Item (
        @SerializedName("id") val id : Int,
        @SerializedName("name_fr") val name : String,
        @SerializedName("images") val images : List<String>,
        @SerializedName("prices") val prices : List<Price>,
        @SerializedName("ingredients") val ingredients : List <Ingredient>
) : Serializable
