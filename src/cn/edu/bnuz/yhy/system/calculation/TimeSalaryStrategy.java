package cn.edu.bnuz.yhy.system.calculation;

public class TimeSalaryStrategy implements ISalaryStrategy{
    private double wTime;
    private double consumption;
    private double hourlyWage;

    public TimeSalaryStrategy() {
        this.consumption = 0;
    }

    @Override
    public double calculate() {
        return calculateSalary()-calculateConsumption();
    }

    @Override
    public double calculateSalary() {
        return wTime*hourlyWage;
    }

    @Override
    public double calculateConsumption() {
        return consumption;
    }

    public void setwTime(double wTime) {
        this.wTime =wTime;
    }

    public void setSalaryUnit(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public void addConsumption(double consumption) {
        this.consumption +=consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption =consumption;
    }

    public double getwTime() {
        return wTime;
    }
}
