package com.example.monsterseeker.viewmodels

import androidx.lifecycle.ViewModel
import com.example.monsterseeker.database.MonsterEntity
import com.example.monsterseeker.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {

    val monsterList: Flow<List<MonsterEntity>> =
        mainRepository.fetchMonsters()

}
