package com.naturecurly.split.presentation.ui.bill

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.naturecurly.split.R
import com.naturecurly.split.injection.components.ScreenComponent
import com.naturecurly.split.presentation.presenters.bill.BillPresenter
import com.naturecurly.split.presentation.ui.BaseActivity
import com.naturecurly.split.presentation.ui.people.AddPeopleActivity
import kotlinx.android.synthetic.main.activity_bill.*
import kotlinx.android.synthetic.main.layout_add_bottom_sheet_dialog.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class BillActivity : BaseActivity(), BillPresenter.View, BillPresenter.Router {

    override fun initDagger(screenComponent: ScreenComponent) {
        screenComponent.inject(this)
    }

    @Inject
    override lateinit var presenter: BillPresenter

    private var addBottomSheetBehavior: BottomSheetBehavior<View>? = null

    // region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)
        setToolbarTitle(R.string.bill_list_title)
        presenter.init(this, this)

        setUpBottomSheet()
        fab_add.onClick { presenter.onAddButtonClicked() }

        add_people_option.onClick { presenter.onAddPeopleButtonClicked() }
    }

    override fun onBackPressed() {
        if (addBottomSheetBehavior?.state == BottomSheetBehavior.STATE_EXPANDED) {
            addBottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
        } else {
            super.onBackPressed()
        }
    }
    // endregion

    // region Private
    private fun setUpBottomSheet() {
        addBottomSheetBehavior = BottomSheetBehavior.from(findViewById<View>(R.id.add_bottom_sheet))
        addBottomSheetBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
    }
    // endregion

    // region View
    override fun showAddBottomDialog() {
        addBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }
    // endregion

    // region Router

    override fun navigateToAddPeopleScreen() {
        startActivity(AddPeopleActivity.newIntent(this))
    }
    // endregion
}