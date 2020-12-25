package com.sunnyweather.android.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Retrofit构建器的最佳写法   11.6.3 P459
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"

    //构建出的Retrofit对象是全局的
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //根据传入的类调用create()方法创建 相应的Service接口的动态代理对象
    fun <T>create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
   // 获取接口的动态代理对象写法如下：
    //var appService = ServiceCreator.create<AppService>()
   //泛型实例化
    inline fun <reified T>create(): T = create(T::class.java)
}