package com.steve.advanced.hybrid;

/**
 * Created by steveyan on 16-10-9.
 */

public interface IHybridHandler {


    // 事件处理的类型
    int getHandlerType();

    void handleTask(IHybridCallBack callBack);

}
