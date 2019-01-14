package com.example.junemon.moviedbkotlin.base

import android.content.Context
import android.view.View

interface BaseFragmentPresenter {
    fun onAttach(context: Context?)
    fun onCreateView(view: View)
}