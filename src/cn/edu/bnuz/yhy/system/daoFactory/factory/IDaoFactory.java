package cn.edu.bnuz.yhy.system.daoFactory.factory;

import cn.edu.bnuz.yhy.system.daoFactory.dao.IEmployeeDao;
import cn.edu.bnuz.yhy.system.daoFactory.dao.ISaleSlipDao;
import cn.edu.bnuz.yhy.system.daoFactory.dao.IWorkTimeCardDao;

public interface IDaoFactory {
    //	方法一：获取三个数据对象---利用对应的接口名
    IEmployeeDao getTimeEmployee();
    IEmployeeDao getMonthEmployee();
    IEmployeeDao getCommitSaleEmployee();
    ISaleSlipDao getSaleSlip();
    IWorkTimeCardDao getWorkTimeCard();
    //	方法二：获取三个数据对象---利用对应的接口名，从对象池中取出对应的对象
    Object getDao(String daoInterface);
}
