package com.example.monsterseeker.repositories

import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.models.DetailedMonster
import com.example.monsterseeker.services.MonsterService
import javax.inject.Inject

class DetailedMonsterRepository @Inject constructor(
    private val monsterService: MonsterService,
    private val monsterDao: MonsterDao
){
    //Init?

    companion object{
        private var instance : DetailedMonsterRepository? = null

        fun getInstance(): DetailedMonsterRepository {
            return instance as DetailedMonsterRepository
        }
    }

    private var dataSet : ArrayList<DetailedMonster> = arrayListOf()

    fun getDataSet(): MutableLiveData<List<DetailedMonster>> {
        setDataSet()
        val data : MutableLiveData<List<DetailedMonster>> = MutableLiveData()

        data.value = dataSet
        return data
    }

    private fun setDataSet() {
        //TODO
    }

}