package com.mycompany.possystem.model;

import java.time.LocalDateTime;

public class Sale {

    private int           id;
    private int           cashierId;
    private String        cashierName;
    private double        totalAmount;
    private double        discount;
    private double        taxAmount;
    private String        paymentMethod;
    private double        amountTendered;
    private double        changeDue;
    private LocalDateTime saleDate;

    public Sale(int id, int cashierId, double totalAmount, double discount,
                double taxAmount, String paymentMethod,
                double amountTendered, double changeDue, LocalDateTime saleDate) {
        this.id             = id;
        this.cashierId      = cashierId;
        this.totalAmount    = totalAmount;
        this.discount       = discount;
        this.taxAmount      = taxAmount;
        this.paymentMethod  = paymentMethod;
        this.amountTendered = amountTendered;
        this.changeDue      = changeDue;
        this.saleDate       = saleDate;
    }

    public int           getId()             { return id; }
    public int           getCashierId()      { return cashierId; }
    public String        getCashierName()    { return cashierName; }
    public double        getTotalAmount()    { return totalAmount; }
    public double        getDiscount()       { return discount; }
    public double        getTaxAmount()      { return taxAmount; }
    public String        getPaymentMethod()  { return paymentMethod; }
    public double        getAmountTendered() { return amountTendered; }
    public double        getChangeDue()      { return changeDue; }
    public LocalDateTime getSaleDate()       { return saleDate; }

    public void setCashierName(String n) { this.cashierName = n; }
}