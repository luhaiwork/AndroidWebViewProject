// IWebviewprocessToMainProcessInterface.aidl
package com.common.webview;

// Declare any non-default types here with import statements

interface IWebviewprocessToMainProcessInterface {
    void handleWebCommand(String commandName,String jsonParams);
}
