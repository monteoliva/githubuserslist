package br.com.monteoliva.githubuserslist.ui.features.information

import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.databinding.ActivityUserInformationBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.*
import br.com.monteoliva.githubuserslist.ui.features.BaseActivity
import br.com.monteoliva.githubuserslist.ui.features.fragments.FragmentUserInfo
import br.com.monteoliva.githubuserslist.ui.features.fragments.FragmentUserRepo
import br.com.monteoliva.githubuserslist.ui.features.list.UserListActivity

@AndroidEntryPoint
class UserInformationActivity : BaseActivity<ActivityUserInformationBinding>() {
    private var userName : String? = null

    override fun getLayoutId(): Int = R.layout.activity_user_information
    override fun initViews() {
        intent?.extras?.let {
            userName = it.getString(Variables.USER_LOGIN, "")
        }
        binding?.let {
            setupToolBar(it.appBarInfo.toolbarApp)
            setActionBarTitle(R.string.title_user_info)
            setActionBarHomeButton()
        }
    }

    override fun initViewModel() {
        replaceFragment(R.id.info_container, FragmentUserInfo.newInstance(userName.toString()))
        replaceFragment(R.id.repo_container, FragmentUserRepo.newInstance(userName.toString()))
    }

    override fun back() {
        Intent(this, UserListActivity::class.java).apply {
            startActivity(this)
            finish()
            animLeftToRight()
        }
    }

    override fun setLoading(isLoading: Boolean) { binding?.progressInfo?.visibility(isLoading) }
    override fun redirectActivity(userLogin: String) {}
}