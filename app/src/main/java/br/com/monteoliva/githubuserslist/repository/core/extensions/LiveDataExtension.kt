package br.com.monteoliva.githubuserslist.repository.core.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observerOnce(observer: (T) -> Unit) {
    removeObserver {}
    observeForever(object : Observer<T> {
        override fun onChanged(value: T) {
            observer.invoke(value)
            this@observerOnce.removeObserver(this)
        }
    })
}