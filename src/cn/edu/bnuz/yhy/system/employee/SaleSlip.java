package cn.edu.bnuz.yhy.system.employee;

import java.util.Calendar;

public class SaleSlip {
    private int ssId ;
    private String empName;
    private String saleDay;
    private int achievement;
    private double commitRate ;
    private static int i = 0;

    private SaleSlip(int achievement, double commitRate, CommitSaleEmployee commitSaleEmployee) {
        this.empName = commitSaleEmployee.getEmpName();
        this.saleDay = "Year= "+ Calendar.YEAR + "Month= "+Calendar.MONTH + "Day= "+Calendar.DAY_OF_MONTH;
        this.achievement = achievement;
        this.commitRate = commitRate;
    }

    public SaleSlip(int ssId, String empName, String saleDay, int achievement, double commitRate) {
        this.ssId = ssId;
        this.empName = empName;
        this.saleDay = saleDay;
        this.achievement = achievement;
        this.commitRate = commitRate;
    }

    public static SaleSlip addSaleSlip(int achievement, double commitRate, CommitSaleEmployee commitSaleEmployee) {
        SaleSlip ss =new SaleSlip(achievement,commitRate,commitSaleEmployee);
        ss.setSsId(commitSaleEmployee);
        return ss;
    }

    private void setSsId(CommitSaleEmployee commitSaleEmployee) {
        ssId = commitSaleEmployee.getEmpId() * 13 + SaleSlip.i * 3;
        SaleSlip.i++;
    }


    public double getAchievement() {
        return achievement;
    }

    public double getCommitRate() {
        return commitRate;
    }

    public int getSsId() {
        return ssId;
    }

    public String getEmpName() {
        return empName;
    }

    public String getSaleDay() {
        return saleDay;
    }

    @Override
    public String toString() {
        return "SaleSlip{" +
                "ssId=" + ssId +
                ", empName='" + empName + '\'' +
                ", saleDay='" + saleDay + '\'' +
                ", achievement=" + achievement +
                ", commitRate=" + commitRate +
                '}';
    }
}
