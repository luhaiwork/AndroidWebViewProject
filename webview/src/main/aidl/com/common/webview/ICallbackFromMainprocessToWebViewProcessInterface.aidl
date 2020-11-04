// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.common.webview;

// Declare any non-default types here with import statements

interface ICallbackFromMainprocessToWebViewProcessInterface {
    void onResult(String callbackName,String response);
}
