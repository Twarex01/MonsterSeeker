package com.example.monsterseeker.repositories

import androidx.annotation.WorkerThread
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.database.MonsterEntity
import com.example.monsterseeker.services.MonsterService
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

    @WorkerThread
    fun fetchMonsters(
    ) = flow {
        val monsters: List<MonsterEntity> = monsterDao.getAll()
        if (monsters.isEmpty()) {
            monsterService.fetchMonsterList()
                .suspendOnSuccess {
                    monsterDao.insertAll(data)
                    emit(data)
                }
                .onFailure { throw(Exception(this)) }
        } else {
            emit(monsters)
        }
    }.flowOn(Dispatchers.IO)

}