package com.example.mvi_design_patern.room

import com.example.mvi_design_patern.model.Blog
import com.example.mvi_design_patern.network.model.BlogDto
import com.example.mvi_design_patern.util.BlogDtoMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor():BlogDtoMapper<BlogCacheDto, Blog>{
    override fun mapToDtoModel(model: BlogCacheDto): Blog {
        return Blog(
            model.id,
            model.title,
            model.body,
            model.image,
            model.category
        )
    }

    override fun mapFromDtoModel(domainModel: Blog): BlogCacheDto {
        return BlogCacheDto(
            domainModel.id,
            domainModel.title,
            domainModel.body,
            domainModel.image,
            domainModel.category
        )
    }

    fun mapFromDomain(models: List<BlogCacheDto>): List<Blog> {
        return models.map { mapToDtoModel(it) }
    }
}