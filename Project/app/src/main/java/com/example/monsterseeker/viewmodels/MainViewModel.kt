package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monsterseeker.database.MonsterEntity
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {

    var repository = mainRepository

    fun fetchMonsters() {
        viewModelScope.launch()
        {
            repository.fetchMonsters()
        }
    }
}


