package br.com.monteoliva.githubuserslist.ui.features.list

import androidx.appcompat.widget.SearchView
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.databinding.ActivityUserListBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.visibility
import br.com.monteoliva.githubuserslist.ui.features.BaseActivity
import br.com.monteoliva.githubuserslist.ui.features.fragments.FragmentUserList

@AndroidEntryPoint
class UserListActivity : BaseActivity<ActivityUserListBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_user_list
    override fun initViews() {
        binding?.let {
            setupToolBar(it.appBar.toolbarApp)
            it.searchList.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            }
        }
    }

    override fun initViewModel() { loadUserList() }
    override fun back() { finish() }
    override fun setLoading(isLoading: Boolean) { binding?.progressList?.visibility(isLoading) }

    private fun loadUserList() {
        replaceFragment(R.id.list_container, FragmentUserList.newInstance())
    }
}