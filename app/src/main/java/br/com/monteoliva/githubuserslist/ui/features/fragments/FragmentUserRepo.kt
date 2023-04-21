package br.com.monteoliva.githubuserslist.ui.features.fragments

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import br.com.monteoliva.githubuserslist.R
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githubuserslist.databinding.FragmentListBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.Variables
import br.com.monteoliva.githubuserslist.repository.core.extensions.observerOnce
import br.com.monteoliva.githubuserslist.repository.core.extensions.wrapperResult
import br.com.monteoliva.githubuserslist.repository.model.repositories.UserRepositories
import br.com.monteoliva.githubuserslist.ui.adapterr.ItemUserListAdapter
import br.com.monteoliva.githubuserslist.ui.adapterr.ItemUserRepoAdapter
import br.com.monteoliva.githubuserslist.ui.features.BaseFragment
import br.com.monteoliva.githubuserslist.viewmodel.UserRepoViewModel

@AndroidEntryPoint
class FragmentUserRepo : BaseFragment<FragmentListBinding>() {
    private val viewModel : UserRepoViewModel by viewModels()
    private var itemAdapter: ItemUserRepoAdapter? = null
    private var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(Variables.USER_LOGIN).let { userName = it }
    }

    override fun getLayoutId(): Int = R.layout.fragment_list
    override fun initViews() {
        ItemUserRepoAdapter().also { itemAdapter = it }
        binding?.apply {
            rvList.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 1)
                adapter       = itemAdapter
            }
        }
    }

    override fun initViewModel() {
        viewModel.getUserRepositories(userName.toString()).observerOnce {
            it.wrapperResult { data ->
                when(data) {
                    is UserRepositories -> loadData(data)
                    is String           -> errorMsg(data.toString())
                }
            }
        }
    }

    private fun loadData(items: UserRepositories) {

    }

    companion object {
        fun newInstance(user: String) : FragmentUserRepo = FragmentUserRepo().apply {
            arguments = Bundle().apply { putString(Variables.USER_LOGIN, user) }
        }
    }
}