package com.hafizrahmadhani.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hafizrahmadhani.movie.data.BankData
import com.hafizrahmadhani.movie.datamodel.DataModelMovieDetail

class ViewModelTvShow : ViewModel() {
    private val listTvShow = MutableLiveData<ArrayList<DataModelMovieDetail>>()

    fun callData(){
        listTvShow.postValue(BankData.dataTvShow())
    }

    fun takeData() : LiveData<ArrayList<DataModelMovieDetail>> {
        return listTvShow
    }
}