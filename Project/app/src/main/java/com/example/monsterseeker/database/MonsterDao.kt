package com.example.monsterseeker.database

import androidx.room.*

@Dao
interface MonsterDao {

    @Query("SELECT * FROM MonsterEntity")
    fun getAll(): List<MonsterEntity>

    @Query("SELECT * FROM MonsterEntity WHERE id = :id_ ")
    fun getById(id_: Long): List<MonsterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg monsters: MonsterEntity)

    @Delete()
    fun delete(monsters: MonsterEntity)
}