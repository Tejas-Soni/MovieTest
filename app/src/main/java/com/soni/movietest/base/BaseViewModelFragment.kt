package com.soni.movietest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes

abstract class BaseViewModelFragment<T : BaseViewModel>(@LayoutRes layout: Int) : BaseFragment(layout) {

    protected val viewModel by lazy { buildViewModel() }

    protected abstract fun buildViewModel(): T

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLiveDataObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setBaseViewModel(viewModel)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @CallSuper
    protected open fun initLiveDataObservers() {
    }

}
