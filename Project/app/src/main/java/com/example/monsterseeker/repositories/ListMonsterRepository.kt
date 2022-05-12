package com.example.monsterseeker.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.dtos.NewMonster
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.services.MonsterService
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ListMonsterRepository @Inject constructor(
    private val monsterService: MonsterService,
    private val monsterDao: MonsterDao
){
    private lateinit var dataSet : List<ListMonster>

    //TODO: Dao
    fun getMonsterData(): MutableLiveData<List<ListMonster>> {
        //setDataSet(
        //    onStart = {},
        //    onCompletion = {},
        //    onError = { "Setting DataSet failed" }
        //)

        mockMonsterData()

        val mutableData : MutableLiveData<List<ListMonster>> = MutableLiveData()

        mutableData.value = dataSet
        return mutableData
    }

    @WorkerThread
    private fun setMonsterData(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        monsterService.getMonsters()
            .suspendOnSuccess{
                dataSet = data
                emit(data) }.onFailure { onError(this) }

    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun addMonster(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit,
        newMonster: NewMonster
    ) = flow {
        monsterService.addMonster(newMonster)
            .suspendOnSuccess{
                emit(data) }.onFailure { onError(this) }

    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun deleteMonster(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit,
        monsterName: String
    ) = flow {
        monsterService.removeMonster(monsterName)
            .suspendOnSuccess{
                emit(data) }.onFailure { onError(this) }

    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun favouriteMonster(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit,
        monsterName: String
    ) = flow {
        monsterService.favouriteMonster(monsterName)
            .suspendOnSuccess{
                emit(data) }.onFailure { onError(this) }

    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    private fun mockMonsterData() {
        val newDataSet = arrayListOf<ListMonster>()

        newDataSet.add(ListMonster("Test1", false))
        newDataSet.add(ListMonster("Test2", false))
        newDataSet.add(ListMonster("Test3", false))
        newDataSet.add(ListMonster("Test4", false))

        dataSet = newDataSet.toList()
    }
}