package cn.edu.bnuz.yhy.system.daoFactory.dao;


import cn.edu.bnuz.yhy.system.employee.Employee;
import cn.edu.bnuz.yhy.system.employee.TimeEmployee;
import cn.edu.bnuz.yhy.system.employee.WorkHourCard;
import cn.edu.bnuz.yhy.system.jdbc.JDBCExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TimeEmpDaoSqlServerImpl implements IEmployeeDao {
    private JDBCExecutor executor;
    private IWorkTimeCardDao workTimeCardDao;
    private EmployeeDaoSqlServerImpl empDaoImpl;

    public TimeEmpDaoSqlServerImpl() {
        executor = JDBCExecutor.getJDBCExecutor();
        workTimeCardDao = new WorkHourCardDaoSqlSeverImpl();
        empDaoImpl = new EmployeeDaoSqlServerImpl();
    }

    public void add(Employee employee) {
        // TODO Connection,sql语句 insert
        empDaoImpl.add(employee);
        TimeEmployee timeEmployee = (TimeEmployee) employee;
        executor.executeUpdate("insert into TimeEmployee(empName, empId, gender, address, bankId, payment, " +
                " position, department, hourWage) values('"
                + timeEmployee.getEmpName() + "','" + timeEmployee.getEmpId() + "','"
                + timeEmployee.getGender() + "','" + timeEmployee.getAddress() + "','"
                + timeEmployee.getBankId() + "','" + timeEmployee.getPayment() + "','"
                + timeEmployee.getPosition() + "','" + timeEmployee.getDepartment() + "','"
                + timeEmployee.getHourlyWage() + "' ) ");
        System.out.println("增加雇员成功");
        workTimeCardDao.add(timeEmployee.getWorkHourCard());
    }


    public void delete(int empId) {
        // TODO Connection,sql语句 delete
        executor.executeUpdate("delete from TimeEmployee where empId="
                + empId);
        empDaoImpl.delete(empId);
        workTimeCardDao.delete(empId);
        System.out.println("删除雇员成功");
    }


    public void update(Employee e, String attribute, int n, String newValue) {
        // TODO Connection,sql语句 update
        TimeEmployee te = (TimeEmployee) e;
        empDaoImpl.update(e,attribute,n,newValue);
        executor.executeUpdate("update MonthEmployee set " + attribute + "='" + newValue +
                "'where empId = '" + te.getEmpId() + "'");
        System.out.println("更新雇员成功");
    }

    @Override
    public void update(int empId, String attribute, String newValue) {
        empDaoImpl.update(empId,attribute,newValue);
        executor.executeUpdate("update TimeEmployee set "+ attribute + "='" +newValue +
                "'where empId = '" + empId +"'");
        System.out.println("更新雇员成功");
    }


    public List query(String empName) {
        // TODO Connection,sql语句 select
        List<TimeEmployee> list = new ArrayList<>();
        ResultSet rs;
        TimeEmployee te;
        WorkHourCard whc ;
        if (empName.equals("")) {
            rs = executor.executeQuery("select * from TimeEmployee");
        } else {
            rs = executor.executeQuery("select * from TimeEmployee where empName like '%"
                    + empName + "'");
        }
        try {
            while (rs.next()) {
                te = new TimeEmployee(rs.getString("empName"), rs.getInt("empId"),
                        rs.getString("gender"), rs.getString("address"), rs.getString("bankId"),
                        rs.getString("payment"), rs.getString("position"), rs.getString("department"),
                        rs.getDouble("hourWage"));
                list.add(te);
            }
            for (TimeEmployee te1:
                 list) {
                te1.setWorkHourCard(workTimeCardDao.detail(te1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("查询雇员成功");
        return list;

    }

    public TimeEmployee detail(int empId) {
        TimeEmployee te = null;
        java.sql.ResultSet rs = executor.executeQuery("select * from TimeEmployee where empId = '"
                + empId + "'");
        try {
            while (rs.next()) {
                te = new TimeEmployee(rs.getString("empName"), rs.getInt("empId"),
                        rs.getString("gender"), rs.getString("address"), rs.getString("bankId"),
                        rs.getString("payment"), rs.getString("position"), rs.getString("department"),
                        rs.getDouble("hourWage"));

            }
            te.setWorkHourCard(workTimeCardDao.detail(te));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("查询雇员成功");
        return te;
    }

    @Override
    public void checkSalary(int empId) {
        ResultSet rs = executor.executeQuery("select * from Salary where empId = '"
                + empId + "'");
        System.out.println("查询成功，查询结果如下：");
        try {
            while (rs.next()) {
                System.out.println("时间" + rs.getTime("day") + "实发薪水" + rs.getDouble("salary") + "本次消费" + rs.getDouble("consumption"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
