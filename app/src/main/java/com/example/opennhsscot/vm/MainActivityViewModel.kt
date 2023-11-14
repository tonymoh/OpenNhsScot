package com.example.opennhsscot.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opennhsscot.model.Field
import com.example.opennhsscot.model.GpData
import com.example.opennhsscot.model.Record
import com.example.opennhsscot.model.Result
import com.example.opennhsscot.repo.GpDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val gpDataRepository: GpDataRepository
): ViewModel(){

    private val _uiState = MutableStateFlow(GpData("null",
        result = Result(
            listOf(Field("","")),
            listOf(
                Record("","","","","","","","","",
                "","","","","","","",0)
            )
            ,""),
        false))

    val uiState: StateFlow<GpData> = _uiState.asStateFlow()

    fun fetchGpData() = viewModelScope.launch {
        _uiState.value = gpDataRepository.getGpData()
    }

    fun fetchGpDataSearch(practiceListSize: String){
        viewModelScope.launch {
            try {
                _uiState.value = gpDataRepository.getGpDataSearch(practiceListSize)
            } catch (e: Exception) {
                Log.d("Log Err from View Model",e.toString())
            }
        }}

}