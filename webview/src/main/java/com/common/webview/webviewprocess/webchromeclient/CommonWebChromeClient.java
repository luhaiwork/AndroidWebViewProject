package com.common.webview.webviewprocess.webchromeclient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.common.webview.WebviewCallback;

public class CommonWebChromeClient extends WebChromeClient {
    private WebviewCallback mWebviewCallback;
    private  static  final String TAG=CommonWebChromeClient.class.getSimpleName();
    public CommonWebChromeClient(WebviewCallback webviewCallback){
        mWebviewCallback=webviewCallback;
    }
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if(mWebviewCallback!=null){
            mWebviewCallback.updateTitle(title);
        }else{
            Log.e(TAG,"mWebviewCallback is null");
        }
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(TAG,consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
