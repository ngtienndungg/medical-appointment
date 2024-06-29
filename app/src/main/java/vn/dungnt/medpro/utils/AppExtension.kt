package vn.dungnt.medpro.utils

import com.google.gson.Gson
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun Throwable.is401UnauthorizedError() = this is HttpException && code() == 401
fun Throwable.is500InternalError() = this is HttpException && code() == 500
fun Throwable.isTimeoutError() = this is SocketTimeoutException
val gson = Gson()
fun <A> A.convertToJson(): String {
    return gson.toJson(this)
}

inline fun <reified T> String.convertFromJson(): T {
    return gson.fromJson(this, T::class.java)
}