package br.com.monteoliva.githubuserslist.repository.core.extensions

import java.net.ConnectException
import android.content.Context
import retrofit2.Response

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.repository.model.WsResult

fun <T> WsResult<T?>.wrapperResult(response: (Any) -> Unit) {
    this.let { result ->
        when (result) {
            is WsResult.Success<*> -> {
                result.data?.let { data -> response.invoke(data) }
            }
            is WsResult.Error -> {
                response.invoke(result.exception.message.toString())
            }
        }
    }
}

fun <T> Response<T?>.wrapperResponse(context: Context) : WsResult<T?> {
    return try {
        if (this.isSuccessful) {
            WsResult.Success(data = this.body())
        }
        else {
            WsResult.Error(exception = Exception(context.getString(R.string.error)))
        }
    }
    catch (e: ConnectException) {
        WsResult.Error(exception = Exception(context.getString(R.string.error1)))
    }
    catch (e: Exception) {
        WsResult.Error(exception = Exception(context.getString(R.string.error2)))
    }
}