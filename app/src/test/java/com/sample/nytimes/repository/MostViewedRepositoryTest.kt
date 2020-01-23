package com.sample.nytimes.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sample.nytimes.api.MostViewedApi
import com.sample.nytimes.model.MostViewed
import com.sample.nytimes.viewmodel.MostViewedViewModel
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import junit.framework.TestCase.assertNotNull
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MostViewedRepositoryTest {

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    var observer: Observer<MostViewed> = mock()

    private lateinit var viewModel: MostViewedViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        viewModel = MostViewedViewModel()
        viewModel.getMostViewedItems().observeForever(observer)
    }

    @Test
    fun getMostViewedNyTimes() {
        assertNotNull(MostViewedRepository().getMostViewedNyTimes())
    }

    @Test
    fun getIsLoading() {
        assertNotNull(MostViewedRepository().getIsLoading())
    }

}