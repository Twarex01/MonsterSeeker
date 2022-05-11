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
    private var dataSet : ArrayList<ListMonster> = arrayListOf()

    fun getDataSet(): MutableLiveData<List<ListMonster>> {
        setDataSet()
        val data : MutableLiveData<List<ListMonster>> = MutableLiveData()

        data.value = dataSet
        return data
    }

    //TODO: Network call
    private fun setDataSet() {
        mockDataSet()
    }

    private fun mockDataSet() {
        dataSet.add(ListMonster("Test 1", false))
        dataSet.add(ListMonster("Test 2", false))
        dataSet.add(ListMonster("Test 3", false))
        dataSet.add(ListMonster("Test 4", false))
    }
}