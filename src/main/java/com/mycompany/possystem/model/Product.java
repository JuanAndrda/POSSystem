package com.mycompany.possystem.model;

public class Product {

    private int     id;
    private String  name;
    private int     categoryId;
    private String  categoryName;
    private double  price;
    private double  cost;
    private int     stock;
    private int     lowStockThreshold;
    private String  barcode;
    private boolean active;

    public Product(int id, String name, int categoryId, double price,
                   double cost, int stock, int lowStockThreshold, String barcode) {
        this.id                = id;
        this.name              = name;
        this.categoryId        = categoryId;
        this.price             = price;
        this.cost              = cost;
        this.stock             = stock;
        this.lowStockThreshold = lowStockThreshold;
        this.barcode           = barcode;
        this.active            = true;
    }

    public int     getId()                { return id; }
    public String  getName()              { return name; }
    public int     getCategoryId()        { return categoryId; }
    public String  getCategoryName()      { return categoryName; }
    public double  getPrice()             { return price; }
    public double  getCost()              { return cost; }
    public int     getStock()             { return stock; }
    public int     getLowStockThreshold() { return lowStockThreshold; }
    public String  getBarcode()           { return barcode != null ? barcode : ""; }
    public boolean isActive()             { return active; }
    public boolean isLowStock()           { return stock <= lowStockThreshold; }

    public void setCategoryName(String n) { this.categoryName = n; }
    public void setStock(int stock)       { this.stock = stock; }

    @Override
    public String toString() { return name; }
}