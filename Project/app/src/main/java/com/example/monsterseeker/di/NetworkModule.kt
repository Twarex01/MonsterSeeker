package com.example.monsterseeker.di

import com.example.monsterseeker.services.MonsterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(
                "TODO"
            )
            .build()
    }
    //Factory?

    @Provides
    @Singleton
    fun provideMonserSeekerService(retrofit: Retrofit): MonsterService {
        return retrofit.create(MonsterService::class.java)
    }
}