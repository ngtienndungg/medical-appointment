package vn.dungnt.medpro.data.base

data class ApiResponse<T>(
    val status: Boolean?,
    val message: String?,
    val data: T?
)