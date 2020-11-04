package com.synjones.androidwebviewproject;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.common.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import com.common.webview.command.Command;
import com.google.auto.service.AutoService;
import com.synjones.base.BaseApplication;

import java.util.Map;

@AutoService({Command.class})
public class CommandShowToast implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map param, ICallbackFromMainprocessToWebViewProcessInterface callbackFromMainprocessToWebViewProcessInterface) {
        if(param!=null){
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> Toast.makeText(BaseApplication.sApplication,String.valueOf(param.get("message")),Toast.LENGTH_SHORT).show());
        }
    }
}
