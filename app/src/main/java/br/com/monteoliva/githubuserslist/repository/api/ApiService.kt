package br.com.monteoliva.githubuserslist.repository.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

import br.com.monteoliva.githubuserslist.repository.model.UserItem
import br.com.monteoliva.githubuserslist.repository.model.repositories.UserRepositories
import br.com.monteoliva.githubuserslist.repository.model.search.UserSearch
import br.com.monteoliva.githubuserslist.repository.model.users.UserList

interface ApiService {
    @GET("/search/users")
    suspend fun getUserSearch(
        @Query("q") q: String) : Response<UserSearch?>

    @GET("/users")
    suspend fun getUserList() : Response<UserList?>

    @GET("/users/{user}")
    suspend fun getUserInformation(@Path("user") user: String) : Response<UserItem?>

    @GET("/users/{user}/repos")
    suspend fun getUserRepositories(@Path("user") user: String) : Response<UserRepositories?>
}