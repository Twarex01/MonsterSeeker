package com.example.monsterseeker.di

import android.content.Context
import coil.util.CoilUtils
import com.example.monsterseeker.services.MonsterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(CoilUtils.createDefaultCache(context))
            .build()
    }

    //TODO: Interceptor?

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(
                "https://localhost:7203/"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMonserSeekerService(retrofit: Retrofit): MonsterService {
        return retrofit.create(MonsterService::class.java)
    }
}