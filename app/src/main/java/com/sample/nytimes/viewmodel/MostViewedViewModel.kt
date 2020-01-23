package com.sample.nytimes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.nytimes.model.MostViewed
import com.sample.nytimes.repository.MostViewedRepository

class MostViewedViewModel: ViewModel() {

    fun getMostViewedItems(): LiveData<MostViewed>{

        return MostViewedRepository().getMostViewedNyTimes()
    }

    fun getIsLoading(): LiveData<Boolean> {
        return MostViewedRepository().getIsLoading()
    }

}