package br.com.monteoliva.githubuserslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import br.com.monteoliva.githubuserslist.repository.core.repositories.UserInformationRepository
import br.com.monteoliva.githubuserslist.repository.model.UserItem
import br.com.monteoliva.githubuserslist.repository.model.WsResult

@HiltViewModel
class UserInformationViewModel @Inject constructor(
    private val repository: UserInformationRepository) : ViewModel() {
    fun getUserInformation(user: String) : LiveData<WsResult<UserItem?>> =
        repository.getUserInformation(user)
}