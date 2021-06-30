package cn.edu.bnuz.yhy.system.daoFactory.dao;


import cn.edu.bnuz.yhy.system.employee.Employee;

import java.util.List;

public interface IEmployeeDao {
    //增加雇员
    void add(Employee employee);

    //删除雇员
    void delete(int empId);

    //更新雇员信息
    void update(Employee employee,String attribute,int num ,String newValue);

    void update(int empId,String attribute,String newValue);

    //通过名字查找雇员
    List query(String name);

    //通过id查找雇员
    Employee detail(int empId);

    //通过雇员id查找工资
    void checkSalary(int empId);


}
