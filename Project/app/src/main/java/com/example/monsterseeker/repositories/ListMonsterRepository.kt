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

    suspend fun getMonsterData(): List<ListMonster> {
        setMonsterData()

        return dataSet
    }

    private suspend fun setMonsterData(){

        var monsters = monsterDao.getAll()

        var listMonsters : ArrayList<ListMonster> = arrayListOf()

        for (monster in monsters) {
            var listMonster = ListMonster(monster.name, monster.favourite)

            listMonsters.add(listMonster)
        }

        dataSet = if(listMonsters.isEmpty()) {
            val monsterResponse = monsterService.getMonsters()
            monsterResponse
        } else {
            listMonsters
        }
    }

    suspend fun addMonster(newMonster: NewMonster) {
        var monsterList : ArrayList<MonsterEntity> = arrayListOf()

        var monster = MonsterEntity(name = newMonster.name,
            description = newMonster.description,
            favourite = false)

        monsterList.add(monster)

        monsterDao.insertAll(monsterList)
    }

    suspend fun deleteMonster(monsterName: String) {
        monsterDao.delete(monsterName)
    }

    suspend fun favouriteMonster(monsterName: String) {
        var monster = monsterDao.getByName(monsterName)[0]

        var modifiedMonster = MonsterEntity(monster.id, monster.name, monster.description, !monster.favourite)

        monsterDao.updateMonster(modifiedMonster)
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