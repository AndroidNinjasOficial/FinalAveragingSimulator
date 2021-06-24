package com.androidninjas.finalaveragingsimulator.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.androidninjas.finalaveragingsimulator.R
import com.androidninjas.finalaveragingsimulator.databinding.ActivityFormulaCalcWebViewBinding
import com.androidninjas.finalaveragingsimulator.util.setupToolbar

class FormulaCalcWebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormulaCalcWebViewBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormulaCalcWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar(R.id.toolbar, getString(R.string.title_activity_formula_calc_webView), true)
    }

    override fun onResume() {
        super.onResume()
        val progress = binding.progressBarFormulaCalcWebViewActivity
        progress.visibility = View.VISIBLE
        handler.postDelayed({
            progress.visibility = View.GONE
            binding.formulaCalcWebViewActivity.loadUrl("file:///android_asset/formulaCalcWebView.html")
        }, 2000)
    }
}