package com.setiyawan.retrofit.webservices

import com.google.gson.annotations.SerializedName
import com.setiyawan.retrofit.models.User
import com.setiyawan.retrofit.utilities.PluginUtils
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

class PluginAPI{
    companion object{
        private var retrofit: Retrofit? = null

        private var okHttpClient = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build()

        fun instance() : PluginAPIService = getClient().create(PluginAPIService::class.java)

        private fun getClient(): Retrofit {
            return if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(PluginUtils.API_ENDPOINT).client(okHttpClient).addConverterFactory(
                    GsonConverterFactory.create()).build()
                retrofit!!
            } else{
                retrofit!!
            }
        }

    }
}

interface PluginAPIService {

    @FormUrlEncoded
    @POST("api/login")
    fun login(@Field("email") email: String? = null, @Field("password") password: String? = null) : Call<WrappedResponse<User>>
}


data class WrappedResponse<T> (
    @SerializedName("message") var message : String?,
    @SerializedName("status") var status : Boolean,
    @SerializedName("results") var result : T?
){
    constructor() : this(null, false, null)
}

data class WrappedListResponse<T> (
    @SerializedName("message") var message : String?,
    @SerializedName("status") var status : Boolean,
    @SerializedName("results") var result : List<T>
){
    constructor() : this(null, false, listOf())

}