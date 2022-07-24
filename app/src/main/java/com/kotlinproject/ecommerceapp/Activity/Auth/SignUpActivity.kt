package com.kotlinproject.ecommerceapp.Activity.Auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kotlinproject.ecommerceapp.R

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.kotlinproject.ecommerceapp.utils.loadingDialog
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var loadingDialog : loadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setup()
    }
    override fun onBackPressed() {
        startActivity(Intent(this,SiginActivity::class.java))
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }
    private fun setup(){
        closeKeyboard(registerPageMainLayout)
        auth = Firebase.auth
        loadingDialog = loadingDialog(this)
        button(auth)
    }

    private fun button(auth : FirebaseAuth) {
        RegisterBtn.setOnClickListener {
            loadingDialog.startLoadingDialog()
            if (etRegisterName.text!!.isNotEmpty() && etRegisterEmail.text!!.isNotEmpty() && etRegisterPassword.text!!.isNotEmpty()&& etRegisterPasswordAgain.text!!.isNotEmpty())
            { if (etRegisterPassword.text.toString().equals(etRegisterPasswordAgain.text.toString()) ) {
                newMemberRegister(
                    etRegisterEmail.text.toString(),
                    etRegisterPassword.text.toString())
            }
            else {
                loadingDialog.dismisDialog()
                showOneActionAlert("Error!","Your passwords are not same, please try again.",null)
            }
            } else {
                loadingDialog.dismisDialog()
                showOneActionAlert("Error!","Something went wrong, please try again.",null)
            }
        }

        LoginfromRegisterBtn.setOnClickListener {
            startActivity(Intent(this,SiginActivity::class.java))
            overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
        }
    }
    //New member register function
    private fun newMemberRegister(mail: String, password: String) {
        auth.createUserWithEmailAndPassword(mail, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                      val user = auth.currentUser
                    updateUI(user)
                } else {
                      Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun saveFireStore(firstName: String,secondName: String, userEmail: String, userUid : String) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["firstname"] = firstName
        user["secondname"] = secondName
        user["email"] = userEmail
        user["count"]= 0
        db.collection("users").document(userUid)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "You Signed In successfully", Toast.LENGTH_LONG).show()

            }
            .addOnFailureListener {
                auth.currentUser?.delete()
            }
    }
    fun updateUI(account: FirebaseUser?) {
        if (account != null) {
            sendConfirmationEmail()
            saveFireStore(etRegisterName.text.toString(),etRegisterSurName.text.toString(),etRegisterEmail.text.toString(), account.uid)
            onBackPressed()
        } else {
            loadingDialog.dismisDialog()
            showOneActionAlert("Error!","Something went wrong, please try again.",null)
        }
    }
    // Send to new member check email
    private fun sendConfirmationEmail(){
        val User = auth.currentUser;
        User!!.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    loadingDialog.dismisDialog()
                    showOneActionAlert("Success","Verification email sent to "+ User.getEmail()) {
                        FirebaseAuth.getInstance().signOut()
                        finish()
                    }
                } else {
                      loadingDialog.dismisDialog()
                    showOneActionAlert("Error","" + task.exception?.message,null)
                }
            }
    }

}
fun Activity.showOneActionAlert(title: String, detailText: String, buttonFunction: (() -> Unit)?){
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(detailText)
    builder.setPositiveButton("Okay"){ dialogInterface, i ->
        if (buttonFunction != null) {
            buttonFunction()
        }
    }
    builder.show()

}