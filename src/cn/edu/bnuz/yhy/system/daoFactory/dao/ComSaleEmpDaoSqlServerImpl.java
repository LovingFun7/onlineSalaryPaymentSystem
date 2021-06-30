package cn.edu.bnuz.yhy.system.daoFactory.dao;



import cn.edu.bnuz.yhy.system.employee.CommitSaleEmployee;
import cn.edu.bnuz.yhy.system.employee.Employee;
import cn.edu.bnuz.yhy.system.employee.MonthEmployee;
import cn.edu.bnuz.yhy.system.jdbc.JDBCExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComSaleEmpDaoSqlServerImpl implements IEmployeeDao {
    private JDBCExecutor executor;
    private ISaleSlipDao saleSlipDao;
    private EmployeeDaoSqlServerImpl empDaoImpl;

    public ComSaleEmpDaoSqlServerImpl() {
        executor = JDBCExecutor.getJDBCExecutor();
        saleSlipDao = new SaleSlipServerImpl();
        empDaoImpl = new EmployeeDaoSqlServerImpl();
    }

    public void add(Employee employee) {
        // TODO Connection,sql语句 insert
        empDaoImpl.add(employee);
        CommitSaleEmployee commitSaleEmployee = (CommitSaleEmployee) employee;
        executor.executeUpdate("insert into CommitSaleEmployee(empName, empId, gender, address, bankId, payment, " +
                " position, department, baseSalary) values('"
                + commitSaleEmployee.getEmpName() + "','" + commitSaleEmployee.getEmpId() + "','"
                + commitSaleEmployee.getGender()+ "','" + commitSaleEmployee.getAddress() + "','"
                + commitSaleEmployee.getBankId() + "','" + commitSaleEmployee.getPayment() + "','"
                + commitSaleEmployee.getPosition() + "','" + commitSaleEmployee.getDepartment() + "','"
                + commitSaleEmployee.getBaseSalary() + "' ) ");
        System.out.println("增加雇员成功");
    }


    public void delete(int empId) {
        // TODO Connection,sql语句 delete
        executor.executeUpdate("delete from CommitSaleEmployee where empId= '"
                + empId + "'");
        empDaoImpl.delete(empId);
        saleSlipDao.deleteEmployee(empId);
        System.out.println("删除雇员成功");
    }


    public void update(Employee e,String attribute,int n,String newValue) {
        // TODO Connection,sql语句 update
        CommitSaleEmployee cse = (CommitSaleEmployee) e;
        empDaoImpl.update(e,attribute,n,newValue);
        executor.executeUpdate("update MonthEmployee set "+ attribute + "='" +newValue +
                "'where empId = '" + cse.getEmpId() +"'");
        System.out.println("更新雇员成功");
    }

    @Override
    public void update(int empId, String attribute, String newValue) {
        empDaoImpl.update(empId,attribute,newValue);
        executor.executeUpdate("update CommitSaleEmployee set "+ attribute + "='" +newValue +
                "'where empId = '" + empId +"'");
        System.out.println("更新雇员成功");
    }


    public List query(String empName) {
        // TODO Connection,sql语句 select
        List<CommitSaleEmployee> list = new ArrayList<>();
        ResultSet rs;
        CommitSaleEmployee cse;
//        值为空则直接查询所有雇员
        if (empName.equals("")){
            rs = executor.executeQuery("select * from CommitSaleEmployee");
        }else {
            rs = executor.executeQuery("select * from CommitSaleEmployee where empName like '%"
                    + empName + "'");
        }
        try {
            while (rs.next()) {
                cse = new CommitSaleEmployee(rs.getString("empName"),rs.getInt("empId"),
                        rs.getString("gender"), rs.getString("address"),rs.getString("bankId"),
                        rs.getString("payment"),rs.getString("position"),rs.getString("department"),
                        rs.getDouble("baseSalary") );
                list.add(cse);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("查询雇员成功");
        return list;

    }

    public CommitSaleEmployee detail(int empId) {
        CommitSaleEmployee commitSaleEmployee = null;
        ResultSet rs = executor.executeQuery("select * from CommitSaleEmployee where empId = '"
                + empId + "'");
        try {
            while (rs.next()) {
                commitSaleEmployee = new CommitSaleEmployee(rs.getString("empName"),rs.getInt("empId"),
                        rs.getString("gender"), rs.getString("address"),rs.getString("bankId"),
                        rs.getString("payment"),rs.getString("position"),rs.getString("department"),
                        rs.getDouble("baseSalary") );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("查询雇员成功");
        return commitSaleEmployee;
    }

    @Override
    public void checkSalary(int empId) {
        ResultSet rs = executor.executeQuery("select * from Salary where empId = '"
                + empId + "'");
        System.out.println("查询成功，查询结果如下：");
        try{
            while (rs.next()){
                System.out.println("时间"+rs.getTime("day")+"实发薪水"+rs.getDouble("salary")+"本次消费"+rs.getDouble("consumption"));
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
