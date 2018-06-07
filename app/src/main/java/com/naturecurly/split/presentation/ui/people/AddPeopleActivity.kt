package com.naturecurly.split.presentation.ui.people

import android.content.Context
import android.os.Bundle
import com.naturecurly.split.R
import com.naturecurly.split.injection.components.ScreenComponent
import com.naturecurly.split.presentation.presenters.people.AddPeoplePresenter
import com.naturecurly.split.presentation.ui.BaseActivity
import org.jetbrains.anko.intentFor
import javax.inject.Inject

/**
 * @author Leon Wu
 */
class AddPeopleActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = context.intentFor<AddPeopleActivity>()
    }

    @Inject
    override lateinit var presenter: AddPeoplePresenter

    override fun initDagger(screenComponent: ScreenComponent) {
        screenComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_people)
    }
}