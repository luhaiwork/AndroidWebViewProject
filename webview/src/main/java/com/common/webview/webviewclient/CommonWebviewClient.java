package com.common.webview.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
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

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (mWebviewCallBack != null) {
            mWebviewCallBack.onError();
        } else {
            Log.e(TAG, "webViewCallback is null.");
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if (mWebviewCallBack != null) {
            mWebviewCallBack.onError();
        } else {
            Log.e(TAG, "webViewCallback is null.");
        }
    }
}
