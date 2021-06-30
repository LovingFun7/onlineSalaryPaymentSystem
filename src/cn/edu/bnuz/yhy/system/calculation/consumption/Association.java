package cn.edu.bnuz.yhy.system.calculation.consumption;

import cn.edu.bnuz.yhy.system.employee.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Association {
    private static int id=1;
    private String associationName;
    private  int associationId ;
    private double joinSpend;//入会费
    private double weekSpend;//每周支付费用
    private List<Employee> member ;//协会成员
    private Map<Integer,AssociationService> services;//协会服务的内容和费用

    public Association(String associationName, double joinSpend, double weekSpend) {
        this.associationName = associationName;
        this.joinSpend = joinSpend;
        this.weekSpend = weekSpend;
        this.associationId = Association.id;
        Association.id++;
        member = new ArrayList<>();
        services = new HashMap<>();
    }

    //加入成员
    public void addMember(Employee employee) {
        member.add(employee);
        employee.addConsumption(joinSpend);
        System.out.println("员工" + employee.getEmpName() + "成功加入" + getAssociationName() + ",花费了" + getJoinSpend() + "元");

    }

    //每周费用
    public void weekSpendPay(Employee employee) {
        employee.addConsumption(weekSpend);
    }

    //添加服务
    public void addService(String name, String content, double price) {
        AssociationService as;
        as = new AssociationService(name,content,price,this);
        services.put(associationId,as);
        System.out.println("已添加" + name + "服务，服务内容为：" + content + "服务价格为：" + price);
    }

    //员工使用服务
    public void useService(Employee employee, int serviceId) {
        AssociationService as;
        as = services.get(serviceId);
        employee.addConsumption(as.getSpend());
        System.out.println("员工" + employee.getEmpName() + "已使用" + as.getServiceName() + "服务,共消费" + as.getSpend() + "元");
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public double getJoinSpend() {
        return joinSpend;
    }

    public void setJoinSpend(double joinSpend) {
        this.joinSpend = joinSpend;
    }

    public double getWeekSpend() {
        return weekSpend;
    }

    public void setWeekSpend(double weekSpend) {
        this.weekSpend = weekSpend;
    }

    public int getAssociationId() {
        return associationId;
    }
}
