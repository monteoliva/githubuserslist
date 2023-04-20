package br.com.monteoliva.githubuserslist.ui.features

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding> : Fragment() {
    protected var binding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        DataBindingUtil.inflate<T>(inflater, getLayoutId(), container, false).also {
            binding = it
        }
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startInitViews()
    }

    override fun onResume() {
        super.onResume()
        startViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun startInitViews() {
        Handler(Looper.getMainLooper()).postDelayed({ initViews() }, 60)
    }
    private fun startViewModel() {
        Handler(Looper.getMainLooper()).postDelayed({ initViewModel() }, 60)
    }

    fun errorMsg(@StringRes msg: Int) { errorMsg(getString(msg)) }
    fun errorMsg(message: String) { (activity as? BaseActivity<*>)?.errorMsg(message) }

    fun boxMsg(@StringRes msg: Int, callback: () -> Unit) { boxMsg(getString(msg)) {callback.invoke()}}
    fun boxMsg(message: String, callback: () -> Unit) {
        (activity as? BaseActivity<*>)?.boxMsg(message) { callback.invoke() }
    }

    fun setLoading(isLoading: Boolean) { (activity as? BaseActivity<*>)?.setLoading(isLoading) }

    @LayoutRes
    abstract fun getLayoutId() : Int
    abstract fun initViews()
    abstract fun initViewModel()
}