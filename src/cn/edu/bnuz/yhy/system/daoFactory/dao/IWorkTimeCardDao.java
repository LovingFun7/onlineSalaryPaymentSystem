package cn.edu.bnuz.yhy.system.daoFactory.dao;


import cn.edu.bnuz.yhy.system.employee.Employee;
import cn.edu.bnuz.yhy.system.employee.TimeEmployee;
import cn.edu.bnuz.yhy.system.employee.WorkHourCard;

import java.util.List;

public interface IWorkTimeCardDao {
    void add(WorkHourCard workHourCard);

    void delete(int whcId);

    //更新工作时间卡信息
    void update(int  whcId, String attribute, String newValue);

    WorkHourCard detail(TimeEmployee timeEmployee);

    void clear(int whcId);

}
