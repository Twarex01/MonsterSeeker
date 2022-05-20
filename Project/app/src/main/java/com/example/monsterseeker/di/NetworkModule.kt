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
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(provideUnsafeOkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(provideUnsafeOkHttpClient)
            .baseUrl(
                "https://localhost:7203/"
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMonserSeekerService(retrofit: Retrofit): MonsterService {
        return retrofit.create(MonsterService::class.java)
    }

    @Provides
    @Singleton
    fun provideUnsafeOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            // builder.hostnameVerifier { _, _ -> true }
            builder.hostnameVerifier ( hostnameVerifier = HostnameVerifier{ _, _ -> true })


            return builder
                .connectTimeout(20L, TimeUnit.SECONDS)
                .writeTimeout  (20L, TimeUnit.SECONDS)
                .readTimeout   (20L, TimeUnit.SECONDS)
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}