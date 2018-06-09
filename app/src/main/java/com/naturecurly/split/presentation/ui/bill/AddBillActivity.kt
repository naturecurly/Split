package com.naturecurly.split.presentation.ui.bill

import android.content.Context
import android.os.Bundle
import com.naturecurly.split.R
import com.naturecurly.split.injection.components.ScreenComponent
import com.naturecurly.split.presentation.presenters.BasePresenter
import com.naturecurly.split.presentation.presenters.bill.AddBillPresenter
import com.naturecurly.split.presentation.ui.BaseActivity
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * @author Leon Wu
 */
class AddBillActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = context.intentFor<AddBillActivity>()
    }

    @Inject
    override lateinit var presenter: AddBillPresenter

    override fun initDagger(screenComponent: ScreenComponent) {
        screenComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bill)

    }
}