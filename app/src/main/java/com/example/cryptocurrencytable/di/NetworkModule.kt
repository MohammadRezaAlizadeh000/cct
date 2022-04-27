package com.example.cryptocurrencytable.di

import com.example.cryptocurrencytable.data.RemoteDataSourceHandler
import com.example.cryptocurrencytable.data.RemoteDataSourceHandlerImpl
import com.example.cryptocurrencytable.data.network.CCTAPIService
import com.example.cryptocurrencytable.utils.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE)
                .addHeader(HEADER_AUTHENTICATION_TOKEN, AUTHENTICATION_TOKEN)
                .build()
            chain.proceed(request)
        }
        return okHttpClient.readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConvertorFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        convertorFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(convertorFactory)
            .build()
    }

    @Provides
    @Singleton
    fun getCCTAPIService(retrofit: Retrofit): CCTAPIService {
        return retrofit.create(CCTAPIService::class.java)
    }

    @Provides
    @Singleton
    fun getRemoteDataSourceHandler(): RemoteDataSourceHandler {
        return RemoteDataSourceHandlerImpl()
    }
}