package com.example.monsterseeker.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.models.DetailedMonster
import com.example.monsterseeker.services.MonsterService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class DetailedMonsterRepository @Inject constructor(
    private val monsterService: MonsterService,
    private val monsterDao: MonsterDao
){
    private var data : DetailedMonster? = null

    suspend fun getMonsterByName(name : String): MutableLiveData<DetailedMonster> {
        setMonsterByName(name)

        val mutableData : MutableLiveData<DetailedMonster> = MutableLiveData()

        mutableData.value = data
        return mutableData
    }

    private suspend fun setMonsterByName(name : String) {
        var daoMonster = monsterDao.getByName(name)

        if(daoMonster.isEmpty())
        {
            when(val monsterResponse = monsterService.getMonster(name))
            {
                is ApiResponse.Success -> {
                    data = monsterResponse.data
                }

                is ApiResponse.Failure.Error -> {
                    return
                }
            }
        }
        else
        {
            var monster = daoMonster[0]
            data = DetailedMonster(monster.name, monster.description)
        }
    }

    private fun mockMonsterDetails(name : String) {
        data = DetailedMonster(name, "Description")
    }

}