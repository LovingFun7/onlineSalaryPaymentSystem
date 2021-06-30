package cn.edu.bnuz.yhy.system.employee;

import cn.edu.bnuz.yhy.system.calculation.CommitSaleStrategy;
import cn.edu.bnuz.yhy.system.calculation.ISalaryStrategy;

import java.util.ArrayList;
import java.util.List;

public class CommitSaleEmployee extends Employee{
    private double baseSalary;
    private List<SaleSlip> saleSlipList ;
    private CommitSaleStrategy salaryStrategy;


    public CommitSaleEmployee(String empName, int empId, String gender, String address, String bankId, String payment, double baseSalary) {
        super(empName, empId, gender, address, bankId, payment);
        this.baseSalary=baseSalary;
        this.saleSlipList = new ArrayList<>();
    }

    public CommitSaleEmployee(String empName, int empId, String gender, String address, String bankId, String payment, String position, String department, double baseSalary) {
        super(empName, empId, gender, address, bankId, payment,  position, department);
        this.baseSalary = baseSalary;
        this.saleSlipList = new ArrayList<>();
    }


    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void setSalaryStrategy(ISalaryStrategy salaryStrategy) {
        this.salaryStrategy = (CommitSaleStrategy) salaryStrategy;
        this.salaryStrategy.setSalarySaleSlip(saleSlipList);
        this.salaryStrategy.setBaseSalary(baseSalary);
        this.salaryStrategy.addConsumption(getConsumption());
        setConsumption(0.0);
        salary = salaryStrategy.calculate();
        saleSlipList.clear();
    }

    public SaleSlip sale(int achievement,double commitRate){
        SaleSlip saleSlip ;
        saleSlip = SaleSlip.addSaleSlip(achievement,commitRate,this);
        saleSlipList.add(saleSlip);
        return saleSlip;
    }


    public double getBaseSalary() {
        return baseSalary;
    }


    public CommitSaleStrategy getSalaryStrategy() {
        return salaryStrategy;
    }


    @Override
    public String toString() {
        return "CommitSaleEmployee{" +
                "baseSalary=" + baseSalary +
                ", saleSlipList=" + saleSlipList +
                ", salaryStrategy=" + salaryStrategy +
                ", empName='" + empName + '\'' +
                ", empId=" + empId +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", bankId='" + bankId + '\'' +
                ", payment='" + payment + '\'' +
                ", salary=" + salary +
                ", consumption=" + consumption +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
