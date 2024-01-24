package com.example.todotry.Adopter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todotry.R
import com.example.todotry.Room.TODOTaskTable

class TaskAdopter(val data: List<TODOTaskTable>, val Taskbutton: taskbutton) :
    RecyclerView.Adapter<TaskAdopter.viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.taskrec, parent, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val current = data[position]
        holder.taskrecimage.setImageResource(current.icon)
        holder.taskrectasktime.text=current.time
        holder.taskrectitale.text=current.title
        holder.taskrectasktask_tiaile_descriptation.text=current.descripatation
    }

    inner class viewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskrecimage: ImageView = view.findViewById(R.id.taskrecimage)
        val taskrectasktime: TextView = view.findViewById(R.id.taskrectasktime)
        val taskrectitale: TextView = view.findViewById(R.id.taskrectitale)
        val taskrectasktask_tiaile_descriptation: TextView =
            view.findViewById(R.id.taskrectasktask_tiaile_descriptation)
        val complete: ImageButton = view.findViewById(R.id.complete)
        val delete: ImageButton = view.findViewById(R.id.delete)
        val notcomplete: ImageButton = view.findViewById(R.id.notcomplete)

        init {
            complete.setOnClickListener {
                Taskbutton.taskcomplete(adapterPosition,data[position])

            }
            delete.setOnClickListener {
                Taskbutton.taskdelete(adapterPosition,data[position])
            }
            notcomplete.setOnClickListener { Taskbutton.tasknotcomplete(adapterPosition,data[position]) }
        }
    }

    interface taskbutton {
        fun taskcomplete(position: Int,data:TODOTaskTable)
        fun taskdelete(position: Int,data:TODOTaskTable)
        fun tasknotcomplete(position: Int,data:TODOTaskTable)
    }
}