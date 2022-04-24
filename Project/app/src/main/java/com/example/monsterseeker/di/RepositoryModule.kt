package com.example.monsterseeker.di

import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.repositories.DetailedMonsterRepository
import com.example.monsterseeker.repositories.ListMonsterRepository
import com.example.monsterseeker.services.MonsterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideDetailedMonsterRepository(
        monsterService: MonsterService,
        monsterDao: MonsterDao
    ): DetailedMonsterRepository {
        return DetailedMonsterRepository(monsterService, monsterDao)
    }

    @Provides
    @ViewModelScoped
    fun provideListMonsterRepository(
        monsterService: MonsterService,
        monsterDao: MonsterDao
    ): ListMonsterRepository {
        return ListMonsterRepository(monsterService, monsterDao)
    }
}