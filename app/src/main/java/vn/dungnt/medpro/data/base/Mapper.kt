package vn.dungnt.medpro.data.base

interface Mapper<M, E> {
    fun toEntity(model: M): E
    fun toModel(entity: E): M
}