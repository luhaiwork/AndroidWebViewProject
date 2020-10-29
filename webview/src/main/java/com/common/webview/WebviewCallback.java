package com.common.webview;

public interface WebviewCallback {
    void pageStarted(String url);

    void pageFinished(String url);

    void onError();
}
