package com.kotlinproject.ecommerceapp.repo
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kotlinproject.ecommerceapp.entity.CRUDCevap
import com.kotlinproject.ecommerceapp.entity.Sepet
import com.kotlinproject.ecommerceapp.entity.SepetCevap
import com.kotlinproject.ecommerceapp.retrofit.ApiUtils
import com.kotlinproject.ecommerceapp.retrofit.SepetDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SepetRepository {

    var sepetListesi: MutableLiveData<List<Sepet>>
    var sdao : SepetDaoInterface

    init {
        sdao = ApiUtils.getSepetDaoInterface()
        sepetListesi = MutableLiveData()
    }

    fun sepetiGetir() : MutableLiveData<List<Sepet>>{
        return sepetListesi
    }

    fun sepetYemekEkle(yemek_adi : String, yemek_resim_adi : String,
                       yemek_fiyat : Int, yemek_siparis_adet : Int, kullanici_adi : String) {

        sdao.sepetYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat,
            yemek_siparis_adet, kullanici_adi).enqueue(object : Callback<CRUDCevap> {

            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body().success
                val mesaj = response.body().message

                Log.e("Sepete Ekle","$basari - $mesaj")
            }

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}

        })

    }

    fun sepetYemekSil(sepet_yemek_id : Int, kullanici_adi: String) {

        sdao.sepetYemekSil(sepet_yemek_id, kullanici_adi).enqueue(object : Callback<CRUDCevap> {

            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>?) {}

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {}

        })

    }

    fun sepetYemekGetir(kullanici_adi: String) {

        sdao.sepetYemekGetir(kullanici_adi).enqueue(object : Callback<SepetCevap> {

            override fun onResponse(call: Call<SepetCevap>?, response: Response<SepetCevap>) {

                val liste = response.body().sepet_yemekler
                sepetListesi.value = liste

                /*if (liste == null){
                    sepetListesi.value = liste
                } else {
                    sepetListesi.value = listOf()
                }*/
            }

            override fun onFailure(call: Call<SepetCevap>?, t: Throwable?) {
                Log.e("Hata", t.toString())
                sepetListesi.value = listOf()
            }

        })
    }

}