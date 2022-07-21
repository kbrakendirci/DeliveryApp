package com.kotlinproject.ecommerceapp.picasso

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoService {

    fun resimGoster(yemek_resim_adi : String, imageView : ImageView) {
        var url = "http://kasimadalan.pe.hu/yemekler/resimler/$yemek_resim_adi"

        Picasso.get().load(url).into(imageView)
    }

}