package br.com.monteoliva.githubuserslist.ui.features.information

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.databinding.ActivityUserInformationBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.*
import br.com.monteoliva.githubuserslist.repository.model.UserItem
import br.com.monteoliva.githubuserslist.ui.features.BaseActivity
import br.com.monteoliva.githubuserslist.ui.features.list.UserListActivity
import br.com.monteoliva.githubuserslist.viewmodel.UserInfoViewModel

class UserInformationActivity : BaseActivity<ActivityUserInformationBinding>() {
    private val viewModel : UserInfoViewModel by viewModels()
    private var userName : String? = null

    override fun getLayoutId(): Int = R.layout.activity_user_information
    override fun initViews(savedInstanceState: Bundle?) {
        savedInstanceState?.getString(Variables.USER_LOGIN).let {
            userName = it
        }
        binding?.let {
            setupToolBar(it.appBarInfo.toolbarApp)
            setActionBarTitle(R.string.title_user_info)
        }
    }

    override fun initViewModel() {
        viewModel.apply {




        }

//        viewModel.getUserInformation(userName.toString()).observerOnce {
//            it.wrapperResult { data ->
//                when (data) {
//                    is UserItem -> loadData(data)
//                    is String   -> errorMsg(data.toString())
//                }
//            }
//        }
    }

    private fun loadData(item: UserItem) {
        binding?.apply {
            appBarInfo.navHeader.let { info ->
                item.avatarUrl?.let          { info.imageView.loadImage(baseContext, it) }
                item.login?.validation().let {  info.textNavName.text = it}







            }




        }
        setLoading(false)
    }


    override fun back() {
    }

    override fun setLoading(isLoading: Boolean) { binding?.progressInfo?.visibility(isLoading) }
    override fun redirectActivity(userLogin: String) {
        Intent(this, UserListActivity::class.java).apply {
            startActivity(this)
            finish()

        }
    }

}