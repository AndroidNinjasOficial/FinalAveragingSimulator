package com.androidninjas.finalaveragingsimulator.ui.fragment.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidninjas.finalaveragingsimulator.MainApplication
import com.androidninjas.finalaveragingsimulator.R
import com.androidninjas.finalaveragingsimulator.model.Simulator
import com.androidninjas.finalaveragingsimulator.util.ModelPreferencesManager
import kotlinx.android.synthetic.main.cardview_item_last_saved_simulator.view.*

class LastSavedSimulatorAdapter(
    val simulators: MutableList<Simulator>
) : RecyclerView.Adapter<LastSavedSimulatorAdapter.LastSavedSimulatorViewHolder>() {
    private val appContext = MainApplication.getInstance().applicationContext
    private val prefs = ModelPreferencesManager

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = LastSavedSimulatorViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_item_last_saved_simulator, parent, false)
    )

    override fun onBindViewHolder(holder: LastSavedSimulatorViewHolder, position: Int) {
        val simulator = simulators[position]
        val view = holder.itemView

        with(view) {
            grade1_cardview.text = simulator.grade1.toString()
            grade2_cardview.text = simulator.grade2.toString()
            grade3_cardview.text = simulator.grade3.toString()
            grade4_cardview.text = simulator.grade4.toString()
            averaging_result_cardView.text = simulator.averaging.toString()

            when (simulator.isApproved) {
                true -> {
                    warning_result_cardview.let {
                        it.text = appContext.getString(R.string.txt_approved_warning_result_fragment_resultado_media)
                        it.setTextColor(Color.GREEN)
                    }
                    image_warning_result_approved_cardview.visibility = View.VISIBLE
                    averaging_result_cardView.setTextColor(Color.GREEN)
                }
                false -> {
                    warning_result_cardview.let {
                        it.text = appContext.getString(R.string.txt_disapproved_warning_result_fragment_resultado_media)
                        it.setTextColor(Color.RED)
                    }
                    image_warning_result_disapproved_cardview.visibility = View.VISIBLE
                    averaging_result_cardView.setTextColor(Color.RED)
                }
            }
        }

    }

    fun remoteAt(position: Int) {
        simulators.removeAt(position)
        notifyItemRemoved(position)
        prefs.remove("KEY_SIMULATOR")
    }

    override fun getItemCount() = simulators.size

    class LastSavedSimulatorViewHolder(view: View) : RecyclerView.ViewHolder(view)
}