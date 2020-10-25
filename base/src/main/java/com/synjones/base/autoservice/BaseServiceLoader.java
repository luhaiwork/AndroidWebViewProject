package com.synjones.base.autoservice;

import java.util.ServiceLoader;

public class BaseServiceLoader {
    private BaseServiceLoader(){

    }
    public static <S> S load(Class<S> service){
        try{
            return ServiceLoader.load(service).iterator().next();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
