package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.models.DetailedMonster

public class DetailedMonsterViewModel (
    private val detailedMonsters : MutableLiveData<List<DetailedMonster>>
) {
    fun getDetailedMonsters(): LiveData<List<DetailedMonster>> {
        return detailedMonsters
    }
}