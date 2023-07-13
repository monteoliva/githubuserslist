package br.com.monteoliva.githubuserslist.ui.features.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.databinding.ActivityUserListBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.Variables
import br.com.monteoliva.githubuserslist.repository.core.extensions.visibility
import br.com.monteoliva.githubuserslist.ui.features.BaseActivity
import br.com.monteoliva.githubuserslist.ui.features.fragments.FragmentUserList
import br.com.monteoliva.githubuserslist.ui.features.fragments.FragmentUserSearch
import br.com.monteoliva.githubuserslist.ui.features.information.UserInformationActivity

@AndroidEntryPoint
class UserListActivity : BaseActivity<ActivityUserListBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_user_list
    override fun initViews() {
        binding?.let {
            setupToolBar(it.appBar.toolbarApp)
            setActionBarTitle(R.string.title_user_list)
            it.searchList.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        loadUserSearch(query.toString().trim())
                        return false
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (query.toString().trim().isEmpty()) { loadUserList() }
                        return false
                    }
                })
            }
        }
    }

    override fun initViewModel() { loadUserList() }
    override fun back() {
        animLeftToRight()
        finish()
    }
    override fun setLoading(isLoading: Boolean) { binding?.progressList?.visibility(isLoading) }
    override fun redirectActivity(userLogin: String) {
        val bundle = Bundle().apply {
            putString(Variables.USER_LOGIN, userLogin)
        }
        Intent(baseContext, UserInformationActivity::class.java).apply {
            putExtras(bundle)
            startActivity(this)
            finish()
            animRightToLeft()
        }
    }

    private fun loadUserList() {
        setLoading(true)
        replaceFragment(R.id.list_container, FragmentUserList.newInstance())
    }

    private fun loadUserSearch(user: String) {
        setLoading(true)
        replaceFragment(R.id.list_container, FragmentUserSearch.newInstance(user))
    }
}