package br.com.monteoliva.githubuserslist.repository.core.repositories

import android.content.Context
import androidx.lifecycle.liveData
import javax.inject.Inject

import br.com.monteoliva.githubuserslist.repository.api.ApiService
import br.com.monteoliva.githubuserslist.repository.core.extensions.wrapperResponse

class UserInformationRepository @Inject constructor(private val context: Context,
                                                    private val server: ApiService) {
    fun getUserInformation(user: String) = liveData {
        emit(server.getUserInformation(user).wrapperResponse(context))
    }
}