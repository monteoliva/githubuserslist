package br.com.monteoliva.githubuserslist.repository.core

import javax.inject.Inject
import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.repository.api.ApiService

class RetrofitMobile @Inject constructor(private val context: Context) {
    val apiService: ApiService
        get() = invoke().create(ApiService::class.java)

    private operator fun invoke(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(context.getString(R.string.API_URL))
            .client(okHttp3())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun okHttp3() : OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(MicroServiceInterceptor())
            .build()
}