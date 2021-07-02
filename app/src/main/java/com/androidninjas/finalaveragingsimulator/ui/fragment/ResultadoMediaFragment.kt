package com.androidninjas.finalaveragingsimulator.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androidninjas.finalaveragingsimulator.R
import com.androidninjas.finalaveragingsimulator.databinding.FragmentResultadoMediaBinding
import com.androidninjas.finalaveragingsimulator.model.Simulator
import com.androidninjas.finalaveragingsimulator.util.*

class ResultadoMediaFragment : Fragment() {
    private var _binding: FragmentResultadoMediaBinding? = null
    private val binding get() = _binding!!
    private var prefs = ModelPreferencesManager
    private lateinit var averagingResult: TextView
    private lateinit var progress: ProgressBar
    private lateinit var labelAveragingResult: TextView
    private lateinit var warningResult: TextView
    private lateinit var imageApprovedWarningResult: ImageView
    private lateinit var imageDisapprovedWarningResult: ImageView
    private lateinit var btnBackToHome: Button
    private lateinit var btnSaveSimulator: Button
    private var isApproved = false
    private val handler = Handler(Looper.getMainLooper())
    private var averagingCalc = 0.0
    private var grade1 = 0.0
    private var grade2 = 0.0
    private var grade3 = 0.0
    private var grade4 = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultadoMediaBinding.inflate(inflater, container, false)
        arguments?.let {
            grade1 = it.getString("key1").toString().toDouble()
            grade2 = it.getString("key2").toString().toDouble()
            grade3 = it.getString("key3").toString().toDouble()
            grade4 = it.getString("key4").toString().toDouble()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        setupDisplayAverageResult()
        setupOnButtonsClick()
    }

    private fun setupDisplayAverageResult() {
        progress.show()
        handler.postDelayed({
            progress.hide()
            if (calculateAveraging() < AverageIdentifierEnum.MINIMUM_AVERAGE.value) {
                showDisapprovedResultDetails()
            } else {
                showApprovedResultDetails()
            }
            showAverageResult()
        }, 2000)
    }

    private fun showAverageResult() {
        averagingResult.text = getString(
            R.string.txt_averaging_result_fragment_resultado_media,
            calculateAveraging().toString()
        )
        labelAveragingResult.visibility = View.VISIBLE
        btnBackToHome.visibility = View.VISIBLE
        btnSaveSimulator.visibility = View.VISIBLE
    }

    private fun showApprovedResultDetails() {
        warningResult.let {
            it.text = getString(R.string.txt_approved_warning_result_fragment_resultado_media)
            it.setTextColor(Color.GREEN)
            it.visibility = View.VISIBLE
        }
        averagingResult.let {
            it.setTextColor(Color.GREEN)
            it.visibility = View.VISIBLE
        }
        imageApprovedWarningResult.visibility = View.VISIBLE
        isApproved = true
    }

    private fun showDisapprovedResultDetails() {
        warningResult.let {
            it.text = getString(R.string.txt_disapproved_warning_result_fragment_resultado_media)
            it.setTextColor(Color.RED)
            it.visibility = View.VISIBLE
        }
        averagingResult.let {
            it.setTextColor(Color.RED)
            it.visibility = View.VISIBLE
        }
        imageDisapprovedWarningResult.visibility = View.VISIBLE
        isApproved = false
    }

    private fun calculateAveraging(): Double {
        averagingCalc = (grade1 * GradeWeightEnum.GRADE_1_WEIGHT.value +
                grade2 * GradeWeightEnum.GRADE_2_WEIGHT.value +
                grade3 * GradeWeightEnum.GRADE_3_WEIGHT.value +
                grade4 * GradeWeightEnum.GRADE_4_WEIGHT.value) / 100
        return averagingCalc
    }

    private fun setupOnButtonsClick() {
        btnBackToHome.setOnClickListener {
            findNavController().navigate(R.id.action_ResultadoMediaFragment_to_HomeFragment)
        }

        btnSaveSimulator.setOnClickListener {
            prefs.insert(Simulator(grade1, grade2, grade3, grade4, calculateAveraging(), isApproved), "KEY_SIMULATOR")

            findNavController().navigate(R.id.action_ResultadoMediaFragment_to_LastSavedSimulatorFragment)
        }
    }

    private fun initializeViews() {
        averagingResult = binding.averagingResult
        progress = binding.progressBarResultadoMediaFragment
        labelAveragingResult = binding.labelAveragingResult
        warningResult = binding.warningResult
        imageApprovedWarningResult = binding.imageWarningResultApproved
        imageDisapprovedWarningResult = binding.imageWarningResultDisapproved
        btnBackToHome = binding.btnBackToHome
        btnSaveSimulator = binding.btnSaveSimulatorResult
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}