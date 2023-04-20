package br.com.monteoliva.githubuserslist.repository.core.extensions

import androidx.appcompat.app.AppCompatActivity
import br.com.monteoliva.githubuserslist.R

fun AppCompatActivity.leftToRight() {
    overridePendingTransition(R.anim.lefttoright, R.anim.stable)
}

fun AppCompatActivity.rightToLeft() {
    overridePendingTransition(R.anim.righttoleft, R.anim.stable)
}

fun AppCompatActivity.topToBottom() {
    overridePendingTransition(R.anim.toptobottom, R.anim.stable)
}

fun AppCompatActivity.bottomToTop() {
    overridePendingTransition(R.anim.bottomtotop, R.anim.stable)
}