package br.com.monteoliva.githubuserslist.repository.model.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import br.com.monteoliva.githubuserslist.repository.model.UserItem

data class UserSearch(
    @SerializedName("incomplete_results")
    @Expose val incompleteResults: Boolean? = null,
    @SerializedName("items")
    @Expose val items: MutableList<UserItem?>? = null,
    @SerializedName("total_count")
    @Expose val totalCount: Int? = null
) : java.io.Serializable