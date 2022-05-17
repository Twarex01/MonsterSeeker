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
    private var data : DetailedMonster? = null

    fun getMonsterByName(name : String): MutableLiveData<DetailedMonster> {
        setMonsterByName(name)

        val mutableData : MutableLiveData<DetailedMonster> = MutableLiveData()

        mutableData.value = data
        return mutableData
    }

    @WorkerThread
    private fun setMonsterByName(name : String) = flow {
        var daoMonster = monsterDao.getByName(name)

        if(daoMonster.isEmpty())
        {
            val monster = monsterService.getMonster(name)
            emit(monster)
        }
        else
        {
            emit(daoMonster[0])
        }
    }.flowOn(Dispatchers.IO)

    private fun mockMonsterDetails(name : String) {
        data = DetailedMonster(name, "Description")
    }

}