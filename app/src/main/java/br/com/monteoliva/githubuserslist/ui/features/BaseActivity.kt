package br.com.monteoliva.githubuserslist.ui.features

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.repository.core.extensions.leftToRight
import br.com.monteoliva.githubuserslist.repository.core.extensions.rightToLeft
import br.com.monteoliva.githubuserslist.repository.core.extensions.topToBottom
import br.com.monteoliva.githubuserslist.repository.core.extensions.bottomToTop

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {
    protected var binding: T? = null
    private var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        startInitViews(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        startInitViewModel()
    }

    private fun startInitViews(savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({ initViews(savedInstanceState) }, 60)
    }

    private fun startInitViewModel() {
        Handler(Looper.getMainLooper()).postDelayed({ initViewModel() }, 60)
    }

    fun setupToolBar(toolbar: Toolbar) {
        toolbar.apply {
            this@BaseActivity.setSupportActionBar(this)
            setTitleTextColor(ContextCompat.getColor(baseContext, R.color.white))
        }
        actionBar = supportActionBar
    }
    fun setActionBarTitle(title: String)            { actionBar?.title    = title }
    fun setActionBarTitle(@StringRes title: Int)    { actionBar?.title    = getString(title) }
    fun setActionBarSubTitle(title: String)         { actionBar?.subtitle = title }
    fun setActionBarSubTitle(@StringRes title: Int) { actionBar?.subtitle = getString(title) }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            back()
            true
        }
        else {
            super.onKeyDown(keyCode, event)
        }
    }

    fun errorMsg(@StringRes msg: Int) { errorMsg(getString(msg)) }
    fun errorMsg(message: String) {
        setLoading(false)
        AlertDialog.Builder(this, R.style.AlertDialogTheme).apply {
            setCancelable(false)
            setMessage(message)
            setPositiveButton(getString(R.string.btn_ok)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            create().show()
        }
    }

    fun boxMsg(@StringRes msg: Int, callback: () -> Unit) { boxMsg(getString(msg)) { callback.invoke() }  }
    fun boxMsg(message: String, callback: () -> Unit) {
        setLoading(false)
        AlertDialog.Builder(this, R.style.AlertDialogTheme).apply {
            setCancelable(false)
            setMessage(message)
            setPositiveButton(getString(R.string.btn_ok)) { dialogInterface, _ ->
                dialogInterface.dismiss()
                callback.invoke()
            }
            setNegativeButton(getString(R.string.btn_cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            create().show()
        }
    }

    fun replaceFragment(@IdRes resourceId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(resourceId, fragment, TAG)
            disallowAddToBackStack()
            commit()
        }
    }

    fun animLeftToRight() { this.leftToRight() }
    fun animRightToLeft() { this.rightToLeft() }
    fun animTopToBottom() { this.topToBottom() }
    fun animBottomToTop() { this.bottomToTop() }

    @LayoutRes
    abstract fun getLayoutId() : Int
    abstract fun initViews(savedInstanceState: Bundle?)
    abstract fun initViewModel()
    abstract fun back()
    abstract fun setLoading(isLoading: Boolean)
    abstract fun redirectActivity(userLogin: String)

    companion object {
        private const val TAG = "BaseActivity"
    }
}