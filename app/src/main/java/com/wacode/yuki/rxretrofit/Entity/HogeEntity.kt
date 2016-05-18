package com.wacode.yuki.rxretrofit.Entity

import com.google.gson.annotations.SerializedName

/**
 * Created by yuki on 2016/05/16.
 */
data class HogeEntity(
        @SerializedName("base_url")
        val hoge:String
)