package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.models.ListMonster

public class ListMonsterViewModel(
    private val mListMonsters : MutableLiveData<List<ListMonster>>,
    public val getListMonsters : LiveData<List<ListMonster>> = mListMonsters
)