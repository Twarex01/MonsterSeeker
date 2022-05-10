package com.example.monsterseeker.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MonsterEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val favourite: Boolean

    //MockObject?
)