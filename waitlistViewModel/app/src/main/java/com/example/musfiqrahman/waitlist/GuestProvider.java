package com.example.musfiqrahman.waitlist;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ShengXiao on 2018-01-31.
 */

public class GuestProvider extends ContentProvider {

    private GuestDAO guestDAO;

    private static final int CODE_GUESTS=100;


    private static final UriMatcher MATCHER=getMatcher();

    private static UriMatcher getMatcher() {
        UriMatcher uriMatcher =new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(GuestContract.AUTHORITY,GuestContract.GuestEntity.TABLE_NAME,CODE_GUESTS);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        if(context == null)
            return false;

        guestDAO = GuestDB.getDB(context).guestDAO();
        return true;    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;

        switch (MATCHER.match(uri)){
            case CODE_GUESTS:
                // return all data
                cursor =guestDAO.getAllGuestsInfo();
                break;
            default:
                // throw exception;
                throw new IllegalArgumentException("Unknown URI "+uri);
        }

        cursor.setNotificationUri(getContext()
                .getContentResolver(), uri);
        return cursor;    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (MATCHER.match(uri)){
            case CODE_GUESTS:
                guestDAO.insertGuestInfo(GuestInfo.fromContentValues(values));
                return null;
            default:
                throw new IllegalArgumentException("Illegal URI"+uri);
        }    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
