package com.common.webview.command;

import com.common.webview.ICallbackFromMainprocessToWebViewProcessInterface;

import java.util.Map;

public interface Command {
    String name();
    void execute(Map param, ICallbackFromMainprocessToWebViewProcessInterface callbackFromMainprocessToWebViewProcessInterface);
}
