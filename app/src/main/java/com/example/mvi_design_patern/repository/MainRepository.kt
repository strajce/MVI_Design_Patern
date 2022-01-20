package com.example.mvi_design_patern.repository

import com.example.mvi_design_patern.model.Blog
import com.example.mvi_design_patern.network.BlogService
import com.example.mvi_design_patern.network.NetworkMapper
import com.example.mvi_design_patern.room.BlogDao
import com.example.mvi_design_patern.room.CacheMapper
import com.example.mvi_design_patern.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogService: BlogService,
    private val blogCacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper,
) {
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(3000)
        try {
            val networkBlogs = blogService.get()
            val blogs = networkMapper.mapFromDomain(networkBlogs)
            for (blog in blogs) {
                blogDao.insert(blogCacheMapper.mapFromDtoModel(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(blogCacheMapper.mapFromDomain(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}