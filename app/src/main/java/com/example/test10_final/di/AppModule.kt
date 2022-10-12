package com.example.test10_final.di

import com.example.test10_final.common.ApiEndpoints
import com.example.test10_final.data.remote.ChatService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @Singleton
    @Provides
    fun apiService(): ChatService =
        Retrofit.Builder().baseUrl(ApiEndpoints.Base_URL).client(providesOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ChatService::class.java)


}