package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterseeker.models.DetailedMonster
import com.example.monsterseeker.repositories.DetailedMonsterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailedMonsterViewModel @Inject constructor(
    detailedMonsterRepository: DetailedMonsterRepository
) : ViewModel() {

    private lateinit var detailedMonster: MutableLiveData<DetailedMonster>

    private var repository : DetailedMonsterRepository = detailedMonsterRepository

    fun getDetailedMonster(): LiveData<DetailedMonster> {
        return detailedMonster
    }

    fun loadDetailedMonster(name : String) {
        if(this::detailedMonster.isInitialized)
            return
        detailedMonster = repository.getMonsterByName(name)
    }
}