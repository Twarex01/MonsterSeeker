package com.example.monsterseeker.repositories

import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.models.DetailedMonster

class DetailedMonsterRepository {
    companion object{
        private var instance : DetailedMonsterRepository? = null

        fun getInstance(): DetailedMonsterRepository {
            if(instance == null)
            {
                instance = DetailedMonsterRepository()
            }
            return instance as DetailedMonsterRepository
        }
    }

    private var dataSet : ArrayList<DetailedMonster> = arrayListOf()

    fun getDataSet(): MutableLiveData<List<DetailedMonster>> {
        setDataSet()
        val data : MutableLiveData<List<DetailedMonster>> = MutableLiveData()

        data.value = dataSet
        return data
    }

    private fun setDataSet() {
        //TODO
    }

}