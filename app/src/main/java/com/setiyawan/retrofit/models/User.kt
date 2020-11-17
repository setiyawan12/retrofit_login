package com.setiyawan.retrofit.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("user_code") var user_code : String? = null,
    @SerializedName("name") var name : String? = null,
    @SerializedName("username") var status : String? = null,
    @SerializedName("email") var email : String? = null,
    @SerializedName("email_verified_at") var email_verified_at : String? = null,
    @SerializedName("role_id") var role_id : String? = null,
    @SerializedName("avatar") var avatar : String? = null,
    @SerializedName("api_token") var api_token : String? = null

) : Parcelable