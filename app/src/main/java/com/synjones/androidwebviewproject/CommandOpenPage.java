package com.synjones.androidwebviewproject;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.common.webview.command.Command;
import com.google.auto.service.AutoService;
import com.synjones.base.BaseApplication;

import java.util.Map;
@AutoService({Command.class})
public class CommandOpenPage implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map param) {
        String targetClass = String.valueOf(param.get("target_class"));
        if(!TextUtils.isEmpty(targetClass)){
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication,targetClass));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
