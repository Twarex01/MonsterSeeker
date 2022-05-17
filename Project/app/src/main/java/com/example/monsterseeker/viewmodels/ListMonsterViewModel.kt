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

    private fun <T> MutableLiveData<T>.trigger() {
        value = value
    }

    fun getListMonsters(): LiveData<List<ListMonster>> {
        return listMonsters
    }

    fun loadListMonsters() {
        viewModelScope.launch()
        {
            listMonsters = repository.getMonsterData()
            listMonsters.trigger()
        }
    }

    fun addListMonster(newMonster: NewMonster) {
        viewModelScope.launch()
        {
            repository.addMonster(
                newMonster
            )

            listMonsters.trigger()
        }
    }

    fun deleteListMonster(name: String) {
        viewModelScope.launch()
        {
            repository.deleteMonster(
                name
            )

            listMonsters.trigger()
        }
    }

    fun favouriteListMonster(name: String) {
        viewModelScope.launch()
        {
            repository.favouriteMonster(
                name
            )

            listMonsters.trigger()
        }
    }
}