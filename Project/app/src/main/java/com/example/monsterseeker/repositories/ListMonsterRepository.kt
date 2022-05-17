package com.example.monsterseeker.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.database.MonsterEntity
import com.example.monsterseeker.dtos.NewMonster
import com.example.monsterseeker.models.DetailedMonster
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.services.MonsterService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ListMonsterRepository @Inject constructor(
    private val monsterService: MonsterService,
    private val monsterDao: MonsterDao
){
    private var dataSet : List<ListMonster> = listOf()

    suspend fun getMonsterData(): MutableLiveData<List<ListMonster>> {
        setMonsterData()

        val mutableData : MutableLiveData<List<ListMonster>> = MutableLiveData()

        mutableData.value = dataSet
        return mutableData
    }

    private suspend fun setMonsterData(){

        var monsters = monsterDao.getAll()

        var listMonsters : ArrayList<ListMonster> = arrayListOf()

        for (monster in monsters) {
            var listMonster = ListMonster(monster.name, monster.favourite)

            listMonsters.add(listMonster)
        }

        if(listMonsters.isEmpty())
        {
            //when(val monsterResponse = monsterService.getMonsters())
            //{
            //    is ApiResponse.Success -> {
            //        dataSet = monsterResponse.data
            //    }

            //    is ApiResponse.Failure.Error -> {
            //        return
            //    }
            //}
        }
        else
        {
            dataSet = listMonsters
        }
    }

    suspend fun addMonster(newMonster: NewMonster) {
        when(val monsterResponse = monsterService.addMonster(newMonster))
        {
            is ApiResponse.Success -> {

            }

            is ApiResponse.Failure.Error -> {
                var monsterList : ArrayList<MonsterEntity> = arrayListOf()

                var monster = MonsterEntity(name = newMonster.name,
                    description = newMonster.description,
                    favourite = false)

                monsterList.add(monster)

                monsterDao.insertAll(monsterList)
            }
        }
    }

    suspend fun deleteMonster(monsterName: String) {
        when(val monsterResponse = monsterService.removeMonster(monsterName))
        {
            is ApiResponse.Success -> {

            }

            is ApiResponse.Failure.Error -> {
                monsterDao.delete(monsterName)
            }
        }
    }

    suspend fun favouriteMonster(monsterName: String) {
        var monster = monsterDao.getByName(monsterName)[0]

        var modifiedMonster = MonsterEntity(monster.id, monster.name, monster.description, !monster.favourite)


        when(val monsterResponse = monsterService.favouriteMonster(monsterName))
        {
            is ApiResponse.Success -> {
            }

            is ApiResponse.Failure.Error -> {
                var monsterList : ArrayList<MonsterEntity> = arrayListOf()

                monsterList.add(modifiedMonster)

                monsterDao.insertAll(monsterList)
            }
        }
    }

    private fun mockMonsterData() {
        val newDataSet = arrayListOf<ListMonster>()

        newDataSet.add(ListMonster("Test1", false))
        newDataSet.add(ListMonster("Test2", false))
        newDataSet.add(ListMonster("Test3", false))
        newDataSet.add(ListMonster("Test4", false))

        dataSet = newDataSet.toList()
    }
}