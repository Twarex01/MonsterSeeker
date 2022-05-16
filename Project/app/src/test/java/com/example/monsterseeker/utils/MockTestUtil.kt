package com.example.monsterseeker.utils

import com.example.monsterseeker.database.MonsterEntity

object MockTestUtil {

    fun mockMonsterList() = listOf(MonsterEntity.mock())
}
