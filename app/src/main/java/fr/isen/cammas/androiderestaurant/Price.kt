package fr.isen.cammas.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Price(
        @SerializedName("price") val price: String
) : Serializable