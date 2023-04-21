package br.com.monteoliva.githubuserslist.repository.core

import android.content.Context
import androidx.lifecycle.liveData
import javax.inject.Inject

import br.com.monteoliva.githubuserslist.repository.api.ApiService
import br.com.monteoliva.githubuserslist.repository.core.extensions.wrapperResponse

class RepositoryServer @Inject constructor(private val context: Context,
                                           private val server: ApiService) {
    fun getUserList() = liveData {
        emit(server.getUserList().wrapperResponse(context))
    }

    fun getUserSearch(user: String) = liveData {
        emit(server.getUserSearch(user).wrapperResponse(context))
    }

    fun getUserInformation(user: String) = liveData {
        emit(server.getUserInformation(user).wrapperResponse(context))
    }

    fun getUserRepositories(user: String) = liveData {
        emit(server.getUserRepositories(user).wrapperResponse(context))
    }
}