package com.example.preferencedatastore.Ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.preferencedatastore.Repository.DataStoreSetting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val dataStoreSetting: DataStoreSetting):ViewModel() {

    fun writeToLocal(name:String)=viewModelScope.launch {
        dataStoreSetting.writeToLocal(name)
    }

    val readToLocal=dataStoreSetting.readToLocal
}