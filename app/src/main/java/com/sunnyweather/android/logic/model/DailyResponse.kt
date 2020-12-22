package com.sunnyweather.android.logic.model

import java.util.*

data class DailyResponse(val status: String, val result: Result){
    data class Result(val daily: Daily)
    data class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>, )
    data class Temperature(val max: Float, val min: Float)
    data class Skycon(val value: String, val date: Date)
    data class LifeIndex(val coldRisk: List<LifeDescription>,val carWashing: List<LifeDescription>,
    val ultraviolet: List<LifeDescription>,val dressing: List<LifeDescription>)

    data class LifeDescription(val desc: String)
}