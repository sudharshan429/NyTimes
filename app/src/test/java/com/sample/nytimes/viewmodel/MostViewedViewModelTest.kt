package com.sample.nytimes.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sample.nytimes.model.MostViewed
import com.sample.nytimes.repository.MostViewedRepository
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.mockito.Mockito.`when`


@RunWith(JUnit4::class)
class MostViewedViewModelTest {

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

    @Mock
    lateinit var mostViewedRepository: MostViewedRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    var mostViewed: MutableLiveData<MostViewed> = mock()

    private lateinit var viewModel: MostViewedViewModel


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        mostViewedRepository = Mockito.mock(MostViewedRepository::class.java)
        viewModel = MostViewedViewModel()

    }

    @Test
    fun getMostViewedItems() {

        `when`(mostViewedRepository.getMostViewedNyTimes()).thenReturn(mostViewed)
        assertNotNull(MostViewedViewModel().getMostViewedItems())
    }

    @Test
    fun getIsLoading() {

        assertNotNull(MostViewedViewModel().getIsLoading())
    }
}