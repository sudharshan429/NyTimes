package com.sample.nytimes.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sample.nytimes.api.MostViewedApi
import com.sample.nytimes.api.NetworkComponent
import com.sample.nytimes.model.MostViewed
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MostViewedRepository {

    private val mostViewedLiveData = MutableLiveData<MostViewed>()
    private val isLoading = MutableLiveData<Boolean>()

    fun getMostViewedNyTimes(): MutableLiveData<MostViewed> {
        isLoading.value = true
        NetworkComponent().getRetrofit().create(MostViewedApi::class.java).getMostViewed()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> onSuccess(result)},
                {error -> onFailure(error)})

        return mostViewedLiveData
    }

    private fun onSuccess(mostViewed: MostViewed){
        mostViewedLiveData.value = mostViewed
        isLoading.value = false
    }

    private fun onFailure(error: Throwable){
        isLoading.value = false
        Log.d("MostViewed Repository","Service Error:${error}")
    }

    fun getIsLoading():MutableLiveData<Boolean>{
        return isLoading
    }
}