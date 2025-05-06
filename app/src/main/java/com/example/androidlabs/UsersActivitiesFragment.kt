package com.example.androidlabs

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UsersActivitiesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsersActivitiesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var myVM: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_activities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myVM = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val adapter = RVAdapter(emptyList(), RVAdapter.FragmentType.USERS)
        recycler.adapter = adapter

        myVM.allTaskItems.observe(viewLifecycleOwner){
                newActivitiesList ->
            adapter.updateData(newActivitiesList)
        }
        adapter.onClick = {
                position ->
            val selectedActivity = adapter.getItem(position)
            val detailFragment = DetailedActivityFragment.newInstance(selectedActivity)

            val parent = parentFragment

            if (parent != null && parent.isAdded) {
                parent.parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.slide_to_right,
                        R.anim.slide_out_left,
                        R.anim.slide_to_left,
                        R.anim.slide_out_right
                    )
                    .replace(R.id.container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TaskListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UsersActivitiesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}