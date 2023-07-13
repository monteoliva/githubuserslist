package br.com.monteoliva.githubuserslist.repository.core.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).also {
        it.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun View.showKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).also {
        it.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}
