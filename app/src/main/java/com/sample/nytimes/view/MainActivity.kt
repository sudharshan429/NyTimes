package com.sample.nytimes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.nytimes.R
import com.sample.nytimes.adapters.MostViewedAdapter
import com.sample.nytimes.api.NetworkComponent
import com.sample.nytimes.model.MostViewed
import com.sample.nytimes.viewmodel.MostViewedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mostViewedViewModel:MostViewedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mostViewedViewModel = ViewModelProvider(this).get(MostViewedViewModel::class.java)

        populateMostViewedItems()

    }

    private fun populateMostViewedItems(){

        if(NetworkComponent().isConnected(this)){
            retry.visibility = View.GONE
            mostViewedViewModel.getMostViewedItems().observe(this, Observer { it -> prepareRecyclerView(it) })
            mostViewedViewModel.getIsLoading().observe(this, Observer { visible ->
                if(visible){
                    progressBar.visibility = View.VISIBLE
                }})
        }else {
            Toast.makeText(this,"Please check your connection and retry",Toast.LENGTH_SHORT).show()
            retry.visibility = View.VISIBLE
            retry.setOnClickListener { populateMostViewedItems() }
        }

    }

    private fun prepareRecyclerView(mostViewed: MostViewed){

        progressBar.visibility = View.GONE
        most_viewed_list.layoutManager = LinearLayoutManager(this)
        most_viewed_list.adapter = MostViewedAdapter(mostViewed.results)
    }

}
