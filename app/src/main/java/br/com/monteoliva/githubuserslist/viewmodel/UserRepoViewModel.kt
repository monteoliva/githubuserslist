package br.com.monteoliva.githubuserslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import br.com.monteoliva.githubuserslist.repository.core.RepositoryServer
import br.com.monteoliva.githubuserslist.repository.model.WsResult
import br.com.monteoliva.githubuserslist.repository.model.repositories.UserRepositories

@HiltViewModel
class UserRepoViewModel @Inject constructor(private val repository: RepositoryServer) : ViewModel() {
    fun getUserRepositories(user: String) : LiveData<WsResult<UserRepositories?>> =
        repository.getUserRepositories(user)
}