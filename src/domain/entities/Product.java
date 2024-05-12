package domain.entities;

import java.util.Calendar;

public abstract class Product {
    protected short id = 2;
    protected String name;
    protected Calendar purchaseDate;
    protected double price;
    protected String address;
    protected short quality;
    protected String comment = "0";

    public abstract short getId();
    public abstract String getName();
    public abstract void setName(String name);
    public abstract Calendar getPurchaseDate();
    public abstract void setPurchaseDate(String purchaseDate);
    public abstract double getPrice();
    public abstract void setPrice(double price);
    public abstract String getAddress();
    public abstract void setAddress(String address);
    public abstract short getQuality();
    public abstract void setQuality(int quality);
    public abstract String getComment();
}