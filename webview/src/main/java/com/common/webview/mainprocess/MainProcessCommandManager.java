package com.common.webview.mainprocess;

import android.os.RemoteException;

import com.common.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import com.common.webview.IWebviewprocessToMainProcessInterface;
import com.common.webview.command.Command;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class MainProcessCommandManager extends IWebviewprocessToMainProcessInterface.Stub{
    private static MainProcessCommandManager sInstance;

    public static MainProcessCommandManager getInstance(){
        if(sInstance==null){
            synchronized (MainProcessCommandManager.class){
                sInstance=new MainProcessCommandManager();
            }
        }
        return sInstance;
    }
    private Map<String,Command> mCommands=new HashMap();
    private MainProcessCommandManager(){
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for(Command command:serviceLoader){
            if(!mCommands.containsKey(command.name())){
                mCommands.put(command.name(),command);
            }
        }
    }
    public void executeCommand(String commandName, Map param, ICallbackFromMainprocessToWebViewProcessInterface callbackFromMainprocessToWebViewProcessInterface){
        mCommands.get(commandName).execute(param,callbackFromMainprocessToWebViewProcessInterface);
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams, ICallbackFromMainprocessToWebViewProcessInterface callbackFromMainprocessToWebViewProcessInterface) throws RemoteException {
//        MainProcessCommandManager.getInstance().
        executeCommand(commandName,new Gson().fromJson(jsonParams,Map.class),callbackFromMainprocessToWebViewProcessInterface);
    }
}
