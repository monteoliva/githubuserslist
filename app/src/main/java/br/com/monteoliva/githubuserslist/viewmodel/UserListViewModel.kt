package br.com.monteoliva.githubuserslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import br.com.monteoliva.githubuserslist.repository.core.RepositoryServer
import br.com.monteoliva.githubuserslist.repository.model.WsResult
import br.com.monteoliva.githubuserslist.repository.model.users.UserList

@HiltViewModel
class UserListViewModel @Inject constructor(private val repository: RepositoryServer) : ViewModel() {
    val pageLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun isLoading(isLoading: Boolean) {
        pageLoading.apply {
            removeObserver {}
            postValue(isLoading)
        }
    }

    val userList : LiveData<WsResult<UserList?>> get() = repository.getUserList()
}