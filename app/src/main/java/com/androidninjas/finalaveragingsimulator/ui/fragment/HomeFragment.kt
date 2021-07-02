package com.androidninjas.finalaveragingsimulator.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androidninjas.finalaveragingsimulator.R
import com.androidninjas.finalaveragingsimulator.databinding.FragmentHomeBinding


class HomeFragment : Fragment(){
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var btnStartSimulator: Button
    private lateinit var btnShowLastSavedSimulator: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        setupOnButtonsClick()
    }

    private fun initializeViews() {
        btnStartSimulator = binding.btnStartSimulator
        btnShowLastSavedSimulator = binding.btnShowLastSavedSimulator
    }

    private fun setupOnButtonsClick() {
        btnStartSimulator.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_toEntradaNotasFragment)
        }

        btnShowLastSavedSimulator.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_LastSavedSimulatorFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}