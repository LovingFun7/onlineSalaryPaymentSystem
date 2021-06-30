package cn.edu.bnuz.yhy.system.calculation;

import cn.edu.bnuz.yhy.system.employee.SaleSlip;

import java.util.ArrayList;
import java.util.List;

public class CommitSaleStrategy implements ISalaryStrategy{
    //计算提成雇员工资 先获取提成雇员在上次发薪后的所有销售凭条，然后计算该发工资。
    private List<SaleSlip> salarySaleSlip ;
    private double baseSalary;
    private double saleSalary ;
    private double consumption ;

    public CommitSaleStrategy(){
        this.salarySaleSlip = new ArrayList<>();
        this.saleSalary =0;
        this.consumption = 0;
    }
    @Override
    public double calculate() {
        return calculateSalary()-calculateConsumption();
    }

    @Override
    public double calculateSalary() {
        //遍历需要支付工资的销售凭条，并按照比例计算工资
        for (SaleSlip salary : salarySaleSlip) {
            saleSalary += salary.getAchievement() * salary.getCommitRate();
        }
        return this.saleSalary + this.baseSalary;
    }

    @Override
    public double calculateConsumption() {
        return consumption;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setSalarySaleSlip(List<SaleSlip> salarySaleSlip) {
        this.salarySaleSlip = salarySaleSlip;
    }


    public void addConsumption(double consumption) {
        this.consumption +=consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption =consumption;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getSaleSalary() {
        return saleSalary;
    }
}
