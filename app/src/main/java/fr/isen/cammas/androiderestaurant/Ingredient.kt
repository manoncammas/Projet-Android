package fr.isen.cammas.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Ingredient(
        @SerializedName("name_fr") val name: String
) : Serializable