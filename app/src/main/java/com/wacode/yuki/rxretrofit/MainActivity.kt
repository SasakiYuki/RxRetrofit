package com.wacode.yuki.rxretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.wacode.yuki.rxretrofit.API.HogeApi
import com.wacode.yuki.rxretrofit.Entity.HogeEntity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startApi()
    }

    private fun startApi(){
        val api = restClient.create(HogeApi::class.java)
        api.getEvent()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<HogeEntity>(){
                    override fun onCompleted() {
                        Log.d(TAG, "onCompleted")
                    }

                    override fun onError(p0: Throwable?) {
                        p0!!.printStackTrace()
                        Log.d(TAG, "error")
                    }

                    override fun onNext(p0: HogeEntity?) {
                        Log.d(TAG,p0!!.hoge.toString())
                    }

                })
    }

    private val restClient:Retrofit
        get() = Retrofit.Builder().client(getClient()).baseUrl(ENDPOINT).addConverterFactory(GsonConverterFactory.create(Gson())).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()

    private fun getClient():OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    companion object{
        private val ENDPOINT = "http://www.hogehoge.com"
        private val TAG = MainActivity.javaClass.simpleName
    }
}
