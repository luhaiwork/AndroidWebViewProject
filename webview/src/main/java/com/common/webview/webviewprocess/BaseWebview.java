package com.common.webview.webviewprocess;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.common.webview.WebviewCallback;
import com.common.webview.bean.JsParam;
import com.common.webview.webviewprocess.settings.WebviewDefaultSettings;
import com.common.webview.webviewprocess.webchromeclient.CommonWebChromeClient;
import com.common.webview.webviewprocess.webviewclient.CommonWebviewClient;
import com.google.gson.Gson;

public class BaseWebview extends WebView {
    private static final String TAG = BaseWebview.class.getSimpleName();

    public BaseWebview(Context context) {
        super(context);
        init();
    }

    public BaseWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        WebviewDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "commonwebview");
        WebviewProcessCommandDispatcher.getInstance().initAidlConnection();
    }

    public void registWebViewCallback(WebviewCallback webviewCallback) {
        setWebViewClient(new CommonWebviewClient(webviewCallback));
        setWebChromeClient(new CommonWebChromeClient(webviewCallback));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        Log.i(TAG, jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            final JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            if (jsParamObject != null) {
                WebviewProcessCommandDispatcher.getInstance().executeCommand(jsParamObject.name, new Gson().toJson(jsParamObject.param));
            }
        }
    }

}
