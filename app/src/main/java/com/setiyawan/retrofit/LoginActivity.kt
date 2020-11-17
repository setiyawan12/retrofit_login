package com.setiyawan.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.setiyawan.retrofit.activities.LoginActivityContract
import com.setiyawan.retrofit.presenters.activities.LoginActivitiesPresenter
import com.setiyawan.retrofit.utilities.PluginUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginActivityContract.View{
    private var presenter = LoginActivitiesPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginActivitiesPresenter(this)
        Login()
        isLoggedIn()
    }
    private fun Login(){
        btnLogin.setOnClickListener{
            val email = etId.text.toString().trim()
            val pass = etPass.text.toString().trim()
            if (email.isNotEmpty() && pass.isNotEmpty()){
                if (pass.length > 5 ){
                    presenter.login(email, pass)
                }else {
                    toast("Cek password anda")
                }
            }else{
                toast("isi semua form")

            }
        }
    }
    override fun toast(message: String) = Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
    override fun success(token: String) {
        PluginUtils.setToken(this, "Bearer $token")
        println(token)
        startActivity(Intent(this, MainActivity::class.java)).also { finish() }
    }

    override fun isLoading(state: Boolean) {
        btnLogin.isEnabled = !state
    }
    //    override fun isLoading(state: Boolean) {
//        btnLogin.isEnabled = !state
//    }
    override fun idError(err: String?) {inId.error = err}
    override fun passwordError(err: String?) {inPass.error = err}
    override fun notConect() {
        btnLogin.isEnabled = true
    }

    private fun isLoggedIn(){
        val token = PluginUtils.getToken(this)
        if(token != null){
            startActivity(Intent(this, MainActivity::class.java)).also { finish() }
        }
    }

}