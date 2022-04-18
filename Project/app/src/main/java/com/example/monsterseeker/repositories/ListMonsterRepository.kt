package com.example.monsterseeker.repositories

import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.models.ListMonster

class ListMonsterRepository() {
    companion object{
        private var instance : ListMonsterRepository? = null

        fun getInstance(): ListMonsterRepository {
            if(instance == null)
            {
                instance = ListMonsterRepository()
            }
            return instance as ListMonsterRepository
        }
    }

    private var dataSet : ArrayList<ListMonster> = arrayListOf()

    fun getDataSet(): MutableLiveData<List<ListMonster>> {
        setDataSet()
        val data : MutableLiveData<List<ListMonster>> = MutableLiveData()

        data.value = dataSet
        return data
    }

    private fun setDataSet() {
        //TODO
    }
}