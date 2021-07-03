package com.androidninjas.finalaveragingsimulator.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidninjas.finalaveragingsimulator.databinding.FragmentLastSavedSimulatorBinding
import com.androidninjas.finalaveragingsimulator.model.Simulator
import com.androidninjas.finalaveragingsimulator.ui.fragment.adapter.LastSavedSimulatorAdapter
import com.androidninjas.finalaveragingsimulator.util.ModelPreferencesManager
import com.androidninjas.finalaveragingsimulator.util.SwipeToDeleteCallback

class LastSavedSimulatorFragment : Fragment(){
    private var _binding: FragmentLastSavedSimulatorBinding? = null
    private val binding get() = _binding!!
    private var savedList = arrayListOf<Simulator>()
    private var prefs = ModelPreferencesManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLastSavedSimulatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

      val simulator = prefs.get<Simulator>("KEY_SIMULATOR")

        if (simulator != null) {
            savedList.add(simulator)
        }

        recyclerView.let {
            it.layoutManager = LinearLayoutManager(requireActivity())
            it.itemAnimator = DefaultItemAnimator()
            it.setHasFixedSize(true)
            it.adapter = LastSavedSimulatorAdapter(savedList)
        }
        setupSwipeToDeleteItem()
    }

    private fun setupSwipeToDeleteItem() {
        val swipeHandler = object: SwipeToDeleteCallback(requireActivity()) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as LastSavedSimulatorAdapter
                adapter.remoteAt(viewHolder.adapterPosition)
            }



        }

       val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun initializeViews() {
        recyclerView = binding.recyclerViewLastSavedSimulator
    }

}