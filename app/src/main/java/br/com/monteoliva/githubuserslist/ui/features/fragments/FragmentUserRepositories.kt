package br.com.monteoliva.githubuserslist.ui.features.fragments

import android.os.Bundle
import androidx.fragment.app.viewModels
import br.com.monteoliva.githubuserslist.R
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githubuserslist.databinding.FragmentListBinding
import br.com.monteoliva.githubuserslist.repository.core.extensions.Variables
import br.com.monteoliva.githubuserslist.ui.features.BaseFragment
import br.com.monteoliva.githubuserslist.viewmodel.UserRepositoriesViewModel

@AndroidEntryPoint
class FragmentUserRepositories : BaseFragment<FragmentListBinding>() {
    private val viewModel : UserRepositoriesViewModel by viewModels()
    private var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(Variables.USER_LOGIN).let { userName = it }
    }

    override fun getLayoutId(): Int = R.layout.fragment_list
    override fun initViews() {
    }

    override fun initViewModel() {
    }
}