package br.com.monteoliva.githubuserslist.ui.features.fragments

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.databinding.FragmentListBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.Variables
import br.com.monteoliva.githubuserslist.repository.core.extensions.observerOnce
import br.com.monteoliva.githubuserslist.repository.core.extensions.wrapperResult
import br.com.monteoliva.githubuserslist.repository.model.search.UserSearch
import br.com.monteoliva.githubuserslist.ui.adapterr.ItemUserListAdapter
import br.com.monteoliva.githubuserslist.ui.features.BaseActivity
import br.com.monteoliva.githubuserslist.ui.features.BaseFragment
import br.com.monteoliva.githubuserslist.viewmodel.UserSearchViewModel

@AndroidEntryPoint
class FragmentUserSearch  : BaseFragment<FragmentListBinding>() {
    private val viewModel : UserSearchViewModel by viewModels()
    private var itemAdapter: ItemUserListAdapter? = null
    private var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(Variables.USER_LOGIN).let { userName = it }
    }

    override fun getLayoutId(): Int = R.layout.fragment_list
    override fun initViews() {
        ItemUserListAdapter().apply {
            onItemClicked = { redirectInfo(it) }
        }.also {
            itemAdapter = it
        }

        binding?.apply {
            rvList.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 1)
                adapter       = itemAdapter
            }
        }
    }

    override fun initViewModel() { updateList(userName.toString()) }

    private fun updateList(user: String) {
        viewModel.getUserSearch(user).observerOnce {
            it.wrapperResult { data ->
                when (data) {
                    is UserSearch -> loadList(data)
                    is String     -> errorMsg(data.toString())
                }
            }
        }
    }

    private fun loadList(data: UserSearch) {
        itemAdapter?.updateList(data.items)
        setLoading(false)
    }

    private fun redirectInfo(userLogin: String) { (activity as? BaseActivity<*>)?.redirectActivity(userLogin) }

    companion object {
        fun newInstance(user: String) : FragmentUserSearch = FragmentUserSearch().apply {
            arguments = Bundle().apply { putString(Variables.USER_LOGIN, user) }
        }
    }
}