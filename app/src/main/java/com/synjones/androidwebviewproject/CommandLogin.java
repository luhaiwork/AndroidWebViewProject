package com.synjones.androidwebviewproject;

import android.content.Intent;
import android.os.RemoteException;

import com.common.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import com.common.webview.command.Command;
import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.synjones.androidwebviewproject.event.LoginEvent;
import com.synjones.base.BaseApplication;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

@AutoService({Command.class})
public class CommandLogin implements Command {
    private String callbackName = "";
    private ICallbackFromMainprocessToWebViewProcessInterface callback;

    @Override
    public String name() {
        return "login";
    }

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void execute(Map param, ICallbackFromMainprocessToWebViewProcessInterface callbackFromMainprocessToWebViewProcessInterface) {
            callbackName = (String) param.get("callbackname");
            callback = callbackFromMainprocessToWebViewProcessInterface;
            Intent intent = new Intent(BaseApplication.sApplication, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
    }

    @Subscribe
    public void onLoginResult(LoginEvent loginEvent) {
        if (callback != null) {
            HashMap map = new HashMap();
            map.put("accountName", loginEvent.name);
            try {
                callback.onResult(callbackName,new Gson().toJson(map));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
