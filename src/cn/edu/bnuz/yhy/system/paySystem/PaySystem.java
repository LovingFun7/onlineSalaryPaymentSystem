/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.bnuz.yhy.system.paySystem;


import cn.edu.bnuz.yhy.system.calculation.CommitSaleStrategy;
import cn.edu.bnuz.yhy.system.calculation.MonthSalaryStrategy;
import cn.edu.bnuz.yhy.system.calculation.TimeSalaryStrategy;
import cn.edu.bnuz.yhy.system.daoFactory.dao.IEmployeeDao;
import cn.edu.bnuz.yhy.system.daoFactory.dao.ISaleSlipDao;
import cn.edu.bnuz.yhy.system.daoFactory.dao.IWorkTimeCardDao;
import cn.edu.bnuz.yhy.system.daoFactory.factory.IDaoFactory;
import cn.edu.bnuz.yhy.system.daoFactory.factory.SQLServerDaoFactory;
import cn.edu.bnuz.yhy.system.employee.CommitSaleEmployee;
import cn.edu.bnuz.yhy.system.employee.MonthEmployee;
import cn.edu.bnuz.yhy.system.employee.TimeEmployee;

import java.util.Calendar;
import java.util.List;

public class PaySystem {
    private static double deposit = 9999999;//用于发放现金的存款
    private static PaySystem paySystem;
    private final IDaoFactory daoFactory;
    private final IEmployeeDao meDao;
    private final IEmployeeDao cseDao;
    private final IEmployeeDao teDao;
    private final ISaleSlipDao ssDao;
    private final IWorkTimeCardDao whcDao;

    //单例模式，仅创建一个支付系统，该系统应该是独占一条线程，到点准时运行，在运行时一直监视着时间以及时支付薪水
    private PaySystem() {
        daoFactory = new SQLServerDaoFactory();
        meDao = daoFactory.getMonthEmployee();
        cseDao = daoFactory.getCommitSaleEmployee();
        teDao = daoFactory.getTimeEmployee();
        ssDao = daoFactory.getSaleSlip();
        whcDao = daoFactory.getWorkTimeCard();
    }


    public static PaySystem getPaySystem() {
        if (paySystem == null) {
            paySystem = new PaySystem();
        }
        return paySystem;
    }

    //运行发放程序
    public void run() {
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            payForTimeEmployee();
        } else if (now.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && now.get(Calendar.WEEK_OF_YEAR) % 2 == 1) {
            payForCommitEmployee();
        } else if (now.get(Calendar.DAY_OF_MONTH) == 1) {  //最后一天不太会，就用了第一天了
            payForMonthEmployee();
        }
    }

    //发放时薪雇员工资
    private void payForTimeEmployee() {
        List<TimeEmployee> timeEmployees = teDao.query("");
        TimeSalaryStrategy timeSalaryStrategy = new TimeSalaryStrategy();
        //利用迭代器遍历所有时薪雇员
        for (TimeEmployee te : timeEmployees) {
            te.setSalaryStrategy(timeSalaryStrategy);//计算单个时薪雇员该支付的工资
            //此处应有一个打钱 请问可以给我打一下吗
            deposit -= te.getSalary();
            System.out.println("员工" + te.getEmpName() + "的工资已结算！" + "共" + te.getSalary() + "元");
            System.out.println("总工时：" + timeSalaryStrategy.getwTime() + " 消费前工资：" +
                    timeSalaryStrategy.calculateSalary()+ " 共消费：" +timeSalaryStrategy.calculateConsumption() +
                    " 支付方式为:" + te.getPayment());
        }
    }

    //发放月薪雇员工资
    private void payForMonthEmployee() {
        List<MonthEmployee> monthEmployees = meDao.query("");
        MonthSalaryStrategy monthSalaryStrategy = new MonthSalaryStrategy();
        //利用迭代器遍历所有时薪雇员
        for (MonthEmployee me :monthEmployees) {
            me.setSalaryStrategy(monthSalaryStrategy);
            deposit -= me.getSalary();
            System.out.println("员工" + me.getEmpName() + "的工资已结算！" + "共" + me.getSalary() + "元");
            System.out.println( " 消费前工资：" +monthSalaryStrategy.calculateSalary()+ " 共消费："
                    +monthSalaryStrategy.calculateConsumption() +" 支付方式为:" + me.getPayment());
        }
    }

    //发放提成雇员工资
    private void payForCommitEmployee() {
        List<CommitSaleEmployee> commitSaleEmployees = cseDao.query("");
        CommitSaleStrategy commitSaleStrategy = new CommitSaleStrategy();
        //利用迭代器遍历所有时薪雇员
        for (CommitSaleEmployee cse :commitSaleEmployees) {
            cse.setSalaryStrategy(commitSaleStrategy);
            deposit -= cse.getSalary();
            System.out.println("员工" + cse.getEmpName() + "的工资已结算！" + "共" + cse.getSalary() + "元");
            System.out.println(" 消费前工资：" +commitSaleStrategy.calculateSalary()+ " 基础工资为："
                    +commitSaleStrategy.getBaseSalary() +" 提成工资为："+ commitSaleStrategy.getSaleSalary()
                    +" 共消费："+commitSaleStrategy.calculateConsumption() +" 支付方式为:" + cse.getPayment());
        }
    }

    public static double getDeposit() {
        return deposit;
    }

    public static void setDeposit(double deposit) {
        PaySystem.deposit = deposit;
    }
}
