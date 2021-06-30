/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.bnuz.yhy.client;

import cn.edu.bnuz.yhy.client.proxy.HandlingCalls;
import cn.edu.bnuz.yhy.system.Call;
import cn.edu.bnuz.yhy.system.employee.CommitSaleEmployee;
import cn.edu.bnuz.yhy.system.employee.MonthEmployee;
import cn.edu.bnuz.yhy.system.employee.SaleSlip;
import cn.edu.bnuz.yhy.system.employee.TimeEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        SocketClient socketClient = SocketClient.getSocketClient();
        Scanner in = new Scanner(System.in);
        System.out.println("欢迎使用YHY的薪酬支付系统");
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        int a = 1;
        while (a != 0) {
            System.out.println("请选择需要操作的类型：1增加雇员，2删除雇员，3更改雇员信息，4查询雇员，5查询雇员薪酬，6工作时间卡操作，7销售凭条操作，0退出系统");
            String name;
            int empId;
            String gender;
            String address;
            String bankId;
            String payment;
            String position;//职位
            String department;//部门
            String employeeType;
            switch (a = in.nextInt()) {
                case 0:
                    break;
                case 1:
                    System.out.println("请输入增加干员的基础信息(名字，雇员号，性别，地址，银行卡号，支付方式，职位，部门)");
                    System.out.println("名字：");
                    name = in.next();
                    System.out.println("雇员号：");
                    empId = in.nextInt();
                    System.out.println("性别：");
                    gender = in.next();
                    System.out.println("地址：");
                    address = in.next();
                    System.out.println("银行卡号：");
                    bankId = in.next();
                    System.out.println("支付方式：");
                    payment = in.next();
                    System.out.println("职位：");
                    position = in.next();
                    ;
                    System.out.println("部门：");
                    department = in.next();
                    double monthSalary;
                    double hourlyWage;
                    double baseSalary;
                    Call call;
                    employeeType = "";
                    label:
                    while (!(employeeType.equals("时薪雇员") || employeeType.equals("月薪雇员") || employeeType.equals("提成雇员"))) {
                        System.out.println("请输入雇员类型:");
                        employeeType = in.next();
                        switch (employeeType) {
                            case "月薪雇员":
                                System.out.println("请输入雇员月薪:");
                                monthSalary = in.nextDouble();
                                MonthEmployee me = new MonthEmployee(name, empId, gender, address, bankId, payment, position, department, monthSalary);
                                call = HandlingCalls.handle(new Object[]{me}, "add", employeeType);
                                System.out.println((String) socketClient.callServer(call).getReturnObj());
                                break label;
                            case "日薪雇员":
                                System.out.println("请输入雇员时薪:");
                                hourlyWage = in.nextDouble();
                                TimeEmployee te = new TimeEmployee(name, empId, gender, address, bankId, payment, position, department, hourlyWage);
                                call = HandlingCalls.handle(new Object[]{te}, "add", employeeType);
                                System.out.println((String) socketClient.callServer(call).getReturnObj());
                                break label;
                            case "提成雇员":
                                System.out.println("请输入雇员基础工资:");
                                baseSalary = in.nextDouble();
                                CommitSaleEmployee cse = new CommitSaleEmployee(name, empId, gender, address, bankId, payment, position, department, baseSalary);
                                call = HandlingCalls.handle(new Object[]{cse}, "add", employeeType);
                                System.out.println((String) socketClient.callServer(call).getReturnObj());
                                break label;
                            default:
                                System.out.println("请输入正确的雇员类型！");
                        }
                    }
                    System.out.println("增加雇员成功，");
                    break;
                case 2:
                    System.out.println("请输入删除雇员的雇员类型：");
                    employeeType = in.next();
                    System.out.println("请输入删除雇员的雇员号：");
                    empId = in.nextInt();
                    call = HandlingCalls.handle(new Object[]{empId}, "delete", employeeType);
                    System.out.println((String) socketClient.callServer(call).getReturnObj());
                    break;
                case 3:
                    System.out.println("请输入更改雇员的雇员类型：");
                    employeeType = in.next();
                    System.out.println("请输入更改的雇员号: ");
                    empId = in.nextInt();
                    System.out.println("请需输入更改的属性: ");
                    String attribute = in.next();
                    System.out.println("请需输入更新的内容: ");
                    String newValue = in.next();
                    call = HandlingCalls.handle(new Object[]{empId, attribute, newValue}, "update", employeeType);
                    System.out.println((String) socketClient.callServer(call).getReturnObj());
                    break;
                case 4:
                    System.out.println("请输入查找雇员的雇员类型：");
                    employeeType = in.next();
                    System.out.println("请输入选择查找类型：1根据姓名查找，2根据雇员号查找");
                    switch (in.nextInt()) {
                        case 1:
                            System.out.println("请输入查找雇员的姓名：");
                            String empName = in.next();
                            call = HandlingCalls.handle(new Object[]{empName}, "query", employeeType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                            break;
                        case 2:
                            System.out.println("请输入查找雇员的雇员号：");
                            empId = in.nextInt();
                            call = HandlingCalls.handle(new Object[]{empId}, "detail", employeeType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                            break;
                    }
                    break;
                case 5:
                    System.out.println("请输入查找薪酬的雇员类型：");
                    employeeType = in.next();
                    System.out.println("请输入雇员的雇员号：");
                    empId = in.nextInt();
                    call = HandlingCalls.handle(new Object[]{empId}, "checkSalary", employeeType);
                    System.out.println((String) socketClient.callServer(call).getReturnObj());
                    break;
                case 6:
                    System.out.println("请输入工作时间卡的操作类型：1查找工作时间卡，2更改工作时间卡，3清空工作时间卡");
                    String daoType = "工作时间卡";
                    int whcId;
                    switch (in.nextInt()) {
                        case 1:
                            System.out.println("请输入查找时间卡的雇卡号：");
                            whcId = in.nextInt();
                            call = HandlingCalls.handle(new Object[]{whcId}, "detail", daoType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                        case 2:
                            System.out.println("请输入查找时间卡的卡号：");
                            whcId = in.nextInt();
                            System.out.println("请需输入更改的属性: ");
                            attribute = in.next();
                            System.out.println("请需输入更新的内容: ");
                            newValue = in.next();
                            call = HandlingCalls.handle(new Object[]{whcId, attribute, newValue}, "update", daoType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                        case 3:
                            System.out.println("请输入查清空时间卡的卡号：");
                            whcId = in.nextInt();
                            call = HandlingCalls.handle(new Object[]{whcId}, "clear", daoType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                    }
                case 7:
                    System.out.println("请输入操作类型：1增加单个销售凭条，2指定雇员增加销售凭条，3删除单个销售凭条，4删除指定雇员销售凭条，5更新销售凭条，6查找单个销售凭条，7查找雇员销售凭条");
                    daoType = "销售凭条";
                    int ssId;
                    SaleSlip saleSlip;
                    switch (in.nextInt()){
                        case 1:
                            System.out.println("请输入销售凭条号：");
                            ssId = in.nextInt();
                            System.out.println("请输入雇员名字：");
                            String empName = in.next();
                            System.out.println("请输入销售日期：");
                            String saleDay = in.next();
                            System.out.println("请输入销售金额：");
                            int achievement = in.nextInt();
                            System.out.println("请输入提成比例：");
                            double commitRate = in.nextDouble();
                            saleSlip = new SaleSlip(ssId,empName,saleDay,achievement,commitRate);
                            call = HandlingCalls.handle(new Object[]{saleSlip}, "add", daoType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                        case 2:
                            System.out.println("请输入雇员号：");
                            empId = in.nextInt();
                            call = HandlingCalls.handle(new Object[]{empId}, "checkSalary", "提成雇员");
                            CommitSaleEmployee cse= (CommitSaleEmployee)socketClient.callServer(call).getReturnObj();
                            System.out.println("请输入销售凭条详细信息：");
                            a =1;
                            List<SaleSlip> saleSlipList = new ArrayList<>();
                            while (a!=0){
                                System.out.println("请输入销售凭条号：");
                                ssId = in.nextInt();
                                System.out.println("请输入雇员名字：");
                                empName = in.next();
                                System.out.println("请输入销售日期：");
                                saleDay = in.next();
                                System.out.println("请输入销售金额：");
                                achievement = in.nextInt();
                                System.out.println("请输入提成比例：");
                                commitRate = in.nextDouble();
                                saleSlip = new SaleSlip(ssId,empName,saleDay,achievement,commitRate);
                                saleSlipList.add(saleSlip);
                                System.out.println("是否继续添加销售凭条，1继续，0终止");
                                a = in.nextInt();
                            }
                            call = HandlingCalls.handle(new Object[]{cse,saleSlipList}, "addEmployeeSlip", daoType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                        case 3:
                            System.out.println("请输入所需删除销售凭条号：");
                            ssId = in.nextInt();
                            call = HandlingCalls.handle(new Object[]{ssId}, "delete", daoType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                        case 4:
                            System.out.println("请输入所需删除雇员的雇员号：");
                            empId = in.nextInt();
                            call = HandlingCalls.handle(new Object[]{empId}, "deleteEmployee", daoType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                        case 5:
                            System.out.println("请输入所需更新的销售凭条号：");
                            ssId = in.nextInt();
                            System.out.println("请需输入更改的属性: ");
                            attribute = in.next();
                            System.out.println("请需输入更新的内容: ");
                            newValue = in.next();
                            call = HandlingCalls.handle(new Object[]{ssId, attribute, newValue}, "update", daoType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                        case 6:
                            System.out.println("请输入所需查找的销售凭条号：");
                            ssId = in.nextInt();
                            call = HandlingCalls.handle(new Object[]{ssId}, "detail", daoType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                        case 7:
                            System.out.println("请输入所需查找的销售凭条号的雇员的雇员号：");
                            empId = in.nextInt();
                            call = HandlingCalls.handle(new Object[]{empId}, "query", daoType);
                            System.out.println((String) socketClient.callServer(call).getReturnObj());
                    }
                default:
                    System.out.println("请输入正确的操作类型");
            }
        }
        System.out.println("只能是下次再见了");
    }
}
