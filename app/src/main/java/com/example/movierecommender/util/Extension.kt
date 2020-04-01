package com.example.movierecommender.util

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.movierecommender.R

fun Fragment.replaceFragment(activity: FragmentActivity?) {
    val fragment = activity?.supportFragmentManager
    val transaction = fragment?.beginTransaction()
    transaction?.replace(R.id.frame_layout, this)?.commitAllowingStateLoss()
}

fun Context.showToast(text: String, duration: Int){
    Toast.makeText(this, text, duration).show()
}