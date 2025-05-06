package com.example.androidlabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

sealed class ListItem {
    data class DateItem(val date: String) : ListItem()
    data class ActivityItem(val taskItem: TaskItem) : ListItem()
}

class RVAdapter(
    private val activities: List<TaskItem>,
    private val fragmentType: FragmentType
) : RecyclerView.Adapter<RVAdapter.MyViewHolder>() {

    private var items: List<ListItem> = listOf()

    var onClick: ((Int) -> Unit)? = null

    enum class FragmentType {
        MY, USERS
    }

    fun updateData(newTaskItems: List<TaskItem>) {
        val sortedTaskItems = newTaskItems.sortedByDescending { it.startTime }
        val newList = mutableListOf<ListItem>()

        var lastDate: String? = null
        for (taskItem in sortedTaskItems) {

            val currentDate = formatDate(taskItem.startTime)
            if (currentDate != lastDate) {
                newList.add(ListItem.DateItem(currentDate))
                lastDate = currentDate
            }
            newList.add(ListItem.ActivityItem(taskItem))
        }

        items = newList
        notifyDataSetChanged()
    }


    fun getItem(position: Int): TaskItem {
        val item = items[position]
        if (item is ListItem.ActivityItem) {
            return item.taskItem
        } else {
            throw IllegalArgumentException("Item at position $position is not an ActivityItem")
        }
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
        return sdf.format(java.util.Date(timestamp))
    }


    abstract class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class ActivityItemViewHolder(view: View) : MyViewHolder(view) {
        val distance: TextView = view.findViewById(R.id.distance)
        val time: TextView = view.findViewById(R.id.time)
        val activityType: TextView = view.findViewById(R.id.activityType)
        val timestamp: TextView = view.findViewById(R.id.timestamp)

        fun bind(ta: TaskItem) {
            val randomTime = (10..120).random()
            val randomDistance = (400..2000).random()
            distance.text = "$randomDistance м"
            time.text = "$randomTime мин"
            activityType.text = ta.activityType.displayName
            val sdf = SimpleDateFormat("d MMMM, HH:mm", Locale("ru"))
            val formattedDate = sdf.format(java.util.Date(ta.startTime))
            timestamp.text = formattedDate
        }
    }

    inner class ActivityItemUsersViewHolder(view: View) : MyViewHolder(view) {
        val distance: TextView = view.findViewById(R.id.distance)
        val time: TextView = view.findViewById(R.id.time)
        val activityType: TextView = view.findViewById(R.id.activityType)
        val timestamp: TextView = view.findViewById(R.id.timestamp)
        val nickname: TextView = view.findViewById(R.id.nickname)

        fun bind(ta: TaskItem) {
            val randomTime = (10..120).random()
            val randomDistance = (400..2000).random()
            distance.text = "$randomDistance м"
            time.text = "$randomTime мин"
            val sdf = SimpleDateFormat("d MMMM, HH:mm", Locale("ru"))
            val formattedDate = sdf.format(java.util.Date(ta.startTime))
            timestamp.text = formattedDate
            nickname.text = "@" + ta.user.toString()
        }
    }

    class ActivityDateViewHolder(view: View) : MyViewHolder(view) {
        val date: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.example.androidlabs.RVAdapter.MyViewHolder {
        return when (viewType) {
            com.example.androidlabs.RVAdapter.Companion.VIEW_TYPE_DATE -> {
                ActivityDateViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.activity_date, parent, false)
                )
            }

            com.example.androidlabs.RVAdapter.Companion.VIEW_TYPE_ACTIVITY -> {
                when (fragmentType) {
                    com.example.androidlabs.RVAdapter.FragmentType.MY -> ActivityItemViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false)
                    )

                    com.example.androidlabs.RVAdapter.FragmentType.USERS -> ActivityItemUsersViewHolder(
                        LayoutInflater.from(parent.context)
                            .inflate(R.layout.task_users, parent, false)
                    )

                    else -> ActivityItemViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false)
                    )
                }
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(
        holder: com.example.androidlabs.RVAdapter.MyViewHolder,
        position: Int
    ) {
        when (val item = items[position]) {
            is com.example.androidlabs.ListItem.DateItem -> {
                (holder as com.example.androidlabs.RVAdapter.ActivityDateViewHolder).date.text =
                    item.date
            }

            is com.example.androidlabs.ListItem.ActivityItem -> {
                when (holder) {
                    is com.example.androidlabs.RVAdapter.ActivityItemViewHolder -> holder.bind(
                        item.taskItem
                    )

                    is com.example.androidlabs.RVAdapter.ActivityItemUsersViewHolder -> holder.bind(
                        item.taskItem
                    )
                }
                holder.itemView.setOnClickListener {
                    onClick?.invoke(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ListItem.DateItem -> VIEW_TYPE_DATE
            is ListItem.ActivityItem -> VIEW_TYPE_ACTIVITY
        }
    }

    companion object {
        const val VIEW_TYPE_DATE = 0
        const val VIEW_TYPE_ACTIVITY = 1
    }

}