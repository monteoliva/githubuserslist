package br.com.monteoliva.githubuserslist.ui.features.information

import android.os.Bundle
import androidx.activity.viewModels

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.databinding.ActivityUserInformationBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.Variables
import br.com.monteoliva.githubuserslist.repository.core.extensions.observerOnce
import br.com.monteoliva.githubuserslist.ui.features.BaseActivity
import br.com.monteoliva.githubuserslist.viewmodel.UserInformationViewModel
import br.com.monteoliva.githubuserslist.viewmodel.UserRepositoriesViewModel

class UserInformationActivity : BaseActivity<ActivityUserInformationBinding>() {
    private val viewModel : UserInformationViewModel by viewModels()
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
        viewModel.getUserInformation(userName.toString()).observerOnce {


        }
    }

    override fun back() {
    }

    override fun setLoading(isLoading: Boolean) {
    }

    override fun redirectActivity(userLogin: String) {
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}