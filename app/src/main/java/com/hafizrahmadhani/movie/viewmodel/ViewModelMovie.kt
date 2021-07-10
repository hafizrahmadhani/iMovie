package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hafizrahmadhani.movie.data.BankData
import com.hafizrahmadhani.movie.datamodel.DataModelMovieDetail

class ViewModelMovie : ViewModel() {
    private val listMovie = MutableLiveData<ArrayList<DataModelMovieDetail>>()

    fun callData(){
        listMovie.postValue(BankData.dataMovie())
    }

    fun takeData() : LiveData<ArrayList<DataModelMovieDetail>>{
        return listMovie
    }
}


