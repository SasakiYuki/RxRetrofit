package com.wacode.yuki.rxretrofit.API

import com.wacode.yuki.rxretrofit.Entity.HogeEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

/**
 * Created by yuki on 2016/05/16.
 */
interface HogeApi {
    @GET("/event-all.json"  )
    fun getEvent(): Observable<HogeEntity>
    @Headers("Content-Type: application/json")
    @POST("/api/users/create")
    fun createUser(@Body sJson:String):Observable<HogeEntity>
}