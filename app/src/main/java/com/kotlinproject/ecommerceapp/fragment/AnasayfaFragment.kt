package com.kotlinproject.ecommerceapp.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.aciktim.viewmodel.AnasayfaFragmentViewModel
import com.kotlinproject.ecommerceapp.R
import com.kotlinproject.ecommerceapp.adapter.PopularCardAdapter
import com.kotlinproject.ecommerceapp.adapter.YemeklerAdapter
import com.kotlinproject.ecommerceapp.databinding.FragmentAnasayfaBinding
import com.kotlinproject.ecommerceapp.viewmodel.SepetFragmentViewModel

class AnasayfaFragment : Fragment() , SearchView.OnQueryTextListener {

    private lateinit var tasarim : FragmentAnasayfaBinding
    private lateinit var viewModel : AnasayfaFragmentViewModel
    private lateinit var viewModelSepet : SepetFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment = this
        tasarim.toolbarAnasayfa.title = ""

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val adapter = YemeklerAdapter(requireContext(),it,viewModel)
            tasarim.yemeklerAdapter = adapter
        }

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val adapterPopular = PopularCardAdapter(requireContext(),it,viewModel)
            tasarim.popularAdapter = adapterPopular
        }
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val tempViewModel : AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel

        val sepetTempViewModel : SepetFragmentViewModel by viewModels()
        viewModelSepet = sepetTempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
          viewModel.ara(query)

        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
         viewModel.ara(newText)
         return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
        viewModelSepet.sepetYemekGetir()
    }

    fun fiyatSirala(){ viewModel.fiyatArtanSirala() }
    fun fiyatAzalanSirala(){ viewModel.fiyatAzalanSirala() }

    fun A_ZSirala(){ viewModel.A_ZSirala() }


    fun Z_ASirala(){ viewModel.Z_ASirala() }
}

