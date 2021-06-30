/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.bnuz.yhy.client.proxy;

import cn.edu.bnuz.yhy.client.PropertiesUtil;
import cn.edu.bnuz.yhy.system.Call;
import com.sun.xml.internal.ws.wsdl.writer.document.ParamType;

public class HandlingCalls {
    //类名
    private static String clazzName;
    //方法名
    private static String methodName;
    //方法参数类型数组
    private static Class[] paramTypes;

    private static Object[] paramValues;

    private static Object returnObj;

    public static Call handle(Object[] objects, String handleType, String daoType) {
        paramValues = objects;
        Class[] a = new Class[objects.length];
        int i =0;
        for (Object object:
             objects) {
            a[i] = object.getClass();
            i++;
        }
        paramTypes = a;
        methodName  = handleType;
        returnObj = String.class;
        if (daoType.equals("月薪雇员")) {
            clazzName = PropertiesUtil.MonthEmployeeService;
        } else if (daoType.equals("时薪雇员")) {
            clazzName = PropertiesUtil.TimeEmployeeService;
        } else if (daoType.equals("提成雇员")) {
            clazzName = PropertiesUtil.CommitSaleEmployeeService;
        }else if(daoType.equals("工作时间卡")){
            clazzName = PropertiesUtil.WorkHourCardService;
        }else  if(daoType.equals("销售凭条")){
            clazzName = PropertiesUtil.SaleSlipService;
        }
        Call call = new Call(clazzName, methodName, paramTypes, paramValues, returnObj);
        return call;
    }

}
