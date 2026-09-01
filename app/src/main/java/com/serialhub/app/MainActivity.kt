package com.serialhub.app

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout

class MainActivity : Activity() {

    private lateinit var webView: WebView
    private var fullscreenView: View? = null
    private var fullscreenContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = true
            loadWithOverviewMode = true
            useWideViewPort = true
        }

        webView.webViewClient = WebViewClient()

        webView.webChromeClient = object : WebChromeClient() {

            override fun onShowCustomView(
                view: View?,
                callback: CustomViewCallback?
            ) {
                if (view == null) return

                fullscreenView = view

                fullscreenContainer = FrameLayout(this@MainActivity)
                fullscreenContainer!!.setBackgroundColor(
                    android.graphics.Color.BLACK
                )

                fullscreenContainer!!.addView(
                    view,
                    FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                )

                setContentView(fullscreenContainer)

                requestedOrientation =
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onHideCustomView() {
                fullscreenContainer?.removeAllViews()

                fullscreenContainer = null
                fullscreenView = null

                setContentView(webView)

                requestedOrientation =
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }

        setContentView(webView)

        webView.loadUrl("https://serialhub12.blogspot.com/")
    }

    override fun onBackPressed() {

        if (fullscreenView != null) {
            webView.webChromeClient?.let {
                if (it is WebChromeClient) {
                    // Fullscreen will be closed by the player.
                }
            }

            fullscreenContainer?.removeAllViews()
            fullscreenContainer = null
            fullscreenView = null

            setContentView(webView)

            requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            return
        }

        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }
}
