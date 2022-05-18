package com.example.monsterseeker.repositories

import android.util.Log
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.database.MonsterEntity
import com.example.monsterseeker.services.MonsterService
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val monsterService: MonsterService,
    private val monsterDao: MonsterDao
){

    suspend fun fetchMonsters() {
        val monsters: List<MonsterEntity> = monsterDao.getAll()
        if (monsters.isEmpty()) {
            try{
                val monsterResponse = monsterService.fetchMonsterList()
                monsterDao.insertAll(monsterResponse)
            }
            catch(e : Exception){
                Log.d("DebugLog", e.message!!)
            }
        }
    }
}