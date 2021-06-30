package cn.edu.bnuz.yhy.system.employee;

import cn.edu.bnuz.yhy.system.calculation.ISalaryStrategy;

public abstract class Employee {
    protected String empName;
    protected int empId;
    protected String gender;
    protected String address;
    protected String bankId;//银行卡号
    protected String payment;//支付方式
    protected double salary;
    protected double consumption;
    protected String position;//职位
    protected String department;//部门

    //不含职位和部门
    public Employee(String empName, int empId, String gender, String address, String bankId, String payment) {
        this.empName = empName;
        this.empId = empId;
        this.gender = gender;
        this.address = address;
        this.bankId = bankId;
        this.payment = payment;
    }

    //含有职位和部门
    public Employee(String empName, int empId, String gender, String address, String bankId, String payment, String position, String department) {
        this.empName = empName;
        this.empId = empId;
        this.gender = gender;
        this.address = address;
        this.bankId = bankId;
        this.payment = payment;
        this.position = position;
        this.department = department;
    }

    public abstract double getSalary();

    public abstract void setSalaryStrategy(ISalaryStrategy salaryStrategy);

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public void addConsumption(double consumption){
        this.consumption+=consumption;
    }


    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
