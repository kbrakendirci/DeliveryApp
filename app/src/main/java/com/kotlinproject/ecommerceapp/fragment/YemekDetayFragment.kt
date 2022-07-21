package com.kotlinproject.ecommerceapp.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.aciktim.viewmodel.YemekDetayFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.kotlinproject.ecommerceapp.R
import com.kotlinproject.ecommerceapp.databinding.FragmentYemekDetayBinding
import com.kotlinproject.ecommerceapp.picasso.PicassoService
import com.kotlinproject.ecommerceapp.viewmodel.SepetFragmentViewModel

class YemekDetayFragment : Fragment() {

    private lateinit var tasarim : FragmentYemekDetayBinding
    private lateinit var viewModel : YemekDetayFragmentViewModel
    private lateinit var viewModelSepet : SepetFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_detay, container, false)
        tasarim.yemekDetayFragment = this
        tasarim.hasip = "Hasip"
        val bundle : YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek

        tasarim.yemekNesnesi = gelenYemek


        PicassoService().resimGoster(gelenYemek.yemek_resim_adi, tasarim.imageViewDetayResimAd)

        var sayi = 0

        tasarim.imageViewArti.setOnClickListener {
             sayi += 1
            tasarim.textViewYemekAdet.text = sayi.toString()

        }

        tasarim.imageViewEksi.setOnClickListener {
            sayi -= 1

            if(sayi < 0) { sayi = 0 }
            tasarim.textViewYemekAdet.text = sayi.toString()
        }

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : YemekDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel

        val tempViewModelSepet : SepetFragmentViewModel by viewModels()
        viewModelSepet = tempViewModelSepet

    }

    override fun onResume() {
        super.onResume()
        viewModelSepet.sepetYemekGetir()
    }

    fun buttonSepeteEkle(yemek_adi : String, yemek_resim_adi : String, yemek_fiyat : Int, yemek_siparis_adet : Int, kullanici_adi : String) {
        viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

    fun sepeteEklendiBildirim(){ Snackbar.make(requireView(), "Sepete Eklendi",Snackbar.LENGTH_LONG)
        .setTextColor(Color.BLUE)
        .setBackgroundTint(Color.WHITE)
        .show() }

    fun gecis() {
        Navigation.findNavController(tasarim.root).navigate(R.id.sepetFragment)
        Navigation.findNavController(tasarim.root).navigate(R.id.anasayfaFragment)
    }

}