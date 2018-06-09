package com.naturecurly.split.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naturecurly.split.SplitApplication
import com.naturecurly.split.injection.components.DaggerScreenComponent
import com.naturecurly.split.injection.components.ScreenComponent
import com.naturecurly.split.injection.modules.ScreenModule
import com.naturecurly.split.presentation.presenters.BasePresenter
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * @author Leon Wu
 */
abstract class BaseActivity : AppCompatActivity() {

    protected abstract val presenter: BasePresenter

    // region LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screenComponent = DaggerScreenComponent.builder()
                .applicationComponent(SplitApplication.appComponent)
                .screenModule(ScreenModule(this))
                .build()
        initDagger(screenComponent)
        presenter.onCreate()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
    // endRegion

    protected abstract fun initDagger(screenComponent: ScreenComponent)

    protected fun setToolbarTitle(title: Int) {
        toolbar_title.text = getString(title)
    }
}