package com.androidninjas.finalaveragingsimulator.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androidninjas.finalaveragingsimulator.R
import com.androidninjas.finalaveragingsimulator.databinding.FragmentEntradaNotasBinding
import com.androidninjas.finalaveragingsimulator.util.GradeIdentifierEnum
import com.androidninjas.finalaveragingsimulator.util.log
import com.androidninjas.finalaveragingsimulator.util.toast
import java.lang.Exception

class EntradaNotasFragment : Fragment() {

    private var _binding: FragmentEntradaNotasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntradaNotasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            validateGradesInputOnClickCalculate()
        } catch (e: Exception){
            requireActivity().log(e.message)
        }
    }

    private fun validateGradesInputOnClickCalculate() {

        binding.btnCalculateAverage.setOnClickListener {

            val grade1 = binding.grade1.text.toString()
            val grade2 = binding.grade2.text.toString()
            val grade3 = binding.grade3.text.toString()
            val grade4 = binding.grade4.text.toString()

            if ((grade1.isEmpty() && grade2.isEmpty()) && (grade3.isEmpty() && grade4.isEmpty())) {

               requireActivity().toast(getString(R.string.txt_empty_grades_error))

            } else {

                if ((grade1.toDouble() > GradeIdentifierEnum.MAXIMUM_GRADE.value ||
                            grade2.toDouble() > GradeIdentifierEnum.MAXIMUM_GRADE.value) ||
                    (grade3.toDouble() > GradeIdentifierEnum.MAXIMUM_GRADE.value ||
                            grade4.toDouble() > GradeIdentifierEnum.MAXIMUM_GRADE.value)
                ) {

                    requireActivity().toast(getString(R.string.txt_max_grade_error))
                } else {
                    openFragmentResultWithArguments(grade1, grade2, grade3, grade4)
                }


            }
        }

    }

    private fun openFragmentResultWithArguments(
        grade1: String,
        grade2: String,
        grade3: String,
        grade4: String
    ) {
        arguments = Bundle().apply {
            putString("key1", grade1)
            putString("key2", grade2)
            putString("key3", grade3)
            putString("key4", grade4)
        }

        findNavController().navigate(
            R.id.action_EntradaNotasFragment_to_ResultadoMediaFragment,
            arguments
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}