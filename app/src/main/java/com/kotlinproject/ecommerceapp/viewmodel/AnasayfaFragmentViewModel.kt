package com.example.aciktim.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlinproject.ecommerceapp.entity.Yemekler
import com.kotlinproject.ecommerceapp.repo.YemeklerRepository

class AnasayfaFragmentViewModel : ViewModel() {

    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    val yrepo = YemeklerRepository()

    init {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
    }

    fun yemekleriYukle(){
        yrepo.tumYemekleriAl()
    }

    fun ara(sonuc : String) {
        yrepo.yemekAra(sonuc)
    }

    fun fiyatSirala(){ yrepo.fiyatSirala()}

    fun A_ZSirala(){yrepo.A_ZSirala()}

}