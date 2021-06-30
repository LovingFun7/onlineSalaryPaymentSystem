package cn.edu.bnuz.yhy.system.employee;

import cn.edu.bnuz.yhy.system.calculation.ISalaryStrategy;
import cn.edu.bnuz.yhy.system.calculation.MonthSalaryStrategy;

public class MonthEmployee extends Employee{
    private double monthSalary;
    private MonthSalaryStrategy salaryStrategy;

    public MonthEmployee(String empName, int empId, String gender, String address, String bankId, String payment, double monthSalary) {
        super(empName, empId, gender, address, bankId, payment );
        this.monthSalary= monthSalary;
    }

    public MonthEmployee(String empName, int empId, String gender, String address, String bankId, String payment, String position, String department, double monthSalary) {
        super(empName, empId, gender, address, bankId, payment,  position, department);
        this.monthSalary= monthSalary;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void setSalaryStrategy(ISalaryStrategy salaryStrategy) {
        this.salaryStrategy = (MonthSalaryStrategy) salaryStrategy;
        this.salaryStrategy.setConsumption(getConsumption());
        this.salaryStrategy.setMonthSalary(monthSalary);
        setConsumption(0.0);
        salary = salaryStrategy.calculate();
    }

    public double getMonthSalary() {
        return monthSalary;
    }

    @Override
    public String toString() {
        return "MonthEmployee{" +
                "empName='" + empName + '\'' +
                ", empId=" + empId +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", bankId='" + bankId + '\'' +
                ", payment='" + payment + '\'' +
                ", salary=" + salary +
                ", consumption=" + consumption +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", monthSalary=" + monthSalary +
                ", salaryStrategy=" + salaryStrategy +
                '}';
    }
}
