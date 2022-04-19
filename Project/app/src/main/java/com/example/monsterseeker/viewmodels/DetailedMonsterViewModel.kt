package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterseeker.models.DetailedMonster
import com.example.monsterseeker.repositories.DetailedMonsterRepository

class DetailedMonsterViewModel : ViewModel() {
    private var detailedMonsters: MutableLiveData<List<DetailedMonster>>? = null

    private var repository : DetailedMonsterRepository? = null

    fun getDetailedMonsters(): LiveData<List<DetailedMonster>>? {
        return detailedMonsters
    }

    private fun loadDetailedMonsters() {
        if(detailedMonsters != null)
            return
        repository = DetailedMonsterRepository.getInstance()
        detailedMonsters = repository!!.getDataSet()
    }
}