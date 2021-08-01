package com.umbrella.simplepattern.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.umbrella.simplepattern.model.SomeObject
import com.umbrella.simplepattern.model.database.ObjectsDatabase
import com.umbrella.simplepattern.model.network.RetroInstance
import com.umbrella.simplepattern.model.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val database = ObjectsDatabase.getInstance(application)
    private val networkLiveData = MutableLiveData<List<SomeObject>>()
    private val databaseLiveData = MutableLiveData<List<SomeObject>>()


    fun getNetworkLiveData(): LiveData<List<SomeObject>> {
        return networkLiveData
    }

    fun getDatabaseLiveData(): LiveData<List<SomeObject>> {
        return databaseLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("parameter1", "parameter2")
            networkLiveData.postValue(response)
        }
    }

    fun makeDatabaseCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = database.objectDao().getAllObjects()
            databaseLiveData.postValue(response)
        }
    }

    fun deleteAllObjectsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            database.objectDao().deleteAllObjects()
        }
    }

    fun insertObjectFromDB(someObject: SomeObject) {
        viewModelScope.launch(Dispatchers.IO) {
            database.objectDao().insertObject(someObject)
        }
    }

    fun deleteObjectFromDB(someObject: SomeObject) {
        viewModelScope.launch(Dispatchers.IO) {
            database.objectDao().deleteObject(someObject)
        }
    }
}