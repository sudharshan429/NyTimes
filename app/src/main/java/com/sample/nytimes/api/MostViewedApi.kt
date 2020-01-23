package com.sample.nytimes.api

import com.sample.nytimes.model.MostViewed
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface MostViewedApi {

    @GET("1.json?api-key=VOo18hvzN9ZIjBhV9Qc0ZMWXywWg95Ym")
    fun getMostViewed(): Observable<MostViewed>
}