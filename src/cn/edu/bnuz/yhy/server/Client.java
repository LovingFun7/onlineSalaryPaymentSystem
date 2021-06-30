


package cn.edu.bnuz.yhy.server;


import cn.edu.bnuz.yhy.system.jdbc.PropertiesUtil;
import cn.edu.bnuz.yhy.system.paySystem.PaySystem;
import cn.edu.bnuz.yhy.system.daoFactory.dao.IEmployeeDao;
import cn.edu.bnuz.yhy.system.daoFactory.dao.ISaleSlipDao;
import cn.edu.bnuz.yhy.system.daoFactory.dao.SaleSlipServerImpl;
import cn.edu.bnuz.yhy.system.daoFactory.factory.IDaoFactory;
import cn.edu.bnuz.yhy.system.daoFactory.factory.SQLServerDaoFactory;
import cn.edu.bnuz.yhy.system.employee.CommitSaleEmployee;
import cn.edu.bnuz.yhy.system.employee.MonthEmployee;
import cn.edu.bnuz.yhy.system.employee.SaleSlip;
import cn.edu.bnuz.yhy.system.employee.TimeEmployee;
import cn.edu.bnuz.yhy.system.paySystem.ThreadPaySystem;

import java.util.List;


public class Client {

    public static void main(String[] args) {
//        测试Dao工厂模式
        checkDaoFactory();

//
//
////        运行薪酬支付系统
        PaySystem paySystem = PaySystem.getPaySystem();
        ThreadPaySystem psRunnable = new ThreadPaySystem(paySystem);
        new Thread(psRunnable).start();

        /*开启端口，监听客服端所发送内容
        为了能监听多个客服端发起的请求，使用多线程
        线程同步不太会，可能会出现同时删除同一个雇员的问题*/
        new SocketServer();
    }

    public static void checkDaoFactory(){
        //      1. DAO工厂模式
        System.out.println("-----------------------------------------");
        System.out.println(" 1. DAO工厂模式");
        IDaoFactory daoFactory = new SQLServerDaoFactory();
//      (1)调用月薪雇员数据库
        System.out.println("(1)调用月薪雇员数据库");
        IEmployeeDao monthEmployeeDao = daoFactory.getMonthEmployee();

//       新建一个月薪雇员(名字，雇员号，性别，地址，银行卡号，支付方式，职位，部门，月薪)
        MonthEmployee me = new MonthEmployee("余昊阳", 19010319, "男", "京5 655",
                "13579", "银行卡支付", "学生", "信院", 7777);

//      ①将雇员信息导入数据库中
        monthEmployeeDao.add(me);
        /*②修改某个雇员的某个属性
         *（员工对象，要修改的属性（表中的列名），属性对应的列数，修改后的成什么）
         *1:empName
         *2:empId
         *3:gender
         *4:address
         *5:bankId
         *6:payment
         *7:position
         *8:department
         *9:monthSalary
         */
        monthEmployeeDao.update(me, "empName", 1, "yuhaoyang");
//      ③查询某个员工
        //通过id进行准确查找
        me = (MonthEmployee) monthEmployeeDao.detail(19010319);
        System.out.println(me.toString());

        //通过名字进行模糊查找
        List<MonthEmployee> mes =  monthEmployeeDao.query("yuhaoyang");
        System.out.println(mes.toString());


//      ④通过员工id查找员工发放的各次工资
        monthEmployeeDao.checkSalary(19010319);

//      ⑤删除某个雇员信息
        monthEmployeeDao.delete(19010319);


//      (2)调用时薪雇员数据库（在调用时薪雇员数据库时，同时调用了工作时间卡数据库）
        System.out.println("(2)调用时薪雇员数据库");
        IEmployeeDao timeEmployeeDao = daoFactory.getTimeEmployee();

//       新建一个时薪雇员(名字，雇员号，性别，地址，银行卡号，支付方式，职位，部门，月薪)
        TimeEmployee te = new TimeEmployee("余日天", 20010019, "男", "京5 655",
                "02468", "现金支付", "学生", "信院", 40);

//      ①将雇员信息导入数据库中
        timeEmployeeDao.add(te);
        /*②修改某个雇员的某个属性
         *（员工对象，要修改的属性（表中的列名），属性对应的列数，修改后的成什么）
         *1:empName
         *2:empId
         *3:gender
         *4:address
         *5:bankId
         *6:payment
         *7:position
         *8:department
         *9:hourlyWage*/

        timeEmployeeDao.update(te,"gender",3,"女");
//        ③查询某个员工
        //通过id进行准确查找
        te = (TimeEmployee) timeEmployeeDao.detail(20010019);
        System.out.println(te.toString());

        //通过名字进行模糊查找
        List<TimeEmployee> tes = timeEmployeeDao.query("余日天");
        System.out.println(tes.toString());

//        ④通过员工id查找员工发放的各次工资
        timeEmployeeDao.checkSalary(20010019);
//        ⑤删除某个雇员信息
        timeEmployeeDao.delete(20010019);




//      (3)调用提成雇员数据库
        System.out.println("(3)调用提成雇员数据库");
        IEmployeeDao cseDao = daoFactory.getCommitSaleEmployee();

//       新建一个时薪雇员(名字，雇员号，性别，地址，银行卡号，支付方式，职位，部门，月薪)
        CommitSaleEmployee cse = new CommitSaleEmployee("余日天阳", 21010091, "男", "京5 655",
                "123456", "邮寄支票", "学生", "信院", 6000);

//      ①将雇员信息导入数据库中
        cseDao.add(cse);
       /* ②修改某个雇员的某个属性
         *（员工对象，要修改的属性（表中的列名），属性对应的列数，修改后的成什么）
         *1:empName
         *2:empId
         *3:gender
         *4:address
         *5:bankId
         *6:payment
         *7:position
         *8:department
         *9:baseSalary*/

        cseDao.update(cse,"bankId",5,"123456");
//        ③查询某个员工
        //通过id进行准确查找
        cse= (CommitSaleEmployee) cseDao.detail(21010091);
        System.out.println(cse.toString());

        //通过名字进行模糊查找
        List<CommitSaleEmployee> cses=  cseDao.query("余日天阳");
        System.out.println(cses.toString());

//        ④通过员工id查找员工发放的各次工资
        cseDao.checkSalary(21010091);

//       (3.1)调用销售凭条数据库
        ISaleSlipDao saleSlipDao = new SaleSlipServerImpl();
//       雇员销售了一件物品,产生销售凭条
        SaleSlip saleSlip = cse.sale(200000, 0.05);
//       将销售凭条导入到数据库
        saleSlipDao.add(saleSlip);
//        查找雇员销售凭条
        List<SaleSlip> saleSlips= saleSlipDao.query("余日天阳");
        System.out.println(saleSlips.toString());

//        删除雇员信息，同时会删除该雇员的销售凭条
        cseDao.delete(21010091);



    }

}
