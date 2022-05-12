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

class RecyclerAdapter(private val mList: List<ListMonster>,
                      private val onButtonClick: (name : String) -> Unit,
                      private val onCheckBoxClick: (name : String) -> Unit,
                      private val onTextViewClick: (name : String) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view, onButtonClick, onCheckBoxClick, onTextViewClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        holder.checkBox.isChecked = itemsViewModel.favourite
        holder.textView.text = itemsViewModel.name
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View,
                     private val onButtonClick: (name : String) -> Unit,
                     private val onCheckBoxClick: (name : String) -> Unit,
                     private val onTextViewClick: (name : String) -> Unit) : RecyclerView.ViewHolder(ItemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val textView: TextView = itemView.findViewById(R.id.textView)

        init {
            val textView: TextView = itemView.findViewById(R.id.textView)
            textView.setOnClickListener{
                onTextViewClick(textView.text.toString())
            }

            val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
            checkBox.setOnClickListener{
                onCheckBoxClick(textView.text.toString())
            }

            val button: Button = itemView.findViewById(R.id.button)
            button.setOnClickListener{
                onButtonClick(textView.text.toString())
            }
        }
    }
}