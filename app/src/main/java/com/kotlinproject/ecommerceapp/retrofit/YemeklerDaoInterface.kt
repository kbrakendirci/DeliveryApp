package com.kotlinproject.ecommerceapp.retrofit
import com.kotlinproject.ecommerceapp.entity.YemeklerCevap
import retrofit2.Call
import retrofit2.http.GET

interface YemeklerDaoInterface {

    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekler() : Call<YemeklerCevap>

}