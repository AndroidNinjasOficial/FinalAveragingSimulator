package com.androidninjas.finalaveragingsimulator.util

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.androidninjas.finalaveragingsimulator.BuildConfig
import com.androidninjas.finalaveragingsimulator.R
import com.google.android.material.snackbar.Snackbar

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction(context.getString(R.string.txt_ok)) {
            snackbar.dismiss()
        }
    }.show()
}

fun Context.log(message: String?) {
    if (BuildConfig.DEBUG) Log.e(this::class.java.simpleName, message!!)
}

fun AppCompatActivity.setupToolbar(@IdRes id: Int, title: String? = null, upNavigation: Boolean = false): ActionBar {
    val toolbar = findViewById<Toolbar>(id)
    setSupportActionBar(toolbar)
    if (title != null){
        supportActionBar?.title = title
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)
    return supportActionBar!!
}