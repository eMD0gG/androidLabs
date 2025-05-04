package com.example.androidlabs

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlabs.databinding.ActivityTypeItemBinding

class TRVAdapter(private val Types: Array<String>): RecyclerView.Adapter<TRVAdapter.ViewHolder>() {
    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    class ViewHolder(val binding: ActivityTypeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val textView: TextView  = binding.type

        fun defaultBg() {
            binding.item.background = itemView.context.getDrawable(R.drawable.borders_file)
        }

        fun selectedBg() {
            binding.item.background = itemView.context.getDrawable(R.drawable.selected_borders_file)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        val binding = ActivityTypeItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)

//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.activity_type_item, viewGroup, false)
//
//        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = Types[position]

        if(position == selectedItemPos)
            viewHolder.selectedBg()
        else
            viewHolder.defaultBg()

        viewHolder.itemView.setOnClickListener {
            // Обновляем позиции
            selectedItemPos = position
            if (lastItemSelectedPos != -1) {
                notifyItemChanged(lastItemSelectedPos)
            }
            lastItemSelectedPos = selectedItemPos
            notifyItemChanged(selectedItemPos)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = Types.size
}
