package com.example.mysololife.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import com.example.mysololife.R

class ContentShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_show)

        val url = intent.getStringExtra("url")
        Toast.makeText(this, url, Toast.LENGTH_LONG).show()

        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl(url.toString())
    }
}