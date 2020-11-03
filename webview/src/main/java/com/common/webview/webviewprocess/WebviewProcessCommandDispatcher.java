package com.common.webview.webviewprocess;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.common.webview.IWebviewprocessToMainProcessInterface;
import com.common.webview.mainprocess.MainprocessCommandService;
import com.synjones.base.BaseApplication;

public class WebviewProcessCommandDispatcher implements ServiceConnection {
    private static WebviewProcessCommandDispatcher sInstance;
    private IWebviewprocessToMainProcessInterface iWebviewprocessToMainProcessInterface;
    public static WebviewProcessCommandDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (WebviewProcessCommandDispatcher.class) {
                sInstance = new WebviewProcessCommandDispatcher();
            }
        }
        return sInstance;
    }

    public void initAidlConnection(){
        Intent intent = new Intent(BaseApplication.sApplication, MainprocessCommandService.class);
        BaseApplication.sApplication.bindService(intent,this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebviewprocessToMainProcessInterface=IWebviewprocessToMainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebviewprocessToMainProcessInterface=null;
        initAidlConnection();
    }

    public void executeCommand(String commandName,String params){
        if(iWebviewprocessToMainProcessInterface !=null){
            try {
                iWebviewprocessToMainProcessInterface.handleWebCommand(commandName,params);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
