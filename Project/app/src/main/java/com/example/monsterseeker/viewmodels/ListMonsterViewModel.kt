package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monsterseeker.models.ListMonster

public class ListMonsterViewModel : ViewModel() {
    private val listMonsters: MutableLiveData<List<ListMonster>> by lazy {
        MutableLiveData<List<ListMonster>>().also {
            loadListMonsters()
        }
    }

    fun getDetailedMonsters(): LiveData<List<ListMonster>> {
        return listMonsters
    }

    private fun loadListMonsters() {
    }
}