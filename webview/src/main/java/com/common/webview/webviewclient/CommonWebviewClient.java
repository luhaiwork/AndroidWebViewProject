package com.common.webview.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.common.webview.WebviewCallback;

public class CommonWebviewClient extends WebViewClient {
    private WebviewCallback mWebviewCallBack;
    private static final String TAG = "CommonWebviewClient";

    public CommonWebviewClient(WebviewCallback callback) {
        mWebviewCallBack = callback;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (mWebviewCallBack != null) {
            mWebviewCallBack.pageStarted(url);
        } else {
            Log.e(TAG, "webViewCallback is null.");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (mWebviewCallBack != null) {
            mWebviewCallBack.pageFinished(url);
        } else {
            Log.e(TAG, "webViewCallback is null.");
        }
    }
}
