package com.example.monsterseeker.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.models.DetailedMonster
import com.example.monsterseeker.services.MonsterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailedMonsterRepository @Inject constructor(
    private val monsterService: MonsterService,
    private val monsterDao: MonsterDao
){
    private lateinit var data : DetailedMonster

    fun getMonsterByName(name : String): MutableLiveData<DetailedMonster> {
        //setMonsterByName(
        //)

        mockMonsterDetails(name)

        val mutableData : MutableLiveData<DetailedMonster> = MutableLiveData()

        mutableData.value = data
        return mutableData
    }

    @WorkerThread
    private fun setMonsterByName(name : String) = flow {
        val monster = monsterService.getMonster(name)
        emit(monster)
    }.flowOn(Dispatchers.IO)

    private fun mockMonsterDetails(name : String) {
        data = DetailedMonster(name, "Description")
    }

}