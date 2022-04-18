package com.example.monsterseeker.database

//@Database(entities = [MonsterEntity::class], version = 1), RoomDatabase()
abstract class MonsterDatabase  {
    abstract fun monsterDao(): MonsterDao
}