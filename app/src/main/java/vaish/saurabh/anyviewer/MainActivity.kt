package vaish.saurabh.anyviewer

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when{
            intent.action== Intent.ACTION_SEND->{
                if("text/plain"==intent.type){
                    etURL.setText(intent.getStringExtra(Intent.EXTRA_TEXT))
                    var stringUrl=""
                    webView.webViewClient= WebViewClient()
                    webView.settings.javaScriptEnabled=true
                    webView.settings.setSupportZoom(true)
                    webView.settings.builtInZoomControls=true
                    webView.settings.pluginState= WebSettings.PluginState.ON
                    webView.settings.allowFileAccess=true
                    search.setOnClickListener {

                        if(etURL.text.toString()!==""){
                            etURL.visibility=View.GONE
                            search.visibility=View.GONE
                            if(etURL.text.toString().contains("google.com")) {
                                Log.d("Indexof", etURL.text.toString().indexOf("url=").toString())
                                stringUrl = (etURL.text.toString()).substring(etURL.text.toString().indexOf("url=") + 4)
                                stringUrl = stringUrl.substring(0, stringUrl.indexOf("&"))
                                Log.d("Indexof", stringUrl)
                                webView.loadUrl("https://docs.google.com/viewer?url=$stringUrl")
                            }
                            else
                            {
                                webView.loadUrl("https://docs.google.com/viewer?url="+etURL.text.toString())
                            }
                        }
                    }

                }
            }
            else->{
                var stringUrl=""
                webView.webViewClient= WebViewClient()
                webView.settings.javaScriptEnabled=true
                webView.settings.allowFileAccess=true
                webView.settings.setSupportZoom(true)
                webView.settings.builtInZoomControls=true
                webView.settings.pluginState=WebSettings.PluginState.ON
                search.setOnClickListener {
                    if(etURL.text.toString()!==""){
//                stringUrl=etURL.text.toString().substring()
                        etURL.visibility=View.GONE
                        search.visibility=View.GONE
                        if(etURL.text.toString().contains("google.com")) {
                            Log.d("Indexof", etURL.text.toString().indexOf("url=").toString())
                            stringUrl = (etURL.text.toString()).substring(etURL.text.toString().indexOf("url=") + 4)
                            stringUrl = stringUrl.substring(0, stringUrl.indexOf("&"))
                            Log.d("Indexof", stringUrl)
                            webView.loadUrl("https://docs.google.com/viewer?url=$stringUrl")
                        }
                        else
                        {
                            webView.loadUrl("https://docs.google.com/viewer?url="+etURL.text.toString())
                        }
                    }
                }
            }

        }
    }

    override fun onBackPressed() {
        etURL.visibility=View.VISIBLE
        search.visibility=View.VISIBLE
        webView.loadUrl("about:blank")
        etURL.setText("")
    }
}

