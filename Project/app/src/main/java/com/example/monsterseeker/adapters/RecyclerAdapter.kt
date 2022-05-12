package com.example.monsterseeker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.monsterseeker.R
import com.example.monsterseeker.models.ListMonster

class RecyclerAdapter(private val mList: List<ListMonster>, private val onItemClick: () -> Unit) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        holder.checkBox.isChecked = itemsViewModel.favourite
        holder.textView.text = itemsViewModel.name
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View, private val onItemClick: () -> Unit) : RecyclerView.ViewHolder(ItemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val textView: TextView = itemView.findViewById(R.id.textView)

        init {
            val button: Button = itemView.findViewById(R.id.button)
            button.setOnClickListener{
                onItemClick()
            }
        }
    }
}