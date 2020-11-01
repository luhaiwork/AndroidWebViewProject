package com.synjones.common.autoService;

import android.content.Context;

import androidx.fragment.app.Fragment;

//依赖倒置原则，依赖于接口不依赖于实现
public interface IWebviewService {
    void startWebviewActivity(Context context, String url, String title,boolean isShowActionBar);
    Fragment getWebViewFragment(String url,boolean canNativeRefresh);
    void startDemoHtml(Context context);
}
