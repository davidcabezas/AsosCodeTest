package com.asoscodetest.ui.base

/**
 * Created by David C. on 18/11/2018.
 */
interface BasePresenter<in T> {

    fun subscribe()
    fun unsubscribe()
    fun attach(view: T)
    fun detach()

}