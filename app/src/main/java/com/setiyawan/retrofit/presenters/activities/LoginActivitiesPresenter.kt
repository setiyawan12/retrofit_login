package com.setiyawan.retrofit.presenters.activities

import com.setiyawan.retrofit.activities.LoginActivityContract
import com.setiyawan.retrofit.models.User
import com.setiyawan.retrofit.utilities.PluginUtils
import com.setiyawan.retrofit.webservices.PluginAPI
import com.setiyawan.retrofit.webservices.WrappedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivitiesPresenter (v: LoginActivityContract.View?):LoginActivityContract.Interaction{
    private var view: LoginActivityContract.View? =v
    private var api = PluginAPI.instance()

    override fun validate(id: String, password:String):Boolean {
        view?.passwordError(null)
        if (!PluginUtils.isValidPassword(password)) {
            view?.passwordError("password tidak valid")
            return false
        }

        return true
    }
    override fun login(email: String, password: String) {
        view?.isLoading(true)
        api.login(email, password).enqueue(object : Callback<WrappedResponse<User>> {
            override fun onFailure(call: Call<WrappedResponse<User>>, t: Throwable) {
                view?.toast("koneksi ke server tidak bisa")
                println("asu "+ t.message)
                view?.notConect()
            }

            override fun onResponse(call: Call<WrappedResponse<User>>, response: Response<WrappedResponse<User>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null && body.status){
                        view?.toast("Selamat Datang ${body.result!!.name}")
                        view?.success(body.result?.api_token!!)
                    }else {
                        view?.toast("Login Gagal, cek email dan password")
                    }
                }else {
                    view?.toast("Ada yang tidak beres, coba lagi nanti, atau hungungi admin")
                }
                view?.isLoading(false)
            }

        })
    }
    override fun destroy() {view = null}

}
