package com.konkuk.kuitprojectsetting.di

import com.konkuk.kuitprojectsetting.data.repositoryimpl.HomeRepositoryImpl
import com.konkuk.kuitprojectsetting.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}