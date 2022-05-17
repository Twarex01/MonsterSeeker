package com.example.monsterseeker.database

import androidx.room.*

@Dao
interface MonsterDao {

    @Query("SELECT * FROM MonsterEntity")
    suspend fun getAll(): List<MonsterEntity>

    @Query("SELECT * FROM MonsterEntity WHERE name = :name_ ")
    suspend fun getByName(name_: String): List<MonsterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(monsters: List<MonsterEntity>)

    @Query("DELETE FROM MonsterEntity WHERE name = :name_ ")
    suspend fun delete(name_ : String)

    @Update
    suspend fun updateMonster(monster: MonsterEntity)
}