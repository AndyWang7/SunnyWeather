package com.sunnyweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Place

//ViewModel层
class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    //searchPlaces一调用，searchLiveData数据变化，就执行switchMap转换函数里的，调用仓库层的searchPlaces()
    val placeLiveData = Transformations.switchMap(searchLiveData) {
        query -> Repository.searchPlaces(query)
    }
    fun searchPlaces(query: String){
        searchLiveData.value = query
    }

    //SharedPreferences保存城市名称
    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavePlace() = Repository.getSavePlace();
    fun isPlaceSaved() = Repository.isPlaceSaved();
}