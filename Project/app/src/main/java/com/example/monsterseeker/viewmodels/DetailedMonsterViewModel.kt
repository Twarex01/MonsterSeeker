package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monsterseeker.models.DetailedMonster
import com.example.monsterseeker.repositories.DetailedMonsterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedMonsterViewModel @Inject constructor(
    detailedMonsterRepository: DetailedMonsterRepository
) : ViewModel() {

    private var detailedMonster: MutableLiveData<DetailedMonster> = MutableLiveData()

    private var repository : DetailedMonsterRepository = detailedMonsterRepository

    fun getDetailedMonster(): LiveData<DetailedMonster> {
        return detailedMonster
    }

    fun loadDetailedMonster(name : String) {
        viewModelScope.launch()
        {
            var monsterData = repository.getMonsterByName(name)
            detailedMonster.value = monsterData
        }
    }
}