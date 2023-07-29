package com.example.factoriohelper

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.factoriohelper.databinding.ActivityCalcBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("https://kirkmcdonald.github.io/calc.html#data=1-1-19&items=")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webView.clearCache(true)
    }
}