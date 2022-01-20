package com.example.mvi_design_patern.network

import com.example.mvi_design_patern.model.Blog
import com.example.mvi_design_patern.network.model.BlogDto
import com.example.mvi_design_patern.util.BlogDtoMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor(
) : BlogDtoMapper<BlogDto, Blog> {
    override fun mapToDtoModel(model: BlogDto): Blog {
        return Blog(
            model.id,
            model.title,
            model.body,
            model.image,
            model.category
        )
    }

    override fun mapFromDtoModel(domainModel: Blog): BlogDto {
        return BlogDto(
            domainModel.id,
            domainModel.title,
            domainModel.body,
            domainModel.image,
            domainModel.category
        )
    }

    fun mapFromDomain(models: List<BlogDto>): List<Blog> {
        return models.map { mapToDtoModel(it) }
    }
}