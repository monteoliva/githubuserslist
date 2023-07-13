package br.com.monteoliva.githubuserslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import br.com.monteoliva.githubuserslist.repository.core.RepositoryServer
import br.com.monteoliva.githubuserslist.repository.model.UserItem
import br.com.monteoliva.githubuserslist.repository.model.WsResult

@HiltViewModel
class UserInfoViewModel @Inject constructor(private val repository: RepositoryServer) : ViewModel() {
    fun getUserInformation(user: String) : LiveData<WsResult<UserItem?>> = repository.getUserInformation(user)
}