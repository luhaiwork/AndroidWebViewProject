package com.synjones.androidwebviewproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.synjones.base.autoservice.BaseServiceLoader
import com.synjones.common.autoService.IWebviewService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        open_webviewactivity.setOnClickListener {
            //ServiceLoad.load 可以提取到base中
//            ServiceLoader.load(IWebviewService::class.java).iterator().next()
//                ?.startWebviewActivity(this, "", "百度")
//            BaseServiceLoader.load(IWebviewService::class.java)
//                ?.startWebviewActivity(this, "https://www.baidu.com", "百度",true)
            BaseServiceLoader.load(IWebviewService::class.java)?.startDemoHtml(this)
        }

    }
}