package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monsterseeker.dtos.NewMonster
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.repositories.ListMonsterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListMonsterViewModel @Inject constructor(
    listMonsterRepository: ListMonsterRepository
) : ViewModel() {
    private var listMonsters: MutableLiveData<List<ListMonster>> = MutableLiveData()

    private var repository : ListMonsterRepository = listMonsterRepository

    fun getListMonsters(): LiveData<List<ListMonster>> {
        return listMonsters
    }

    fun loadListMonsters() {
        viewModelScope.launch()
        {
            var monsterData = repository.getMonsterData()
            listMonsters.value = monsterData
        }
    }

    fun addListMonster(newMonster: NewMonster) {
        viewModelScope.launch()
        {
            repository.addMonster(
                newMonster
            )

            var monsterData = repository.getMonsterData()
            listMonsters.value = monsterData
        }
    }

    fun deleteListMonster(name: String) {
        viewModelScope.launch()
        {
            repository.deleteMonster(
                name
            )

            var monsterData = repository.getMonsterData()
            listMonsters.value = monsterData
        }
    }

    fun favouriteListMonster(name: String) {
        viewModelScope.launch()
        {
            repository.favouriteMonster(
                name
            )

            var monsterData = repository.getMonsterData()
            listMonsters.value = monsterData
        }
    }
}