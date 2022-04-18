package com.devcommop.joaquin.crptoola.di

import com.devcommop.joaquin.crptoola.common.Constants.BASE_URL
import com.devcommop.joaquin.crptoola.data.remote.CoinPaprikaApi
import com.devcommop.joaquin.crptoola.data.repository.CoinRepositoryImpl
import com.devcommop.joaquin.crptoola.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)//InstallIn batata hai how long our dependencies(module wali) would live. Installing in SingletonComponent::class ka matlab hai ki as long as our application is alive those dependencies would live
object AppModule {
    @Provides
    @Singleton//Singleton != SingletonComponent. Singleton means only one instance of current object(returned by function) would be there
    fun providePaprikaApi() : CoinPaprikaApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }
    @Provides
    @Singleton
    fun provideCoinRepository(api : CoinPaprikaApi) : CoinRepository{
        return CoinRepositoryImpl(api)
    }
}