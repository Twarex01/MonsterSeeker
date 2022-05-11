package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.repositories.ListMonsterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListMonsterViewModel @Inject constructor(
    listMonsterRepository: ListMonsterRepository
) : ViewModel() {
    private lateinit var detailedMonsters: MutableLiveData<List<ListMonster>>

    private var repository : ListMonsterRepository = listMonsterRepository

    fun getListMonsters(): LiveData<List<ListMonster>> {
        return detailedMonsters
    }

    fun loadListMonsters() {
        if(this::detailedMonsters.isInitialized)
            return
        detailedMonsters = repository.getDataSet()
    }
}