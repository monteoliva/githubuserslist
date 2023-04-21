package br.com.monteoliva.githubuserslist.ui.features.fragments

import android.os.Bundle
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.databinding.FragmentInfoBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.*
import br.com.monteoliva.githubuserslist.repository.model.UserItem
import br.com.monteoliva.githubuserslist.ui.features.BaseFragment
import br.com.monteoliva.githubuserslist.viewmodel.UserInfoViewModel

@AndroidEntryPoint
class FragmentUserInfo : BaseFragment<FragmentInfoBinding>() {
    private val viewModel : UserInfoViewModel by viewModels()
    private var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(Variables.USER_LOGIN).let { userName = it }
    }

    override fun getLayoutId(): Int = R.layout.fragment_info
    override fun initViews() {}
    override fun initViewModel() {
        viewModel.getUserInformation(userName.toString()).observerOnce {
            it.wrapperResult { data ->
                when (data) {
                    is UserItem -> loadData(data)
                    is String   -> errorMsg(data.toString())
                }
            }
        }
    }

    private fun loadData(item: UserItem) {
        binding?.apply {
            context?.let {
                item.avatarUrl?.let { it1 -> imageNavView.loadImage(it, it1) }
                item.login?.validation().let {  it1 -> textNavName.text = it1}
            }
        }
    }

    companion object {
        fun newInstance(user: String) : FragmentUserInfo = FragmentUserInfo().apply {
            arguments = Bundle().apply { putString(Variables.USER_LOGIN, user) }
        }
    }
}