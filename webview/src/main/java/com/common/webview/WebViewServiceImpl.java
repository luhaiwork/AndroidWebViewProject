package com.common.webview;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.common.webview.utils.Constants;
import com.google.auto.service.AutoService;
import com.synjones.common.autoService.IWebviewService;

@AutoService({IWebviewService.class})
public class WebViewServiceImpl implements IWebviewService {

    @Override
    public void startWebviewActivity(Context context, String url, String title,boolean isShowActionBar) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(Constants.TITLE, title);
            intent.putExtra(Constants.URL, url);
            intent.putExtra(Constants.IS_SHOW_ACTION_BAR, isShowActionBar);
            context.startActivity(intent);
        }
    }

    @Override
    public Fragment getWebViewFragment(String url,boolean canNativeRefresh) {
        return WebviewFragment.newInstance(url,canNativeRefresh);
    }
}
