package cn.edu.bnuz.yhy.system.daoFactory.dao;


import cn.edu.bnuz.yhy.system.employee.Employee;
import cn.edu.bnuz.yhy.system.employee.MonthEmployee;
import cn.edu.bnuz.yhy.system.jdbc.JDBCExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonthEmpDaoSqlServerImpl implements IEmployeeDao {
    private JDBCExecutor executor;
    private EmployeeDaoSqlServerImpl empDaoImpl;

    public MonthEmpDaoSqlServerImpl() {
        executor = JDBCExecutor.getJDBCExecutor();
        empDaoImpl = new EmployeeDaoSqlServerImpl();
    }

    public void add(Employee employee) {
        // TODO Connection,sql语句 insert
        empDaoImpl.add(employee);
        MonthEmployee monthEmployee = (MonthEmployee) employee;
        executor.executeUpdate("insert into MonthEmployee(empName, empId, gender, address, bankId, payment, " +
                " position, department, monthSalary) values('"
                + monthEmployee.getEmpName() + "','" + monthEmployee.getEmpId() + "','"
                + monthEmployee.getGender() + "','" + monthEmployee.getAddress() + "','"
                + monthEmployee.getBankId() + "','" + monthEmployee.getPayment() + "','"
                + monthEmployee.getPosition() + "','" + monthEmployee.getDepartment() + "','"
                + monthEmployee.getMonthSalary() + "' ) ");
        System.out.println("增加雇员成功");
    }


    public void delete(int empId) {
        // TODO Connection,sql语句 delete
        executor.executeUpdate("delete from MonthEmployee where empId= '"
                + empId + "'");
        empDaoImpl.delete(empId);
        System.out.println("删除雇员成功");
    }


    public void update(Employee e, String attribute, int n, String newValue) {
        // TODO Connection,sql语句 update
        MonthEmployee me = (MonthEmployee) e;
        empDaoImpl.update(e,attribute,n,newValue);
        executor.executeUpdate("update MonthEmployee set " + attribute + "='" + newValue +
                "'where empId = '" + me.getEmpId() + "'");
        System.out.println("更新雇员成功");
    }

    @Override
    public void update(int empId, String attribute, String newValue) {
        empDaoImpl.update(empId,attribute,newValue);
        executor.executeUpdate("update MonthEmployee set "+ attribute + "='" +newValue +
                "'where empId = '" + empId +"'");
        System.out.println("更新雇员成功");
    }


    public List query(String empName) {
        // TODO Connection,sql语句 select
        List<MonthEmployee> list = new ArrayList<>();
        ResultSet rs;
        MonthEmployee me;
        if (empName.equals("")) {
            rs = executor.executeQuery("select * from MonthEmployee");
        } else {
            rs = executor.executeQuery("select * from MonthEmployee where empName like '%"
                    + empName + "'");
        }
        try {
            while (rs.next()) {
                me = new MonthEmployee(rs.getString("empName"), rs.getInt("empId"),
                        rs.getString("gender"), rs.getString("address"), rs.getString("bankId"),
                        rs.getString("payment"), rs.getString("position"), rs.getString("department"),
                        rs.getDouble("monthSalary"));
                list.add(me);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("查询雇员成功");
        return list;

    }

    public MonthEmployee detail(int empId) {
        MonthEmployee monthEmployee = null;
        java.sql.ResultSet rs = executor.executeQuery("select * from MonthEmployee where empId = '"
                + empId + "'");
        try {
            while (rs.next()) {
                monthEmployee = new MonthEmployee(rs.getString("empName"), rs.getInt("empId"),
                        rs.getString("gender"), rs.getString("address"), rs.getString("bankId"),
                        rs.getString("payment"), rs.getString("position"), rs.getString("department"),
                        rs.getDouble("monthSalary"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("查询雇员成功");
        return monthEmployee;
    }

    @Override
    public void checkSalary(int empId) {
        ResultSet rs = executor.executeQuery("select * from Salary where empId = '"
                + empId + "'");
        System.out.println("查询成功工资，查询结果如下：");
        try {
            while (rs.next()) {
                System.out.println("时间" + rs.getTime("day") + "实发薪水" + rs.getDouble("salary") + "本次消费" + rs.getDouble("consumption"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
