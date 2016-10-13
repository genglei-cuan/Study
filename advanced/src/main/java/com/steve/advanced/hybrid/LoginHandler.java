package com.steve.advanced.hybrid;

/**
 * Created by yantinggeng on 2016/10/13.
 */

public class LoginHandler implements IHybridHandler {

    @Override
    public String getHandlerType() {
        return BybridEventType.Type_Login;
    }

    @Override
    public void handleTask() {

    }
}
