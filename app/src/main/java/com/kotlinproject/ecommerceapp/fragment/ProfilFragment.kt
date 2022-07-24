package com.kotlinproject.ecommerceapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.kotlinproject.ecommerceapp.Activity.Auth.SiginActivity
import com.kotlinproject.ecommerceapp.R
import kotlinx.android.synthetic.main.fragment_profil.*

class ProfilFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_profil, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeMyPasswordLayout.setOnClickListener {
            val action =
                ActionOnlyNavDirections(R.id.action_navigation_authentication_to_change_My_Password)
            Navigation.findNavController(it).navigate(action)
        }
        myAccountLayout.setOnClickListener {
            val action =
                ActionOnlyNavDirections(R.id.action_profilFragment_to_accountDetails)
            Navigation.findNavController(it).navigate(action)
        }

        signOutLayout.setOnClickListener {
            logOut()
        }
    }
    private fun logOut(){
        FirebaseAuth.getInstance().signOut()
        activity?.let{
            val intent = Intent (it, SiginActivity::class.java)
            it.startActivity(intent)
            it.finish()
        }
    }
}
