package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.models.ListMonster

public class ListMonsterViewModel(
    private val listMonsters : MutableLiveData<List<ListMonster>>
) {
    fun getListMonsters(): LiveData<List<ListMonster>> {
        return listMonsters
    }
}