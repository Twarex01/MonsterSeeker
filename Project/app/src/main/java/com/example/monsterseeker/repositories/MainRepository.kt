package com.example.monsterseeker.repositories

import androidx.annotation.WorkerThread
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.database.MonsterEntity
import com.example.monsterseeker.services.MonsterService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val monsterService: MonsterService,
    private val monsterDao: MonsterDao
){

    suspend fun fetchMonsters() {
        val monsters: List<MonsterEntity> = monsterDao.getAll()
        if (monsters.isEmpty()) {

            when(val monsterResponse = monsterService.fetchMonsterList())
            {
                is ApiResponse.Success -> {
                    monsterDao.insertAll(monsterResponse.data)
                }

                is ApiResponse.Failure.Error -> {
                    return
                }
            }
        }
    }
}