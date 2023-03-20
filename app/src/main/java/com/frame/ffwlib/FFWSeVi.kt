package com.frame.ffwlib

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class FFWSeVi(activity: AppCompatActivity) : WebView(activity) {
    init {
        val webSettings = settings
        webSettings.apply {
            setSupportMultipleWindows(true)
            allowFileAccess = true
            allowContentAccess = true
            domStorageEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            userAgentString = System.getProperty("http.agent") ?: ""
            @SuppressLint("SetJavaScriptEnabled")
            javaScriptEnabled = true
        }
        super.setWebViewClient(WebViewClient())
    }
}