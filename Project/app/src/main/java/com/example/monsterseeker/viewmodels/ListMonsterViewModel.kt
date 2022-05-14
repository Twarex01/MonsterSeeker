package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterseeker.dtos.NewMonster
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.repositories.ListMonsterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListMonsterViewModel @Inject constructor(
    listMonsterRepository: ListMonsterRepository
) : ViewModel() {
    private lateinit var listMonsters: MutableLiveData<List<ListMonster>>

    private var repository : ListMonsterRepository = listMonsterRepository

    fun getListMonsters(): LiveData<List<ListMonster>> {
        return listMonsters
    }

    fun loadListMonsters() {
        if(this::listMonsters.isInitialized)
            return
        listMonsters = repository.getMonsterData()
    }

    fun addListMonster(newMonster: NewMonster) {
        repository.addMonster(
            newMonster
        )
    }

    fun deleteListMonster(name: String) {
        repository.deleteMonster(
            name
        )
    }

    fun favouriteListMonster(name: String) {
        repository.favouriteMonster(
            name
        )
    }
}