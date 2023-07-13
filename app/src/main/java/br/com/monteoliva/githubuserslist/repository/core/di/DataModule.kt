package br.com.monteoliva.githubuserslist.repository.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import br.com.monteoliva.githubuserslist.repository.api.ApiService
import br.com.monteoliva.githubuserslist.repository.core.RetrofitMobile

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun bindContext(@ApplicationContext context: Context) : Context = context

    @Singleton
    @Provides
    fun bindApiService(service: RetrofitMobile) : ApiService = service.apiService
}