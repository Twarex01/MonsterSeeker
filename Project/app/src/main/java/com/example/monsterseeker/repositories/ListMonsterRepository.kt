package com.example.monsterseeker.repositories

import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.database.MonsterEntity
import com.example.monsterseeker.dtos.NewMonster
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.services.MonsterService
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

        if(listMonsters.isEmpty()) {
            try{
                val monsterResponse = monsterService.getMonsters()
                dataSet = monsterResponse
            }
            catch(e : Exception){

            }
        } else {
            dataSet = listMonsters
        }
    }

    suspend fun addMonster(newMonster: NewMonster) {
        var monsterList : ArrayList<MonsterEntity> = arrayListOf()

        var monster = MonsterEntity(name = newMonster.name,
            description = newMonster.description,
            favourite = false)

        monsterList.add(monster)

        monsterDao.insertAll(monsterList)

        try {
            monsterService.addMonster(newMonster)
        }
        catch(e : Exception){

        }
    }

    suspend fun deleteMonster(monsterName: String) {
        monsterDao.delete(monsterName)

        try {
            monsterService.removeMonster(monsterName)
        }
        catch(e : Exception){

        }
    }

    suspend fun favouriteMonster(monsterName: String) {
        var monster = monsterDao.getByName(monsterName)[0]

        var modifiedMonster = MonsterEntity(monster.id, monster.name, monster.description, !monster.favourite)

        monsterDao.updateMonster(modifiedMonster)

        try {
            monsterService.favouriteMonster(monsterName)
        }
        catch(e : Exception){

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