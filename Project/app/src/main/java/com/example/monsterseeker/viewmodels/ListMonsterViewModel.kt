package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.repositories.ListMonsterRepository

class ListMonsterViewModel : ViewModel() {
    private lateinit var detailedMonsters: MutableLiveData<List<ListMonster>>
    private lateinit var repository : ListMonsterRepository

    fun getListMonsters(): LiveData<List<ListMonster>> {
        return detailedMonsters
    }

    fun loadListMonsters() {
        if(this::detailedMonsters.isInitialized)
            return
        repository = ListMonsterRepository.getInstance()
        detailedMonsters = repository.getDataSet()
    }
}