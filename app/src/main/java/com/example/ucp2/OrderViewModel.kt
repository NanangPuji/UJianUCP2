package com.example.ucp2

import androidx.lifecycle.ViewModel
import com.example.ucp2.ui.data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel : ViewModel(){
    private val _stateUI = MutableStateFlow(DataForm())
    val stateUI: StateFlow<DataForm> = _stateUI.asStateFlow()

    fun setC(listContact: MutableList<String>) {
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                nama = listContact[0],
                nim = listContact[1],
                konsentrasi = listContact[2],
                judul = listContact[3]
            )
        }
    }

    fun setD1(pilih: String){
        _stateUI.update { currentState -> currentState.copy(dosen = pilih)}
    }

    fun setD2(pilih: String){
        _stateUI.update { currentState -> currentState.copy(dosen = pilih)}
    }


}