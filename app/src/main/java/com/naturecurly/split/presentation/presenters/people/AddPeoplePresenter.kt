package com.naturecurly.split.presentation.presenters.people

import android.database.sqlite.SQLiteConstraintException
import com.naturecurly.split.domain.data.Person
import com.naturecurly.split.domain.usercases.people.AddPersonUseCase
import com.naturecurly.split.domain.usercases.people.DeletePersonUseCase
import com.naturecurly.split.domain.usercases.people.GetAllPeopleUseCase
import com.naturecurly.split.presentation.presenters.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * @author Leon Wu
 */
class AddPeoplePresenter @Inject constructor(private val addPersonUseCase: AddPersonUseCase,
                                             private val getAllPeopleUseCase: GetAllPeopleUseCase,
                                             private val deletePersonUseCase: DeletePersonUseCase) : BasePresenter {

    private lateinit var view: AddPeoplePresenter.View
    private lateinit var router: AddPeoplePresenter.Router

    // region Lifecycle
    override fun onCreate() {
        super.onCreate()
        loadPeople(isFirstLoad = true)
    }
    // endregion

    // region Public Functions
    fun init(view: AddPeoplePresenter.View, router: AddPeoplePresenter.Router) {
        this.view = view
        this.router = router
    }

    fun onAddButtonClicked(name: String) {
        if (name.isNotEmpty()) {
            view.clearPersonInputField()
            addPersonUseCase.addPerson(name)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view.hideSameNameError()
                        loadPeople(isFirstLoad = false)
                    }, {
                        when (it) {
                            is SQLiteConstraintException -> {
                                view.showSameNameError()
                            }
                        }
                    })
        } else {
            view.showEmptyNameError()
        }
    }

    fun onBackPressed(isDeleteButtonShown: Boolean) {
        if (isDeleteButtonShown) {
            view.clearDeleteButton()
        } else {
            view.back()
        }
    }

    fun onDeleteButtonClicked(id: Long, position: Int) {
        deletePersonUseCase.deletePerson(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.removePersonFromList(position)
                }, {})
    }
    // endregion

    // region Private Functions
    private fun loadPeople(isFirstLoad: Boolean) {
        getAllPeopleUseCase.getAllPeople()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showAllPeople(it)
                    if (!isFirstLoad) {
                        view.scrollListToBottom()
                    }
                }
    }
    // endregion

    interface View {
        fun showAllPeople(people: List<Person>)
        fun clearPersonInputField()
        fun scrollListToBottom()
        fun showSameNameError()
        fun hideSameNameError()
        fun clearDeleteButton()
        fun back()
        fun removePersonFromList(position: Int)
        fun showEmptyNameError()
    }

    interface Router {

    }
}