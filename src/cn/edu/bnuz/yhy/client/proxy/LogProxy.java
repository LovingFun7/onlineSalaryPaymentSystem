/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.bnuz.yhy.client.proxy;

import cn.edu.bnuz.yhy.system.Call;

import java.lang.reflect.Proxy;

public class LogProxy {
    public static Object getProxyInstance(Call call) {
        LogHandler lh = new LogHandler(call);
        Object object = Proxy.newProxyInstance(
                call.getClass().getClassLoader(),
                call.getClass().getInterfaces(),
                lh);
        return object;
    }
}
