package br.com.monteoliva.githubuserslist.ui.features.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.databinding.FragmentListBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.observerOnce
import br.com.monteoliva.githubuserslist.repository.core.extensions.wrapperResult
import br.com.monteoliva.githubuserslist.repository.model.users.UserList
import br.com.monteoliva.githubuserslist.ui.adapterr.ItemUserListAdapter
import br.com.monteoliva.githubuserslist.ui.features.BaseFragment
import br.com.monteoliva.githubuserslist.viewmodel.UserListViewModel

@AndroidEntryPoint
class FragmentUserList : BaseFragment<FragmentListBinding>() {
    private val viewModel : UserListViewModel by viewModels()
    private var itemAdapter: ItemUserListAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_list
    override fun initViews() {
        ItemUserListAdapter().also { itemAdapter = it }
        binding?.apply {
            swipeRefreshList.setOnRefreshListener { viewModel.isLoading(true) }
            rvList.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 1)
                adapter       = itemAdapter
            }
        }
    }

    override fun initViewModel() {
        viewModel.apply {
            isLoading(true)
            pageLoading.observe(viewLifecycleOwner) {
                if (it == true) {
                    userList.observerOnce { result ->
                        result.wrapperResult { data ->
                            when (data) {
                                is UserList -> loadList(data)
                                is String   -> errorMsg(data.toString())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadList(items: UserList) {
        itemAdapter?.updateList(items)
        binding?.swipeRefreshList?.isRefreshing = false
        setLoading(false)
        viewModel.isLoading(false)
    }

    companion object {
        fun newInstance() : FragmentUserList = FragmentUserList()
    }
}