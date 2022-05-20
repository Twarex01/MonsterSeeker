package com.example.monsterseeker.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MonsterEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String,
    val favourite: Boolean
) {
    companion object {

        fun mock() = MonsterEntity(
            id = 1,
            name = "Monster",
            description = "Super scary monster",
            favourite = false
        )
    }
}