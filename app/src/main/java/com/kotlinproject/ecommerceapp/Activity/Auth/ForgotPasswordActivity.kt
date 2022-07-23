package com.kotlinproject.ecommerceapp.Activity.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlinproject.ecommerceapp.R
import kotlinx.android.synthetic.main.activity_forgot_password.*
import android.app.Activity
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        closeKeyboard(forgotPasswordMainLayout)

        forgotPasswordButton.setOnClickListener(){

            //Şifreyi yenilemek için Firebase bağlantısı yapılır
            if (etForgotPassword.text!!.isNotEmpty()){
                FirebaseAuth.getInstance().sendPasswordResetEmail(etForgotPassword.text.toString())
                    .addOnCompleteListener{ task ->
                        //Mailin Yollandığı kontrol edilir.
                        if(task.isSuccessful){
                            showOneActionAlert("Success","We send an email your mailbox please check your e-mail box",null)
                            //  val intent = Intent(this, ActivityLoginPage::class.java)
                            //startActivity(intent)

                        }else{
                            showOneActionAlert("Error","" + task.exception?.message,null)
                        }
                    }
                    .addOnFailureListener {
                        showOneActionAlert("Success",it.localizedMessage,null)
                    }
            }else{
                showOneActionAlert("Error","Please enter your Email Address" ,null)

            }
        }
        forgotPasswordFromLoginBtn.setOnClickListener {
            startActivity(Intent(this,SiginActivity::class.java))
            overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
        }
    }
}