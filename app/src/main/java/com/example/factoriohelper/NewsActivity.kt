package com.example.factoriohelper

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class NewsActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        webView = findViewById(R.id.news_webview)
        webView.loadUrl("https://www.factorio.com/blog/")
    }
    override fun onDestroy() {
        super.onDestroy()
        webView.clearCache(true)
    }
}
