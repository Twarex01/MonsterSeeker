package com.example.monsterseeker.repositories

import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.services.MonsterService
import javax.inject.Inject

class ListMonsterRepository @Inject constructor(
    private val monsterService: MonsterService,
    private val monsterDao: MonsterDao
){
    //Init?

    companion object{
        private var instance : ListMonsterRepository? = null

        fun getInstance(): ListMonsterRepository {
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