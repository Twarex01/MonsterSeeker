package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterseeker.models.DetailedMonster

public class DetailedMonsterViewModel : ViewModel() {
    private val detailedMonsters: MutableLiveData<List<DetailedMonster>> by lazy {
        MutableLiveData<List<DetailedMonster>>().also {
            loadDetailedMonsters()
        }
    }

    fun getDetailedMonsters(): LiveData<List<DetailedMonster>> {
        return detailedMonsters
    }

    private fun loadDetailedMonsters() {
    }
}