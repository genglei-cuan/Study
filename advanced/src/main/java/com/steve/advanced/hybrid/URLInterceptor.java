package com.steve.advanced.hybrid;

import android.support.v4.util.ArrayMap;

/**
 * url 拦截器,用于拦截 URL，确定需要Java层执行的行为。
 *
 * Created by steveyan on 16-10-9.
 */

public class URLInterceptor {

    private ArrayMap<String, IHybridHandler> handlers = new ArrayMap<>();

    public boolean intercept(String url) {
        IHybridHandler handler = handle(url);
        if (handler == null) {
            return false;
        }
        handler.handleTask();
        return true;
    }


    private IHybridHandler handle(String url) {
        String type = getTaskType(url);
        IHybridHandler handler = handlers.get(type);
        if (handler == null) {
            // TODO: 2016/10/13 new handler
            switch (type) {
                case BybridEventType.Type_Login:
                    handler = new LoginHandler();
                    handlers.put(BybridEventType.Type_Login, handler);
                    break;
            }
        }
        return null;
    }

    private String getTaskType(String url) {
        String type = BybridEventType.Type_Login;
        return type;
    }
}
