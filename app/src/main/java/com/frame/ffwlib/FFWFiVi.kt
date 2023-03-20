package com.frame.ffwlib

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FFWFiVi(activity: AppCompatActivity) : WebView(activity) {
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

        super.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?,
            ): Boolean {
                val link = request?.url?.toString() ?: ""
                if (link.startsWith("https://") || link.startsWith("http://")) {
                    view?.loadUrl(link)
                    return true
                } else {
                    val packageManager: PackageManager = activity.packageManager
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    val resolvedActivity : ResolveInfo? = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
                    if (resolvedActivity == null) {
                        Toast.makeText(activity, "This application not found", Toast.LENGTH_SHORT).show()
                    } else {
                        activity.startActivity(intent)
                    }
                    return true
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                CookieManager.getInstance().flush()
            }
        })
    }


    fun fLoad(link: String) {
        super.loadUrl(link)
    }
}