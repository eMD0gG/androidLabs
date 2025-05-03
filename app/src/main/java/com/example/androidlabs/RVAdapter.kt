package com.example.androidlabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val activities: List<TaskItem>,
                private val fragmentType: FragmentType) : RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    var onClick: ((Int) -> Unit)? = null

    enum class FragmentType {
        MY, USERS
    }

    abstract class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class ActivityItemViewHolder(view: View) : MyViewHolder(view) {
        val distance: TextView = view.findViewById(R.id.distance)
        val time: TextView = view.findViewById(R.id.time)
        val activityType: TextView = view.findViewById(R.id.activityType)
        val timestamp: TextView = view.findViewById(R.id.timestamp)

        fun bind(ta: TaskItem) {
            distance.text = ta.distance
            time.text = ta.time
            activityType.text = ta.activityType
            timestamp.text = ta.timestamp
        }
    }

    inner class ActivityItemUsersViewHolder(view: View) : MyViewHolder(view) {
        val distance: TextView = view.findViewById(R.id.distance)
        val time: TextView = view.findViewById(R.id.time)
        val activityType: TextView = view.findViewById(R.id.activityType)
        val timestamp: TextView = view.findViewById(R.id.timestamp)
        val nickname: TextView = view.findViewById(R.id.nickname)

        fun bind(ta: TaskItem) {
            distance.text = ta.distance
            time.text = ta.time
            activityType.text = ta.activityType
            timestamp.text = ta.timestamp
            nickname.text = ta.user
        }
    }

    class ActivityDateViewHolder(view: View) : MyViewHolder(view) {
        val date: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.androidlabs.RVAdapter.MyViewHolder {
        return when(viewType){
            com.example.androidlabs.RVAdapter.Companion.VIEW_TYPE_1 -> {
                ActivityDateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_date, parent, false))
            }
            com.example.androidlabs.RVAdapter.Companion.VIEW_TYPE_2 -> {
                when (fragmentType){
                    com.example.androidlabs.RVAdapter.FragmentType.MY -> ActivityItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false))
                    com.example.androidlabs.RVAdapter.FragmentType.USERS -> ActivityItemUsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_users, parent, false))
                    else -> ActivityItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false))
                }
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = activities[position]
        when(holder) {
            is ActivityItemViewHolder -> holder.bind(item)
            is ActivityItemUsersViewHolder -> holder.bind(item)
            is ActivityDateViewHolder -> holder.date.text = "День ${position / 5 + 1}"
        }
        holder.itemView.setOnClickListener {
            onClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(position % 5 == 0) VIEW_TYPE_1 else VIEW_TYPE_2
    }

    companion object {
        const val VIEW_TYPE_1 = 0
        const val VIEW_TYPE_2 = 1
    }

}