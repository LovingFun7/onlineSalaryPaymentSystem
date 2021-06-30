package cn.edu.bnuz.yhy.system.paySystem;



public class ThreadPaySystem implements Runnable{
    private PaySystem paySystem;

    public ThreadPaySystem(PaySystem paySystem) {
        this.paySystem = paySystem;
    }

    @Override
    public void run() {
        while (true) {
            paySystem.run();
            System.out.println("正在运行员工管理子系统和薪酬支付子系统");
            try {
                Thread.sleep(2000000);//300000每隔5分钟运行一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public PaySystem getPaySystem(){
        return paySystem;
    }

}
