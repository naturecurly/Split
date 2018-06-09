package com.naturecurly.split.presentation.ui.people

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.naturecurly.split.R
import com.naturecurly.split.domain.data.Person
import kotlinx.android.synthetic.main.item_people_card.view.*

class PeopleAdapter(context: Context,
                    private var peopleData: List<Person>) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    fun setData(people: List<Person>) {
        peopleData = people
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_people_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = peopleData.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val person = peopleData[position]
        with(holder?.itemView!!) {
            avatar.text = person.name
            avatar.backgroundTintList = ColorStateList.valueOf(person.backgroundColor)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}