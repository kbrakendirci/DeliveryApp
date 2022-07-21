package com.kotlinproject.ecommerceapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.aciktim.viewmodel.AnasayfaFragmentViewModel
import com.kotlinproject.ecommerceapp.databinding.PopularCardTasarimBinding
import com.kotlinproject.ecommerceapp.entity.Yemekler
import com.kotlinproject.ecommerceapp.fragment.AnasayfaFragmentDirections
import com.kotlinproject.ecommerceapp.picasso.PicassoService

class PopularCardAdapter(var mContext : Context,
                         var yemeklerListesi : List<Yemekler>,
                         var viewModel : AnasayfaFragmentViewModel
) : RecyclerView.Adapter<PopularCardAdapter.PopularYemekCardTasarimTutucu>() {

    inner class PopularYemekCardTasarimTutucu (tasarim : PopularCardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){

        var tasarim : PopularCardTasarimBinding

        init {
            this.tasarim = tasarim
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularYemekCardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = PopularCardTasarimBinding.inflate(layoutInflater,parent,false)

        return PopularYemekCardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: PopularYemekCardTasarimTutucu, position: Int) {

        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim
        t.yemekNesnesi = yemek


        PicassoService().resimGoster(t.yemekNesnesi!!.yemek_resim_adi,t.popularImageViewYemek)

        t.popularCard.setOnClickListener{
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek = yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }


}