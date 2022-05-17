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

    //Idk
    fun loadListMonsters() {
        viewModelScope.launch()
        {
            listMonsters = repository.getMonsterData()
        }
    }

    //Bad request
    fun addListMonster(newMonster: NewMonster) {
        viewModelScope.launch()
        {
            repository.addMonster(
                newMonster
            )
        }
    }

    fun deleteListMonster(name: String) {
        viewModelScope.launch()
        {
            repository.deleteMonster(
                name
            )
        }
    }

    fun favouriteListMonster(name: String) {
        viewModelScope.launch()
        {
            repository.favouriteMonster(
                name
            )
        }
    }
}