package cn.edu.bnuz.yhy.system.calculation;

public class MonthSalaryStrategy implements ISalaryStrategy{
    private double monthSalary;
    private double consumption;

    public MonthSalaryStrategy() {
        this.consumption = 0;
    }

    @Override
    public double calculate() {
        return calculateSalary() - calculateConsumption();
    }

    @Override
    public double calculateSalary() {
        return monthSalary;
    }

    @Override
    public double calculateConsumption() {
        return consumption;
    }

    public void setMonthSalary(double monthSalary) {
        this.monthSalary = monthSalary;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }
    public void addConsumption(double consumption) {
        this.consumption +=consumption;
    }
}
