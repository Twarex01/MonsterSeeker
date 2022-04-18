package com.example.monsterseeker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.models.DetailedMonster

public class DetailedMonsterViewModel (
    private val mDetailedMonsters : MutableLiveData<List<DetailedMonster>>,
    public val getDetailedMonsters : LiveData<List<DetailedMonster>> = mDetailedMonsters
)