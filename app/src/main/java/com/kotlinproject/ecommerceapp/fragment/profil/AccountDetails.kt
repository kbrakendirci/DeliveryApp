package com.kotlinproject.ecommerceapp.fragment.profil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kotlinproject.ecommerceapp.R
import com.kotlinproject.ecommerceapp.utils.loadingDialog
import kotlinx.android.synthetic.main.activity_account_details.*

class AccountDetails : AppCompatActivity() {

    lateinit var loadingDialog : loadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getActionBar()?.setTitle("");
        getSupportActionBar()?.setTitle("");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)
        loadingDialog = loadingDialog(this)
        getUserInfo()

    }

    fun getUserInfo(){
        loadingDialog.startLoadingDialog()
        accountDetailNameEditText.setInputType(InputType.TYPE_NULL);
        accountDetailSurnameEditText.setInputType(InputType.TYPE_NULL);
        accountDetailEmailEditText.setInputType(InputType.TYPE_NULL);
        val db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance()
        val currentUser = user.currentUser!!.uid
        val docRef = db.collection("users").document(currentUser)
        docRef.get()
            .addOnSuccessListener { users ->
                if (users != null) {
                    loadingDialog.dismisDialog()
                    accountDetailNameEditText.setText(users.getString("firstname"), TextView.BufferType.SPANNABLE)
                    accountDetailSurnameEditText.setText(users.getString("secondname"), TextView.BufferType.SPANNABLE)
                    accountDetailEmailEditText.setText(users.getString("email"), TextView.BufferType.SPANNABLE)
                } else {
                    loadingDialog.dismisDialog()
                }
            }
            .addOnFailureListener { exception ->
                loadingDialog.dismisDialog()
            }
    }
}
