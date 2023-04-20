package br.com.monteoliva.githubuserslist.ui.features.list

import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.databinding.ActivityUserListBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.observerOnce
import br.com.monteoliva.githubuserslist.repository.core.extensions.visibility
import br.com.monteoliva.githubuserslist.repository.core.extensions.wrapperResult
import br.com.monteoliva.githubuserslist.repository.model.users.UserList
import br.com.monteoliva.githubuserslist.ui.adapterr.ItemUserListAdapter
import br.com.monteoliva.githubuserslist.ui.features.BaseActivity
import br.com.monteoliva.githubuserslist.viewmodel.UserListViewModel

@AndroidEntryPoint
class UserListActivity : BaseActivity<ActivityUserListBinding>() {
    private val viewModel : UserListViewModel by viewModels()
    private var itemAdapter: ItemUserListAdapter? = null

    override fun getLayoutId(): Int = R.layout.activity_user_list
    override fun initViews() {
        ItemUserListAdapter().also { itemAdapter = it }
        binding?.apply {
            swipeRefreshList.setOnRefreshListener { viewModel.isLoading(true) }
            rvList.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 1)
                adapter       = itemAdapter
            }
            idSV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }
    }

    override fun initViewModel() {
        viewModel.apply {
            isLoading(true)
            pageLoading.observe(this@UserListActivity) {
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
        setLoading(false)
        viewModel.isLoading(false)
    }

    override fun back() { finish() }
    override fun setLoading(isLoading: Boolean) {
        binding?.apply {
            swipeRefreshList.isRefreshing = isLoading
            progressList.visibility(isLoading)
        }
    }
}