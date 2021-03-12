package fr.isen.cammas.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataResult (
        @SerializedName("data") val data: List<Category>
) : Serializable