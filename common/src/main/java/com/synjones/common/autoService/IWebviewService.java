package com.synjones.common.autoService;

import android.content.Context;

//依赖倒置原则，依赖于接口不依赖于实现
public interface IWebviewService {
    void startWebviewActivity(Context context, String url, String title,boolean isShowActionBar);
}
