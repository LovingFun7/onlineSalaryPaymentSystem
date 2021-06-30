/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.edu.bnuz.yhy.system.daoFactory.dao;

import cn.edu.bnuz.yhy.system.employee.Employee;
import cn.edu.bnuz.yhy.system.jdbc.JDBCExecutor;

import java.util.List;

public class EmployeeDaoSqlServerImpl implements IEmployeeDao{

    private JDBCExecutor executor;

    public EmployeeDaoSqlServerImpl() {
        executor = JDBCExecutor.getJDBCExecutor();
    }

    @Override
    public void add(Employee employee) {
        executor.executeUpdate("insert into Employee(empName, empId, gender, address, bankId, payment, " +
                " position, department) values('"
                + employee.getEmpName() + "','" + employee.getEmpId() + "','"
                + employee.getGender()+ "','" + employee.getAddress() + "','"
                + employee.getBankId() + "','" + employee.getPayment() + "','"
                + employee.getPosition() + "','" + employee.getDepartment()  + "' )  ");
    }

    @Override
    public void delete(int empId) {
        executor.executeUpdate("delete from Employee where empId= '"
                + empId  +"'");
    }

    @Override
    public void update(Employee employee, String attribute, int num, String newValue) {
        executor.executeUpdate("update Employee set "+ attribute + "='" +newValue +
                "'where empId = '" + employee.getEmpId() +"'");
    }

    @Override
    public void update(int empId, String attribute, String newValue) {
        executor.executeUpdate("update Employee set "+ attribute + "='" +newValue +
                "'where empId = '" + empId +"'");
    }

    @Override
    public List query(String name) {
        return null;
    }

    @Override
    public Employee detail(int empId) {
        return null;
    }

    @Override
    public void checkSalary(int empId) {

    }
}
