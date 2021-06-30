package cn.edu.bnuz.yhy.system.employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WorkHourCard {
    private int whcId;
    private String empName;
    private int allRwTime;//总实际工作时间
    private double allWTime;//总工时
    private int rwTime;//实际工作时间
    private double wTime;//工时
    private List<Date> creditCardTime;
    private Calendar cal;
    private int startTime;//当日上班时间
    private int endTime;//当日下班时间
    private int today;//日期

    public WorkHourCard(int empId, String empName) {
        this.whcId = empId;
        this.empName = empName;
        this.allRwTime = 0;
        this.allWTime = 0.0;
        this.rwTime = 0;
        this.wTime = 0.0;
        creditCardTime = new ArrayList<>();
        cal = Calendar.getInstance();
    }

    public WorkHourCard(int whcId, String empName, int allRwTime, double allWTime, int rwTime, double wTime) {
        this.whcId = whcId;
        this.empName = empName;
        this.allRwTime = allRwTime;
        this.allWTime = allWTime;
        this.rwTime = rwTime;
        this.wTime = wTime;
    }

    //上班打卡
    public void clockIn() {
        creditCardTime.add(cal.getTime());
        startTime = cal.get(Calendar.HOUR_OF_DAY);
        today = cal.get(Calendar.DAY_OF_MONTH);
    }

    //下班打卡
    public void clockOut() {
        creditCardTime.add(cal.getTime());
        endTime = cal.get(Calendar.HOUR_OF_DAY);
        if (cal.get(Calendar.DAY_OF_MONTH) != today) {
            endTime += 24;
        }
        workTimeStrategy();
    }

    //计算当日实际工作时间和工时
    public void workTimeStrategy() {
        rwTime = endTime - startTime;
        if (rwTime > 8) wTime += 8 + (rwTime - 8) * 1.5;
    }

    //
    public void clearWaitWorkCard() {
        allRwTime += rwTime;
        allWTime += wTime;
        wTime = 0.0;
        rwTime = 0;
    }

    public int getWhcId() {
        return whcId;
    }

    public String getEmpName() {
        return empName;
    }

    public int getAllRwTime() {
        return allRwTime;
    }

    public double getAllWTime() {
        return allWTime;
    }

    public int getRwTime() {
        return rwTime;
    }

    public double getwTime() {
        return wTime;
    }

    public List<Date> getCreditCardTime() {
        return creditCardTime;
    }

    @Override
    public String toString() {
        return "WorkHourCard{" +
                "whcId=" + whcId +
                ", empName='" + empName + '\'' +
                ", allRwTime=" + allRwTime +
                ", allWTime=" + allWTime +
                ", rwTime=" + rwTime +
                ", wTime=" + wTime +
                ", creditCardTime=" + creditCardTime +
                ", cal=" + cal +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", today=" + today +
                '}';
    }
}
