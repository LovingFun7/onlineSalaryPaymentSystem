package cn.edu.bnuz.yhy.system.calculation.consumption;

public class AssociationService {
    private double spend;
    private String serviceContent;
    private int serviceId;
    private  String serviceName;
    private static int i = 1;



    public AssociationService(String name, String content, double price, Association association) {
        this.spend = price;
        this.serviceContent =content;
        this.serviceId = association.getAssociationId() * 1000 + AssociationService.i;
        i++;
        this.serviceName = name;
    }

    public double getSpend() {
        return spend;
    }

    public void setSpend(double spend) {
        this.spend = spend;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
