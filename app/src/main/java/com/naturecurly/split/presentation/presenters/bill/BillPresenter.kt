package com.naturecurly.split.presentation.presenters.bill

import com.naturecurly.split.presentation.presenters.BasePresenter
import javax.inject.Inject

/**
 * @author Leon Wu
 */
class BillPresenter @Inject constructor() : BasePresenter {

    private lateinit var view: View
    private lateinit var router: Router

    // region Public Functions
    fun init(view: View, router: Router) {
        this.view = view
        this.router = router
    }

    fun onAddButtonClicked() {
        view.showAddBottomDialog()
    }

    fun onAddPeopleButtonClicked() {
        router.navigateToAddPeopleScreen()
    }

    fun onBackPressed() {

    }
    // endregion

    interface Router {
        fun navigateToAddPeopleScreen()
    }

    interface View {
        fun showAddBottomDialog()
    }
}