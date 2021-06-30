package cn.edu.bnuz.yhy.system.daoFactory.dao;


import cn.edu.bnuz.yhy.system.employee.TimeEmployee;
import cn.edu.bnuz.yhy.system.employee.WorkHourCard;
import cn.edu.bnuz.yhy.system.jdbc.JDBCExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkHourCardDaoSqlSeverImpl implements IWorkTimeCardDao {
    protected JDBCExecutor executor;

    public WorkHourCardDaoSqlSeverImpl() {
        executor = JDBCExecutor.getJDBCExecutor();
    }

    @Override
    public void add(WorkHourCard workHourCard) {
        executor.executeUpdate("insert into WorkHourCard(whcId,empName,allRealWorkTime,allWorkTime,realDayWorkTime,dayWorkTime) values('"
                + workHourCard.getWhcId() + "','" + workHourCard.getEmpName() + "','"
                + workHourCard.getAllRwTime() + "','" + workHourCard.getAllWTime() + "','"
                + workHourCard.getRwTime() + "','" + workHourCard.getwTime() + "')");

        System.out.println("增加工作时间卡成功");
    }

    @Override
    public void delete(int whcId) {
        executor.executeUpdate("delete from WorkHourCard where whcId="
                + whcId);
        System.out.println("删除工作时间卡成功");
    }

    @Override
    public void update(int  whcId, String attribute,  String newValue) {
        executor.executeUpdate("update WorkHourCard set "+ attribute + "='" +newValue +
                "'where whcId = '" + whcId +"'");
        System.out.println("更新工作时间卡成功");
    }

    @Override
    public WorkHourCard detail(TimeEmployee timeEmployee) {
        WorkHourCard workHourCard = null;
        ResultSet rs = executor.executeQuery("select * from WorkHourCard where whcId = '"
                + timeEmployee.getEmpId() + "'");

        try {
            while (rs.next()) {
                workHourCard = new WorkHourCard(rs.getInt("whcId"), rs.getString("empName"),
                        rs.getInt("allRealWorkTime"), rs.getDouble("allWorkTime"), rs.getInt("realDayWorkTime"),
                        rs.getDouble("dayWorkTime"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("查找工作时间卡成功");
        return workHourCard;
    }

    @Override
    public void clear(int whcId) {
        executor.executeUpdate("update WorkHourCard set allRealWorkTime = '0', allWorkTime = '0.0',realDayWorkTime = '0' dayWorkTime = '0.0'"+
                "where whcId = '" + whcId +"'");
        System.out.println("清空工作时间卡成功");
    }


}
