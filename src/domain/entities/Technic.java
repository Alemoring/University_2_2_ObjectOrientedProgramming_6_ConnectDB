package domain.entities;

import java.util.Calendar;

public class Technic extends Product{
    private static short id = 3;
    private int guarantee = -1;

    public Technic(String name, Calendar purchaseDate, double price, String address, short quality, String comment, int guarantee){
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.address = address;
        this.quality = quality;
        this.comment = comment;
        this.guarantee = guarantee;
    }
    public Technic(String name, Calendar purchaseDate, double price, String address, short quality, int guarantee){
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.address = address;
        this.quality = quality;
        this.guarantee = guarantee;
    }
    public Technic(String name, Calendar purchaseDate, double price, String address, short quality, String comment){
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.address = address;
        this.quality = quality;
        this.comment = comment;
    }
    public Technic(String name, Calendar purchaseDate, double price, String address, short quality){
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.address = address;
        this.quality = quality;
    }
    public short getId(){
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Calendar getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public void setPurchaseDate(String purchaseDate) {
        String[] values = purchaseDate.trim().split(" ");
        this.purchaseDate.set(Integer.parseInt(values[0]), (Integer.parseInt(values[1]) - 1), Integer.parseInt(values[2]));
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public short getQuality() {
        return quality;
    }

    @Override
    public void setQuality(int quality) {
        this.quality = (short) quality;
    }

    @Override
    public String getComment() {
        return comment;
    }

    public int getGuarantee() {
        return guarantee;
    }
}