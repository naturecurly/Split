package com.naturecurly.split.presentation.ui.people

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import com.naturecurly.split.R
import com.naturecurly.split.domain.data.Person
import kotlinx.android.synthetic.main.item_people_avatar.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onLongClick

class PeopleAdapter(context: Context,
                    private var peopleData: ArrayList<Person>,
                    private val listener: OnDeleteButtonListener? = null) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    private var deleteButtonPosition: Int? = null

    fun setData(people: List<Person>) {
        peopleData = ArrayList(people)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_people_avatar, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = peopleData[position]
        with(holder.itemView) {
            avatar.text = person.name
            avatar.backgroundTintList = ColorStateList.valueOf(person.backgroundColor)
            setOutline(this)

            if (deleteButtonPosition != position) {
                hideDeleteButton(this)
            } else {
                showDeleteButton(this)
            }

            onClick { clearDeleteButton() }
            avatar.onLongClick {
                deleteButtonPosition?.let {
                    notifyItemChanged(it)
                }
                showDeleteButton(this@with)
                deleteButtonPosition = position
            }
            avatar.onClick {
                if (deleteButtonPosition != position) {
                    clearDeleteButton()
                }
            }
            delete_button.onClick {
                deleteButtonPosition = null
                peopleData.remove(person)
                listener?.onDeleteButtonClicked(person.id, position)
            }
        }
    }

    override fun getItemCount() = peopleData.size

    private fun setOutline(view: View) {
        with(view) {
            avatar.clipToOutline = true
            avatar.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    val radius = Math.min(view.width, view.height) / 2 + 8
                    val centerX = (view.right - view.left) / 2
                    val centerY = (view.bottom - view.top) / 2
                    outline.setOval(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
                }
            }
        }
    }

    private fun showDeleteButton(view: View) {
        with(view) {
            avatar.visibility = View.GONE
            delete_button.visibility = View.VISIBLE
        }
    }

    private fun hideDeleteButton(view: View) {
        with(view) {
            avatar.visibility = View.VISIBLE
            delete_button.visibility = View.GONE
        }
    }

    fun isDeleteButtonShown() = deleteButtonPosition != null

    fun clearDeleteButton() {
        deleteButtonPosition?.let {
            notifyItemChanged(it)
            deleteButtonPosition = null
        }
    }

    interface OnDeleteButtonListener {
        fun onDeleteButtonClicked(id: Long, position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}