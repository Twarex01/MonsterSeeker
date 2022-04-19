package com.example.monsterseeker.database

//@Dao
interface MonsterDao {
    fun getAll(): List<MonsterEntity>

    fun loadAllByIds(monsterIds: IntArray): List<MonsterEntity>

    fun insertAll(vararg monsters: MonsterEntity)

    fun delete(monsters: MonsterEntity)
}