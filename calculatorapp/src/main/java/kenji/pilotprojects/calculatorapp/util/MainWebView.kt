package kenji.pilotprojects.calculatorapp.util

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

class MainWebView(context: Context) : WebView(context) {
    init {
        webViewClient = WebViewClient()
//        settings.apply {
//
//            javaScriptEnabled = true
//            allowFileAccessFromFileURLs = true
//            allowUniversalAccessFromFileURLs = true
//            setAppCacheEnabled(true)
//            allowFileAccess = true
//            cacheMode = WebSettings.LOAD_DEFAULT
//            setAppCachePath(context.cacheDir.absolutePath)
//            setAppCacheMaxSize(50 * 1024 * 1024)
//            loadWithOverviewMode = true
//            useWideViewPort = true
//        }
//        settings.apply {
//            setAppCacheEnabled(true)
//            cacheMode = WebSettings.LOAD_DEFAULT
//            setAppCachePath(context.cacheDir.absolutePath)
//            // ตั้งค่าอื่น ๆ ตามต้องการ
//            javaScriptEnabled = true
//            domStorageEnabled = true
//            // เพิ่มการตั้งค่าอื่น ๆ ตามความต้องการของคุณ
//        }
    }
}

@Composable
fun ComposeCustomWebView(url: String) {
    AndroidView(factory = { context ->
        MainWebView(context).apply {
            loadUrl(url)
        }
    })
}


//@Composable
//private fun WebViewContent() {
//    val mUrl = "https://www.photopea.com/"
//    AndroidView(
//        factory = {
//            WebView(it).apply {
//                layoutParams = ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//                )
//                webViewClient = WebViewClient()
//                loadUrl(mUrl)
//            }
//        }, update = {
//            it.loadUrl(mUrl)
//        }
//    )
//}


// webview_main!!.setBackgroundColor(android.graphics.Color.WHITE)
//        webview_main!!.setBackgroundResource(R.drawable.launch_screen)
//        webview_main!!.addJavascriptInterface(ContactToAndroid(this), "zapy")
//        webview_main!!.settings.apply {
//            javaScriptEnabled = true
//            allowFileAccessFromFileURLs = true
//            allowUniversalAccessFromFileURLs = true
//
//            setAppCacheEnabled(true)
//            allowFileAccess = true
//            cacheMode = WebSettings.LOAD_DEFAULT
//            setAppCachePath(activity!!.cacheDir.absolutePath)
//            setAppCacheMaxSize(50 * 1024 * 1024)
//            loadWithOverviewMode = true
//            useWideViewPort = true
//
//        }
////        webview_main!!.webViewClient = WebAppController {
////            webview_main!!.loadUrl(it)
////        }
//        webview_main!!.webViewClient = WebViewClient()
//
//        webview_main!!.webChromeClient = object : WebChromeClient() {
//            override fun onPermissionRequest(request: PermissionRequest?) {
//
//                request?.grant(request.resources)
////                super.onPermissionRequest(request)
//            }
//
//        }