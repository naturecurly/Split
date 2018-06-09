package com.naturecurly.split.presentation.ui.people

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.naturecurly.split.R
import com.naturecurly.split.domain.data.Person
import com.naturecurly.split.injection.components.ScreenComponent
import com.naturecurly.split.presentation.presenters.people.AddPeoplePresenter
import com.naturecurly.split.presentation.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_add_people.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onEditorAction
import javax.inject.Inject

/**
 * @author Leon Wu
 */
class AddPeopleActivity : BaseActivity(), AddPeoplePresenter.View, AddPeoplePresenter.Router, PeopleAdapter.OnDeleteButtonListener {

    companion object {
        fun newIntent(context: Context) = context.intentFor<AddPeopleActivity>()
    }

    @Inject
    override lateinit var presenter: AddPeoplePresenter

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    lateinit var adapter: PeopleAdapter

    override fun initDagger(screenComponent: ScreenComponent) {
        screenComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_people)
        setToolbarTitle(R.string.add_people_title)

        presenter.init(this, this)
        setUpPeopleList()
        setUpEditText()
        add_people_button.onClick { presenter.onAddButtonClicked(add_people_edit_text.text.toString()) }
    }

    override fun onBackPressed() {
        presenter.onBackPressed(adapter.isDeleteButtonShown())
    }

    override fun onDeleteButtonClicked(id: Long, position: Int) {
        presenter.onDeleteButtonClicked(id, position)
    }

    // region Private Functions
    private fun setUpPeopleList() {
        people_list.layoutManager = gridLayoutManager
        adapter = PeopleAdapter(this, arrayListOf(), this)
        people_list.adapter = adapter
    }

    private fun setUpEditText() {
        add_people_edit_text.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(view: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    presenter.onAddButtonClicked(add_people_edit_text.text.toString())
                    return true
                } else {
                    return false
                }
            }
        })
    }
    // endregion

    // region View
    override fun showAllPeople(people: List<Person>) {
        adapter.setData(people)
    }

    override fun clearPersonInputField() {
        add_people_edit_text.text!!.clear()
    }

    override fun scrollListToBottom() {
        people_list.smoothScrollToPosition(people_list.adapter!!.itemCount - 1)
    }

    override fun showSameNameError() {
        add_people_text_input_layout.error = getString(R.string.same_name_error)
    }

    override fun hideSameNameError() {
        add_people_text_input_layout.error = null
    }

    override fun clearDeleteButton() {
        adapter.clearDeleteButton()
    }

    override fun back() = super.onBackPressed()

    override fun removePersonFromList(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun showEmptyNameError() {
        add_people_text_input_layout.error = getString(R.string.no_name_error)
    }
    // endregion
}