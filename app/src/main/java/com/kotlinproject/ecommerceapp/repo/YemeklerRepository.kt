package com.kotlinproject.ecommerceapp.repo
import androidx.lifecycle.MutableLiveData
import com.kotlinproject.ecommerceapp.entity.Yemekler
import com.kotlinproject.ecommerceapp.entity.YemeklerCevap
import com.kotlinproject.ecommerceapp.retrofit.ApiUtils
import com.kotlinproject.ecommerceapp.retrofit.YemeklerDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerRepository {

    var yemeklerListesi: MutableLiveData<List<Yemekler>>
    var ydao : YemeklerDaoInterface

    init {
        ydao = ApiUtils.getYemeklerDaoInterface()
        yemeklerListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }

    fun tumYemekleriAl(){
        ydao.tumYemekler().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemeklerListesi.value = liste
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun yemekAra(aramaKelimesi : String){
        ydao.tumYemekler().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler

                val sonuc =  liste.filter { it.yemek_adi.contains(aramaKelimesi, true) }
                yemeklerListesi.value = sonuc
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

    fun fiyatSirala() {

        ydao.tumYemekler().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler

                val sirala =  liste.sortedWith(compareBy { it.yemek_fiyat })
                yemeklerListesi.value = sirala
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })

    }

    fun A_ZSirala() {

        ydao.tumYemekler().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler

                val sirala =  liste.sortedWith(compareBy { it.yemek_adi })
                yemeklerListesi.value = sirala
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })

    }



}