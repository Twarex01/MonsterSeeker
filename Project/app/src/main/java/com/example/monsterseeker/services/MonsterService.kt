package com.example.monsterseeker.services

import com.example.monsterseeker.database.MonsterEntity
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface  MonsterService {
    //TODO: Backend még nincs hozzá -> Változhat

    @GET("TODO")
    suspend fun fetchMonsterList(): ApiResponse<List<MonsterEntity>>
}