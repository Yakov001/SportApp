package com.example.sportapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.sportapp.R


class WebViewFragment : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.web_view_fragment, container, false)

        val myWebView: WebView = view.findViewById(R.id.webview)
        myWebView.settings.javaScriptEnabled = true
        myWebView.loadUrl("https://www.fifa.com/")

        return view
    }
}