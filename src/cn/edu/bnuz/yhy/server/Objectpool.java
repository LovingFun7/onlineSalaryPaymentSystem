package cn.edu.bnuz.yhy.server;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Objectpool {
    private Map<String,Object> objectpool = new HashMap<>();

    public Object getObject(String clazzName) {
        // TODO Auto-generated method stub
        try {
            Class clazz = Class.forName(clazzName);
            //得到相应的构造函数
            Constructor cons = clazz.getConstructor();
            Object object = cons.newInstance();
            objectpool.put(clazzName, object);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return objectpool.get(clazzName);
    }
}
