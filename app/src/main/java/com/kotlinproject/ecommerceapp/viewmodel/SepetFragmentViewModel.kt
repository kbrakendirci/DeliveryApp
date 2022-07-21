package com.kotlinproject.ecommerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinproject.ecommerceapp.entity.Sepet
import com.kotlinproject.ecommerceapp.repo.SepetRepository

class SepetFragmentViewModel : ViewModel(){

    var kullanici_adi : String = "Hasip"
    var sepetListesi = MutableLiveData<List<Sepet>>()
    val srepo = SepetRepository()

    init {
        sepetListesi = srepo.sepetiGetir()
    }

    fun sepetYemekGetir(){
        srepo.sepetYemekGetir(kullanici_adi)
    }

    fun sepetYemekSil(sepet_yemek_id : Int, kullanici_adi :String) {
        srepo.sepetYemekSil(sepet_yemek_id, kullanici_adi)
    }
}