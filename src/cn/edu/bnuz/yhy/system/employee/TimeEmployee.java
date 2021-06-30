package cn.edu.bnuz.yhy.system.employee;

import cn.edu.bnuz.yhy.system.calculation.ISalaryStrategy;
import cn.edu.bnuz.yhy.system.calculation.TimeSalaryStrategy;

public class TimeEmployee extends Employee{

    private double hourlyWage;//时薪
    private TimeSalaryStrategy salaryStrategy;
    private WorkHourCard workHourCard;

    public TimeEmployee(String empName, int empId, String gender, String address, String bankId, String payment,  double hourlyWage) {
        super(empName, empId, gender, address, bankId, payment);
        this.hourlyWage=hourlyWage;
        this.workHourCard =new WorkHourCard(empId,empName);
    }

    public TimeEmployee(String empName, int empId, String gender, String address, String bankId, String payment, String position, String department, double hourlyWage) {
        super(empName, empId, gender, address, bankId, payment,  position, department);
        this.hourlyWage=hourlyWage;
        this.workHourCard =new WorkHourCard(empId,empName);
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void setSalaryStrategy(ISalaryStrategy salaryStrategy) {
        this.salaryStrategy = (TimeSalaryStrategy) salaryStrategy;
        this.salaryStrategy.setwTime(workHourCard.getwTime());//设置时间
        this.salaryStrategy.setSalaryUnit(hourlyWage);//设置单位薪水
        this.salaryStrategy.setConsumption(getConsumption());
        setConsumption(0.0);//清空待结算消费
        workHourCard.clearWaitWorkCard();//清空待结算工时
        salary = this.salaryStrategy.calculate();
    }

    public WorkHourCard getWorkHourCard() {
        return workHourCard;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setWorkHourCard(WorkHourCard workHourCard) {
        this.workHourCard = workHourCard;
    }


    @Override
    public String toString() {
        return "TimeEmployee{" +
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
                ", hourlyWage=" + hourlyWage +
                ", salaryStrategy=" + salaryStrategy +
                ", allWTime=" + workHourCard.getAllWTime()+
                ", allRwTime" + workHourCard.getAllRwTime() +
                '}';
    }
}
