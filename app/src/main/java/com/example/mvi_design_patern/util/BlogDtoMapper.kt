package com.example.mvi_design_patern.util

interface BlogDtoMapper<T, DomainModel> {
    fun mapToDtoModel(model: T): DomainModel
    fun mapFromDtoModel(domainModel: DomainModel): T
}