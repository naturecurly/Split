package com.naturecurly.split.presentation.presenters

/**
 * @author Leon Wu
 */
interface BasePresenter {

    fun onStart() {}

    fun onResume() {}

    fun onPause() {}

    fun onStop() {}

    fun onDestroy() {}
}