package com.setiyawan.retrofit.utilities

import android.content.Context
import android.content.Context.MODE_PRIVATE

class PluginUtils   {
    companion object{
        var API_ENDPOINT ="https://reqres.in/"

        fun getToken(context:Context):String?{
            val token = context.getSharedPreferences("USER", MODE_PRIVATE)
            return token?.getString("TOKEN", null)

        }
        fun setToken(context: Context, token:String){
            val pref = context.getSharedPreferences("USER", MODE_PRIVATE)
            pref.edit().apply(){
                putString("TOKEN", token)
                apply()
            }
        }
        fun clearToken(context: Context){
            val pref= context.getSharedPreferences("USER", MODE_PRIVATE)
            pref.edit().clear().apply()
        }
        fun isValidPassword(password: String)= password.length>6
    }
}