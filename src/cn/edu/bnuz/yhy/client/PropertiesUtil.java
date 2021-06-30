package cn.edu.bnuz.yhy.client;

import cn.edu.bnuz.yhy.system.employee.WorkHourCard;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性工具类
 *
 */
public class PropertiesUtil {
    //属性列表
    private static Properties properties = new Properties();
    //配置文件的路径
    private static String CONFIG = "/cn/edu/bnuz/yhy/cfg/objectpool.properties";
    //读取资源文件, 设置输入流
    private static InputStream is ;

    public static  String TimeEmployeeService;
    public static  String MonthEmployeeService;
    public static String CommitSaleEmployeeService;
    public static String WorkHourCardService;
    public static String SaleSlipService;
/*    public static String MonthEmployee;
    public static String TimeEmployee;
    public static String CommitSaleEmployee;*/
    static {
        is = PropertiesUtil.class.getResourceAsStream(CONFIG);
        try {
            //加载输入流
            properties.load(is);
            TimeEmployeeService = properties.getProperty("TimeEmployeeService");
            MonthEmployeeService = properties.getProperty("MonthEmployeeService");
            CommitSaleEmployeeService = properties.getProperty("CommitSaleEmployeeService");
            WorkHourCardService = properties.getProperty("WorkHourCardService");
            SaleSlipService = properties.getProperty("SaleSlipService");
/*            MonthEmployee = properties.getProperty("MonthEmployee");
            TimeEmployee = properties.getProperty("TimeEmployee");
            CommitSaleEmployee = properties.getProperty("CommitSaleEmployee");*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

