package com.example.monsterseeker.di

import android.app.Application
import androidx.room.Room
import com.example.monsterseeker.R
import com.example.monsterseeker.database.MonsterDao
import com.example.monsterseeker.database.MonsterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): MonsterDatabase {
        return Room
            .databaseBuilder(
                application,
                MonsterDatabase::class.java,
                application.getString(R.string.database)
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMonsterDao(appDatabase: MonsterDatabase): MonsterDao {
        return appDatabase.monsterDao()
    }

}