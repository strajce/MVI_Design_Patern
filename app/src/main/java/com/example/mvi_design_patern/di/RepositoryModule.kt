package com.example.mvi_design_patern.di

import com.example.mvi_design_patern.network.BlogService
import com.example.mvi_design_patern.network.NetworkMapper
import com.example.mvi_design_patern.repository.MainRepository
import com.example.mvi_design_patern.room.BlogDao
import com.example.mvi_design_patern.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogService: BlogService,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, blogService, cacheMapper, networkMapper)
    }
}