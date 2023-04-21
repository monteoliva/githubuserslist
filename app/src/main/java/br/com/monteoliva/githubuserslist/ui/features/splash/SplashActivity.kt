package br.com.monteoliva.githubuserslist.ui.features.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.databinding.ActivitySplashBinding
import br.com.monteoliva.githubuserslist.ui.features.BaseActivity
import br.com.monteoliva.githubuserslist.ui.features.list.UserListActivity

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_splash
    override fun initViews() { load() }
    override fun initViewModel() {}
    override fun back() {}
    override fun setLoading(isLoading: Boolean) {}
    override fun redirectActivity(userLogin: String) {}

    private fun load() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, UserListActivity::class.java))
            finish()
            animRightToLeft()
        }, SECONDS)
    }

    companion object {
        private val SECONDS: Long = 2000
    }
}