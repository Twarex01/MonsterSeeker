package com.example.monsterseeker.services

import com.example.monsterseeker.database.MonsterEntity
import com.example.monsterseeker.dtos.NewMonster
import com.example.monsterseeker.models.DetailedMonster
import com.example.monsterseeker.models.ListMonster
import com.skydoves.sandwich.ApiResponse
import okhttp3.ResponseBody
import retrofit2.http.*

interface  MonsterService {
    @GET("/Monsters/fetch")
    suspend fun fetchMonsterList(): ApiResponse<List<MonsterEntity>>

    @GET("/Monsters")
    suspend fun getMonsters(): ApiResponse<List<ListMonster>>

    @GET("/Monsters/{name}")
    suspend fun getMonster(@Path(value = "name") name : String): ApiResponse<DetailedMonster>

    @PUT("/Monsters/{name}/favourite")
    suspend fun favouriteMonster(@Path(value = "name") name : String): ApiResponse<ResponseBody>

    @POST("/Monsters")
    suspend fun addMonster(@Body newMonster : NewMonster): ApiResponse<ResponseBody>

    @DELETE("/Monsters/{name}")
    suspend fun removeMonster(@Path(value = "name") name : String): ApiResponse<ResponseBody>
}