package com.steve.advanced.hybrid;

/**
 * 需要拦截 url 处理的通过实现这个接口实现
 *
 * Created by steveyan on 16-10-9.
 */

public interface IHybridHandler {

    // 事件处理的类型
    String getHandlerType();

    void handleTask();

}
