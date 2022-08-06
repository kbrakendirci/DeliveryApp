package com.kotlinproject.ecommerceapp.fragment
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.kotlinproject.ecommerceapp.MainActivity
import com.kotlinproject.ecommerceapp.R
import com.kotlinproject.ecommerceapp.adapter.SepetAdapter
import com.kotlinproject.ecommerceapp.databinding.FragmentSepetBinding
import com.kotlinproject.ecommerceapp.entity.Sepet
import com.kotlinproject.ecommerceapp.viewmodel.SepetFragmentViewModel

class SepetFragment : Fragment() {

    private lateinit var tasarim : FragmentSepetBinding
    private lateinit var viewModel : SepetFragmentViewModel
    private lateinit var listeSepet : List<Sepet>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)
        tasarim.sepetFragment = this


        viewModel.sepetListesi.observe(viewLifecycleOwner) {

            (activity as MainActivity).badgeNumber(viewModel.sepetListesi.value?.size)

            val adapter = SepetAdapter(requireContext(), it, viewModel)
            tasarim.sepetAdapter = adapter

            listeSepet = viewModel.sepetListesi.value!!

            tasarim.toplamFiyat.text = toplamFiyat(listeSepet, listeSepet.size - 1).toString() + " ₺"
            tasarim.geneltoplamFiyat.text=genelToplamFiyat(listeSepet, listeSepet.size - 1).toString() + " ₺"
        }

        return tasarim.root
    }
    fun siparişverildiBildirim(){ Snackbar.make(requireView(), "Siparişiniz Verildi", Snackbar.LENGTH_LONG)
        .setTextColor(Color.BLUE)
        .setBackgroundTint(Color.WHITE)
        .show() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel : SepetFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetYemekGetir()
    }

    fun toplamFiyat (listeSepet: List<Sepet>, size : Int) : Int {

        var toplam = 0

        for(s in 0..size) {

            toplam += (listeSepet.get(s).yemek_siparis_adet.toInt() * listeSepet.get(s).yemek_fiyat.toInt())

        }
        return toplam
    }
    fun genelToplamFiyat (listeSepet: List<Sepet>, size : Int) : Int {

        var toplam = 0

        for(s in 0..size) {

            toplam += (listeSepet.get(s).yemek_siparis_adet.toInt() * listeSepet.get(s).yemek_fiyat.toInt())

        }
        val kuryeücreti=toplam+20
        return kuryeücreti
    }
}