package  com.kotlinproject.ecommerceapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sepet (@SerializedName("sepet_yemek_id")     @Expose var yemek_id:String,
                  @SerializedName("yemek_adi")          @Expose var yemek_adi:String,
                  @SerializedName("yemek_resim_adi")    @Expose var yemek_resim_adi:String,
                  @SerializedName("yemek_fiyat")        @Expose var yemek_fiyat:String,
                  @SerializedName("yemek_siparis_adet") @Expose var yemek_siparis_adet:String,
                  @SerializedName("kullanici_adi")      @Expose var kullanici_adi:String,) : Serializable {
}