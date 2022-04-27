package com.example.cryptocurrencytable.di

import com.example.cryptocurrencytable.data.Repository
import com.example.cryptocurrencytable.usecase.ExchangesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideExchangesUseCase(repo: Repository): ExchangesUseCase {
        return repo
    }

}