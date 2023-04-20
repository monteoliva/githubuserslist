package br.com.monteoliva.githubuserslist.repository.core.repositories

import android.content.Context
import androidx.lifecycle.liveData
import javax.inject.Inject

import br.com.monteoliva.githubuserslist.repository.api.ApiService
import br.com.monteoliva.githubuserslist.repository.core.extensions.wrapperResponse

class UserSearchRepository @Inject constructor(private val context: Context,
                                               private val server: ApiService) {
    fun getUserSearch(user: String) = liveData {
        emit(server.getUserSearch(user).wrapperResponse(context))
    }
}