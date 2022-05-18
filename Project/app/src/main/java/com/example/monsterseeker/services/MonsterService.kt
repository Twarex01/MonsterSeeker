package com.example.monsterseeker.services

import com.example.monsterseeker.database.MonsterEntity
import com.example.monsterseeker.dtos.NewMonster
import com.example.monsterseeker.models.DetailedMonster
import com.example.monsterseeker.models.ListMonster
import com.skydoves.sandwich.ApiResponse
import okhttp3.ResponseBody
import retrofit2.http.*

interface  MonsterService {
    @GET("api/Monsters/fetch")
    suspend fun fetchMonsterList(): List<MonsterEntity>

    @GET("api/Monsters")
    suspend fun getMonsters(): List<ListMonster>

    @GET("api/Monsters/{name}")
    suspend fun getMonster(@Path(value = "name") name : String): DetailedMonster

    @PUT("api/Monsters/{name}/favourite")
    suspend fun favouriteMonster(@Path(value = "name") name : String): ApiResponse<ResponseBody>

    @POST("api/Monsters")
    suspend fun addMonster(@Body newMonster : NewMonster): ApiResponse<ResponseBody>

    @DELETE("api/Monsters/{name}")
    suspend fun removeMonster(@Path(value = "name") name : String): ApiResponse<ResponseBody>
}