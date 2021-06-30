package cn.edu.bnuz.yhy.system.daoFactory.dao;

import cn.edu.bnuz.yhy.system.employee.CommitSaleEmployee;
import cn.edu.bnuz.yhy.system.employee.Employee;
import cn.edu.bnuz.yhy.system.employee.SaleSlip;
import cn.edu.bnuz.yhy.system.employee.WorkHourCard;

import java.util.List;

public interface ISaleSlipDao {
    void add(SaleSlip saleSlip);

    void delete(int ssId);

    void deleteEmployee(int empId);

    void update(SaleSlip saleSlip, String attribute, int num , String newValue);

    void update(int ssId,String attribute,String newValue);

    //通过干员名字查找销售凭条
    List query(String empName);
    //通过id查找销售凭条
    SaleSlip detail(int ssId);
    //录入销售雇员的所有销售凭条
    void addEmployeeSlip(CommitSaleEmployee cse, List<SaleSlip> allSaleSlipList);
}
