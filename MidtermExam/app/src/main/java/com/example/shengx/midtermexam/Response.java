package com.example.shengx.midtermexam;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by SHENG.X on 2018-03-19.
 */
@Entity
public class Response {
    @PrimaryKey(autoGenerate =true)
    int responseID;

    @ColumnInfo
    String text;

    @ColumnInfo
    int number;

    @ColumnInfo
    boolean found;

    @ColumnInfo
    String type;

    public Response(String text, int number, boolean found, String type) {
        this.text = text;
        this.number = number;
        this.found = found;
        this.type = type;
    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getResponseID() {

        return responseID;
    }

    public String getText() {
        return text;
    }

    public int getNumber() {
        return number;
    }

    public boolean isFound() {
        return found;
    }

    public String getType() {
        return type;
    }
}
