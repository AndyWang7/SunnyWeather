package com.sunnyweather.android.ui.weather

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository

class WeatherViewModel : ViewModel(){
    //LiveData对象
    private val locationLiveData = MutableLiveData<com.sunnyweather.android.logic.model.Location>()

    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    //调用switchMap观察locationLiveData对象 发生变化调用仓库层 refreshWeather方法得到真正的数据
    //switchMap 接收两个参数 第一个刚定义的要观察的LiveData对象， 第二个参数是个转换函数   di返回一个LiveData对象 location  并且switchMap把它转换成可观察的
    val weatherLiveData = Transformations.switchMap(locationLiveData){ location ->
        Repository.refreshWeather(location.lng, location.lat)
    }

    //提供给Activity 调用传入 lng lat， 传给LiveData对象 locationLiveData, 上面定义的Location类型的
    fun refreshWeather(lng : String, lat: String){
        locationLiveData.value = com.sunnyweather.android.logic.model.Location(lng,lat)
    }
}