package cn.edu.bnuz.yhy.system.daoFactory.factory;

import cn.edu.bnuz.yhy.system.daoFactory.dao.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SQLServerDaoFactory implements IDaoFactory {
    IEmployeeDao timeEmployeeDao = new TimeEmpDaoSqlServerImpl();
    IEmployeeDao commitSaleEmployeeDao = new ComSaleEmpDaoSqlServerImpl();
    IEmployeeDao monthEmployeeDao = new MonthEmpDaoSqlServerImpl();
    ISaleSlipDao saleSlipDao = new SaleSlipServerImpl();
    IWorkTimeCardDao workTimeCardDao = new WorkHourCardDaoSqlSeverImpl();

    //	声明自身的实例
    private static SQLServerDaoFactory ssdf = null;
    //	对象池---将从配置文件中取出来的对象，放在hashmap中
    private static Map<String, Object> daos = new HashMap<>();

    //私有构造函数
    private SQLServerDaoFactory(String daoconfig) {
        try {

            /*
             * 从xml文件读取
             */
            Class clazz=null;
            Object object = null;

            //1.创建SAX读取对象
            SAXReader reader = new SAXReader();
            //2.指定解析的xml源
            Document document = reader.read(new File(daoconfig));
            //3.得到元素
            Element rootElement = document.getRootElement();
            // System.out.println(rootElement);
            //获得根元素下面的所有子元素
            List<Element> elements =rootElement.elements();

            /*
             * 	按照接口的不同来组织xml文件，以及对应的读取操作
             */
            for(Element element:elements){
                //接口，一共有四种：ICarTypeDao、IOrderDao、ICustomerDao、ISellerDao
                //其中一种接口有多个实现类，根据设置实现类的id属性的不同，来解决取哪个实现类。要取的实现类的id设置为use
                //获取到接口名
                String interfaceName = element.attributeValue("interfaceName");
                String className = null;

                List<Element> listElement=element.elements();
                //遍历获取要实例化的类名
                for(Element e:listElement){
                    if((e.attributeValue("id")).equals("use"))  className = e.getText();
                }
                //利用反射技术，进行实例化
                clazz = Class.forName(className);
                object = clazz.newInstance();
                //将实例存入对象池
                daos.put(interfaceName, object);
//        			 System.out.println("className:"+className);
//        			 System.out.println("interfaceName:"+interfaceName);



            }

        }catch(Exception e){
            System.out.println("error!");
            e.printStackTrace();
        }

    }
    public SQLServerDaoFactory() {
        // TODO Auto-generated constructor stub
    }
    //单例方法,针对多线程的话，要使用同步方法
    public static synchronized SQLServerDaoFactory getInstance(String string) {
        // TODO Auto-generated method stub
        if(ssdf==null) ssdf = new SQLServerDaoFactory(string);
        return ssdf;
    }


    @Override
    public IEmployeeDao getTimeEmployee() {
        return this.timeEmployeeDao;
    }

    @Override
    public IEmployeeDao getMonthEmployee() {
        return this.monthEmployeeDao;
    }

    @Override
    public IEmployeeDao getCommitSaleEmployee() {
        return this.commitSaleEmployeeDao;
    }

    @Override
    public ISaleSlipDao getSaleSlip() {
        return this.saleSlipDao;
    }

    @Override
    public IWorkTimeCardDao getWorkTimeCard() {
        return this.workTimeCardDao;
    }

    @Override
    public Object getDao(String daoInterface) {
        Object obj = daos.get(daoInterface);
        return obj;
    }

}
