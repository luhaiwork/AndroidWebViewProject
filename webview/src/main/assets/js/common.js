var commonjs = {};
commonjs.os = {};
commonjs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
commonjs.os.isAndroid = !commonjs.os.isIOS;
commonjs.callbacks = {}

commonjs.callback = function (callbackname, response) {
   var callbackobject = commonjs.callbacks[callbackname];
   console.log("xxxx"+callbackname);
   if (callbackobject !== undefined){
       if(callbackobject.callback != undefined){
          console.log("xxxxxx"+response);
            var ret = callbackobject.callback(response);
           if(ret === false){
               return
           }
           delete xiangxuejs.callbacks[callbackname];
       }
   }
}
//commonjs.takeNativeAction = function(commandname, parameters){
//    console.log("commonjs takenativeaction")
//    var request = {};
//    request.name = commandname;
//    request.param = parameters;
//    if(window.commonjs.os.isAndroid){
//        console.log("android take native action" + JSON.stringify(request));
//        window.xiangxuewebview.takeNativeAction(JSON.stringify(request));
//    } else {
//        window.webkit.messageHandlers.xiangxuewebview.postMessage(JSON.stringify(request))
//    }
//}

commonjs.takeNativeAction=function(commandname,parameters){
        console.log("commonjs takenativeaction")
        var request={};
        request.name=commandname;
        request.param=parameters;
        if(window.commonjs.os.isAndroid){
        console.log("android take native action"+JSON.stringify(request))
            window.commonwebview.takeNativeAction(JSON.stringify(request));
        }else{
            //执行IOS方法 ios 交互,只能接受一个js参数
            window.webkit.messageHandlers.xiangxuewebview.postMessage(JSON.stringify(request))
        }
}

commonjs.takeNativeActionWithCallback = function(commandname, parameters, callback) {
    var callbackname = "nativetojs_callback_" +  (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
    commonjs.callbacks[callbackname] = {callback:callback};

    var request = {};
    request.name = commandname;
    request.param = parameters;
    request.param.callbackname = callbackname;
    if(window.commonjs.os.isAndroid){
        window.commonwebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.commonwebview.postMessage(JSON.stringify(request))
    }
}

window.commonjs = commonjs;
