// IWebviewprocessToMainProcessInterface.aidl
package com.common.webview;

// Declare any non-default types here with import statements

interface IWebviewprocessToMainProcessInterface {
    void handleWebComman(String commandName,String jsonParams);
}
