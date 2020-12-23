package com.sunnyweather.android.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//网络数据源 访问入口 对接口进行封装

//使用suspendCoroutine函数简化Retrofit的回调  P473
object SunnyWeatherNetwork {
    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    private val placeService = ServiceCreator.create(PlaceService::class.java)
    suspend fun searchPlaces(query: String) = placeService.searchPlace(query).await()

    //对WeatherService中新增的接口进行封装
    suspend fun getDailyWeather(lng: String,lat: String) = weatherService.getDailyWeather(lng,lat).await()
    suspend fun getRealtimeWeather(lng: String,lat: String) = weatherService.getRealtimeWeather(lng,lat).await()

    //挂起函数，调用里面的suspendCoroutline 之后立刻被挂起
    private suspend fun<T> Call<T>.await(): T{
        return suspendCoroutine {continuation ->
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {

                    val body = response.body()
                    if (body != null) continuation.resume(body)     //恢复被挂起的协程 并传入服务器响应数据
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)             //恢复被挂起的协程 并传入异常原因
                }
            })
        }
    }
}