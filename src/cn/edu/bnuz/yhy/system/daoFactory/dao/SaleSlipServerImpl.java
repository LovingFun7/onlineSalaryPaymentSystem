package cn.edu.bnuz.yhy.system.daoFactory.dao;

import cn.edu.bnuz.yhy.system.employee.CommitSaleEmployee;
import cn.edu.bnuz.yhy.system.employee.SaleSlip;
import cn.edu.bnuz.yhy.system.jdbc.JDBCExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleSlipServerImpl implements ISaleSlipDao {

    protected JDBCExecutor executor;

    public SaleSlipServerImpl() {
        executor = JDBCExecutor.getJDBCExecutor();
    }

    @Override
    public void add(SaleSlip saleSlip) {
        executor.executeUpdate("insert into SaleSlip(ssId,empName,saleDay,achievement,commitRate) values('"
                + saleSlip.getSsId() + "','" + saleSlip.getEmpName() + "','" + saleSlip.getSaleDay() + "','" + saleSlip.getAchievement() + "','"
                + saleSlip.getCommitRate() + "')");
        System.out.println("增加凭条成功");
    }

    @Override
    public void addEmployeeSlip(CommitSaleEmployee cse, List<SaleSlip> saleSlipList) {
        for (SaleSlip saleSlip : saleSlipList) {
            executor.executeUpdate("insert into SaleSlip(ssId,empName,saleDay,achievement,commitRate) values('"
                    + saleSlip.getSsId() + "','" + cse.getEmpName() + "','" + saleSlip.getSaleDay() + "','" + saleSlip.getAchievement() + "','"
                    + saleSlip.getCommitRate() + "')");
        }
        System.out.println("增加雇员凭条成功");
    }

    @Override
    public void delete(int ssId) {
        executor.executeUpdate("delete from SaleSlip where ssId="
                + ssId);
        System.out.println("删除凭条成功");
    }

    @Override
    public void deleteEmployee(int empId) {
        executor.executeUpdate("delete from SaleSlip where empId="
                + empId);
        System.out.println("删除雇员凭条成功");
    }

    @Override
    public void update(SaleSlip saleSlip, String attribute, int num, String newValue) {
        executor.executeUpdate("update SaleSlip set " + attribute + "='" + newValue +
                "'where ssId = '" + saleSlip.getSsId() + "'");
        System.out.println("更新凭条成功");
    }

    @Override
    public void update(int ssId, String attribute, String newValue) {
        executor.executeUpdate("update SaleSlip set " + attribute + "='" + newValue +
                "'where ssId = '" + ssId + "'");
        System.out.println("更新凭条成功");
    }


    @Override
    public List query(String empName) {
        List<SaleSlip> list = new ArrayList<>();
        ResultSet rs;
        SaleSlip ss;
        if (empName.equals("")) {
            rs = executor.executeQuery("select * from SaleSlip");
        } else {
            rs = executor.executeQuery("select * from SaleSlip where empName like '%"
                    + empName + "'");
        }
        try {
            while (rs.next()) {
                ss = new SaleSlip(rs.getInt("ssId"), rs.getString("empId"),
                        rs.getString("saleDay"), rs.getInt("achievement"),
                        rs.getDouble("commitRate"));
                list.add(ss);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("查找凭条成功");
        return list;
    }

    @Override
    public SaleSlip detail(int ssId) {
        SaleSlip saleSlip = null;
        java.sql.ResultSet rs = executor.executeQuery("select * from SaleSlip where ssId = '"
                + ssId + "'");
        try {
            while (rs.next()) {
                saleSlip = new SaleSlip(rs.getInt("ssId"), rs.getString("empId"),
                        rs.getString("saleDay"), rs.getInt("achievement"),
                        rs.getDouble("commitRate"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("查找凭条成功");
        return saleSlip;
    }


}
