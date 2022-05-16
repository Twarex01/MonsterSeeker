package com.example.monsterseeker.persistence

import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.database.MonsterEntity
import com.example.monsterseeker.utils.MockTestUtil.mockMonsterList
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class MonsterDaoTest : LocalDatabase() {

    private lateinit var monsterDao: MonsterDao

    @Before
    fun init() {
        monsterDao = db.monsterDao()
    }

    @Test
    fun insertAndLoadMonsters() = runBlocking {
        val mockDataList = mockMonsterList()
        monsterDao.insertAll(mockDataList)

        val loadFromDB = monsterDao.getAll()
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = MonsterEntity.mock()
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }

    @Test
    fun deleteMonster() = runBlocking {
        val mockDataList = mockMonsterList()
        monsterDao.insertAll(mockDataList)

        val mockData = MonsterEntity.mock()

        val count1 = monsterDao.getAll().count()

        monsterDao.delete(mockData.name)

        val count2 = monsterDao.getAll().count()

        assertThat(count1, `is`(count2 + 1))
    }

    @Test
    fun selectMontser() = runBlocking {
        val mockDataList = mockMonsterList()
        monsterDao.insertAll(mockDataList)

        val mockData = MonsterEntity.mock()

        val monster = monsterDao.getByName(mockData.name)

        assertThat(monster[0].toString(), `is`(mockData.toString()))
    }

    @Test
    fun updateMonster() = runBlocking {
        val mockDataList = mockMonsterList()
        monsterDao.insertAll(mockDataList)

        val mockData = MonsterEntity.mock()

        var modifiedMonster = MonsterEntity(mockData.id, mockData.name, mockData.description, !mockData.favourite)

        monsterDao.updateMonster(modifiedMonster)

        val monster = monsterDao.getByName(mockData.name)

        assertThat(monster[0].toString(), `is`(`not`(mockData.toString())))
        assertThat(monster[0].toString(), `is`(modifiedMonster.toString()))
    }

}
