package domain.entities;

import java.util.Calendar;

public class Milk extends Food{
    private static short id = 4;
    private double fatContent;

    public Milk(String name, Calendar purchaseDate, double price, String address, short quality, String comment, Calendar storageLife, double fatContent) {
        super(name, purchaseDate, price, address, quality, comment, storageLife);
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.address = address;
        this.quality = quality;
        this.comment = comment;
        this.storageLife = storageLife;
        this.fatContent = fatContent;
    }
    public Milk(String name, Calendar purchaseDate, double price, String address, short quality, String comment, double fatContent) {
        super(name, purchaseDate, price, address, quality, comment);
        Calendar altstorageLife = Calendar.getInstance();
        altstorageLife.set(1950, 0, 1);
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.address = address;
        this.quality = quality;
        this.comment = comment;
        this.storageLife = altstorageLife;
        this.fatContent = fatContent;
    }
    public Milk(String name, Calendar purchaseDate, double price, String address, short quality, Calendar storageLife, double fatContent) {
        super(name, purchaseDate, price, address, quality, storageLife);
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.address = address;
        this.quality = quality;
        this.storageLife = storageLife;
        this.fatContent = fatContent;
    }
    public Milk(String name, Calendar purchaseDate, double price, String address, short quality, double fatContent) {
        super(name, purchaseDate, price, address, quality);
        Calendar altstorageLife = Calendar.getInstance();
        altstorageLife.set(1950, 0, 1);
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.address = address;
        this.quality = quality;
        this.storageLife = altstorageLife;
        this.fatContent = fatContent;
    }
    @Override
    public short getId(){
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Calendar getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public short getQuality() {
        return quality;
    }

    @Override
    public String getComment() {
        return comment;
    }

    public Calendar getStorageLife(){
        return storageLife;
    }

    public double getFatContent() {
        return fatContent;
    }

    public void setFatContent(double fatContent) {
        this.fatContent = fatContent;
    }
}