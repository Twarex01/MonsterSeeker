package com.example.monsterseeker.database

import androidx.room.*

@Dao
interface MonsterDao {

    @Query("SELECT * FROM MonsterEntity")
    fun getAll(): List<MonsterEntity>

    @Query("SELECT * FROM MonsterEntity WHERE name = :name_ ")
    fun getByName(name_: String): List<MonsterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(monsters: List<MonsterEntity>)
}