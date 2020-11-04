// IWebviewprocessToMainProcessInterface.aidl

package com.common.webview;
import com.common.webview.ICallbackFromMainprocessToWebViewProcessInterface;
// Declare any non-default types here with import statements

interface IWebviewprocessToMainProcessInterface {
    void handleWebCommand(String commandName,String jsonParams,ICallbackFromMainprocessToWebViewProcessInterface callback);
}
