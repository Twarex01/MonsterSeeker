package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.repositories.ListMonsterRepository

class ListMonsterViewModel : ViewModel() {
    private var detailedMonsters: MutableLiveData<List<ListMonster>>? = null

    private var repository : ListMonsterRepository? = null

    fun getListMonsters(): LiveData<List<ListMonster>>? {
        return detailedMonsters
    }

    private fun loadListMonsters() {
        if(detailedMonsters != null)
            return
        repository = ListMonsterRepository.getInstance()
        detailedMonsters = repository!!.getDataSet()
    }
}