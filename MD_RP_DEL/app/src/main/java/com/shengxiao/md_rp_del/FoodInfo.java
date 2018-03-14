package com.shengxiao.md_rp_del;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ShengXiao on 2018-03-11.
 */
@Entity
public class FoodInfo {


    @PrimaryKey(autoGenerate = true)
    private int fid;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String expiryDate;

    @ColumnInfo
    private int quantity;

    public FoodInfo(String name, String expiryDate, int quantity) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {

        return name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {

        this.fid = fid;
    }

    @Override
    public String toString() {
        String info="Name: "+getName()+" Expiry Date: "+getExpiryDate()+" Quantity: "+getQuantity();
        return info;
    }
}
