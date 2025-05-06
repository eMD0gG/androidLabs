package com.example.androidlabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailedActivityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailedActivityFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var ta: TaskItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ta = it.getSerializable(ARG_ACTIVITY) as? TaskItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_activity, container, false).apply {
            isClickable = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val arrow = view.findViewById<ImageView>(R.id.icon_arrow)

        arrow.setOnClickListener(){
            parentFragmentManager.popBackStack()
        }

        val distance: TextView = view.findViewById(R.id.distance)
        val time: TextView = view.findViewById(R.id.time)
        val activityType: TextView = view.findViewById(R.id.activity_type)
        val timestamp: TextView = view.findViewById(R.id.timestamp)

        distance.text = "10 м"
        time.text = "5 мин"
        activityType.text = ta?.activityType?.displayName
        timestamp.text = ta?.id.toString()

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        private const val ARG_ACTIVITY = "activity_arg"

        @JvmStatic
        fun newInstance(ta: TaskItem) =
            DetailedActivityFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ACTIVITY, ta)
                }
            }
    }
}