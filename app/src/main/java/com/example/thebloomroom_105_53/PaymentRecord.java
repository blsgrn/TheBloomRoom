package com.example.thebloomroom_105_53;

public class PaymentRecord {
    private int receiptNo;
    private String customer;
    private double total;
    private String method;

    public PaymentRecord(int receiptNo, String customer, double total, String method) {
        this.receiptNo = receiptNo;
        this.customer = customer;
        this.total = total;
        this.method = method;
    }

    public int getReceiptNo() {
        return receiptNo;
    }


    public String getCustomer() {
        return customer;
    }



    public double getTotal() {
        return total;
    }



    public String getMethod() {
        return method;
    }


}
