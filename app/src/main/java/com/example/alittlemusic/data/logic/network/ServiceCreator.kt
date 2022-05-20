package com.example.retrofittest

import android.content.Context
import com.example.alittlemusic.baseClass.MyApplication.Companion.context
import okhttp3.Cookie
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object ServiceCreator {

    private const val BASE_URL = "https://www.liuxin-bs.com/"
    private const val TIME_OUT_LENGTH = 8L

    val builder = getOkHttpClientBuilder(context)

    private fun getOkHttpClientBuilder(context: Context): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)//错误重连
//            cookieJar(Cookie.Builder)
            callTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
            connectTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
            readTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
            val headerInterceptor: Interceptor = object : Interceptor{
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val originalRequest = chain.request()
                    val requestBuilder = originalRequest.newBuilder()
                        .addHeader("Accept-Encoding","gzip, deflate, br")
                        .addHeader("Accept","application/json")
                        .addHeader("Content-Type","application/json; charset=utf-8")
//                        .addHeader("xhrFields","{ withCredentials: true }")
//                        .addHeader("User-Agent","7d384af7d1b0908e7b6f25de5142a9c54ba2a162576a5131016b270f7c497bdc993166e004087dd388801f7711c82f1bd07dd68cd3eabc7746b14e3f0c3f8af9fe5c85647582a507")
//                        .method(originalRequest.method(), originalRequest.body()).apply {
//                            addHeader("Authorization","Bearer " +"7d384af7d1b0908e7b6f25de5142a9c54ba2a162576a5131016b270f7c497bdc993166e004087dd388801f7711c82f1bd07dd68cd3eabc7746b14e3f0c3f8af9fe5c85647582a507")//添加请求头信息，服务器进行token有效性验证
//                        }
                    val request = requestBuilder.build()
                    return chain.proceed(request)
                }
            }
            addInterceptor(headerInterceptor)
        }
        return builder
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//     private val retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .client(builder.build())
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)


}