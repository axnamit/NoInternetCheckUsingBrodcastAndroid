package com.example.autonetworkchange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
object  Utils {

private val booleanMutableLiveData = MutableLiveData<Boolean>()
    fun setNetBool(booleanMutableLiveData: Boolean?) {
        this.booleanMutableLiveData.value = booleanMutableLiveData
    }

    fun getNetBool(): LiveData<Boolean?>? {
        return booleanMutableLiveData
    }
}
