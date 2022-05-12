package com.example.monsterseeker.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.dtos.NewMonster
import com.example.monsterseeker.models.ListMonster
import com.example.monsterseeker.services.MonsterService
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class ListMonsterRepository @Inject constructor(
    private val monsterService: MonsterService,
    private val monsterDao: MonsterDao
){
    private var dataSet : List<ListMonster> = listOf()

    //TODO: Dao
    fun getDataSet(): MutableLiveData<List<ListMonster>> {
        setDataSet(
            onStart = {},
            onCompletion = {},
            onError = { "Setting DataSet failed" }
        )

        val data : MutableLiveData<List<ListMonster>> = MutableLiveData()

        data.value = dataSet
        return data
    }

    @WorkerThread
    private fun setDataSet(
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
    fun addToDataSet(
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
    fun deleteFromDataSet(
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
    fun modifyInDataSet(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit,
        monsterName: String
    ) = flow {
        monsterService.favouriteMonster(monsterName)
            .suspendOnSuccess{
                emit(data) }.onFailure { onError(this) }

    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

    private fun mockDataSet() {
        val newDataSet = arrayListOf<ListMonster>()

        newDataSet.add(ListMonster("Test 1", false))
        newDataSet.add(ListMonster("Test 2", false))
        newDataSet.add(ListMonster("Test 3", false))
        newDataSet.add(ListMonster("Test 4", false))

        dataSet = newDataSet.toList()
    }
}