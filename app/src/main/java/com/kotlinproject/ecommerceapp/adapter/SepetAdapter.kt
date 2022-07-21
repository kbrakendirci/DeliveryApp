package com.kotlinproject.ecommerceapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kotlinproject.ecommerceapp.databinding.SepetCardTasarimBinding
import com.kotlinproject.ecommerceapp.entity.Sepet
import com.kotlinproject.ecommerceapp.picasso.PicassoService
import com.kotlinproject.ecommerceapp.viewmodel.SepetFragmentViewModel

class SepetAdapter(var mContext : Context,
                   var sepetListesi : List<Sepet>,
                   var viewModel : SepetFragmentViewModel
) : RecyclerView.Adapter<SepetAdapter.SepetCardTasarimTutucu>() {


    inner class SepetCardTasarimTutucu(tasarim : SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root) {

        var tasarim : SepetCardTasarimBinding

        init {
            this.tasarim = tasarim
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {

        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = SepetCardTasarimBinding.inflate(layoutInflater,parent,false)

        return SepetCardTasarimTutucu(tasarim)

    }

    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepet = sepetListesi.get(position)
        val t = holder.tasarim
        t.sepetNesnesi = sepet
        PicassoService().resimGoster(sepet.yemek_resim_adi,t.imageViewResimAdi)

        t.imageViewSil.setOnClickListener {
            Snackbar.make(it,"${sepet.yemek_adi} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET") { viewModel.sepetYemekSil(sepet.yemek_id.toInt(), sepet.kullanici_adi)
                viewModel.sepetYemekGetir()}
                .setActionTextColor(Color.RED)
                .setTextColor(Color.BLUE)
                .setBackgroundTint(Color.WHITE)
                .show()
        }
    }

    override fun getItemCount(): Int {
        return sepetListesi.size
    }
}