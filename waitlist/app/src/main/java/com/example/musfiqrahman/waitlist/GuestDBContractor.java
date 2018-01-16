package com.example.musfiqrahman.waitlist;

/**
 * Created by ShengXiao on 2018-01-15.
 */

public class GuestDBContractor {
    public static final String DB_PK = "_id";
    public static final String DB_GUEST_NAME="guest_name";
    public static final String    DB_GUEST_NUMBER="guest_number";
    public static final  String DB_NAME="myDB";
    public static final String DB_TABLE_NAME="guest_list";
    public static final String DB_CREATE_SQL =
            "create table " + DB_TABLE_NAME
                    + " (" + DB_PK + " integer primary key autoincrement, "
                    + DB_GUEST_NAME         + " text not null, "
                    + DB_GUEST_NUMBER   + " integer not null"
                    + ");";
    public static final int DATABASE_VERSION = 1;
    public static final String[] ALL_KEYS = new String[] {DB_PK, DB_GUEST_NAME, String.valueOf(DB_GUEST_NUMBER)};

}
