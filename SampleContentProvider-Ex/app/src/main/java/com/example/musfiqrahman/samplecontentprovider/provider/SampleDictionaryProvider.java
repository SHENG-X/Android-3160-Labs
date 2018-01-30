package com.example.musfiqrahman.samplecontentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.musfiqrahman.samplecontentprovider.data.DictionaryContract;
import com.example.musfiqrahman.samplecontentprovider.data.DictionaryDao;
import com.example.musfiqrahman.samplecontentprovider.data.DictionaryDatabase;
import com.example.musfiqrahman.samplecontentprovider.data.DictionaryEntry;

/**
 * Created by musfiqrahman on 2018-01-21.
 */

/**
 * A {@link ContentProvider} based on a Room database.
 *
 * <p>Note that you don't need to implement a ContentProvider unless you want to expose the data
 * outside your process or your application already uses a ContentProvider.</p>
 */

public class SampleDictionaryProvider extends ContentProvider {
    private DictionaryDao dictionaryDao;

    /** The authority of this content provider. */
//    public static final String AUTHORITY = "com.example.musfiqrahman.samplecontentprovider.provider";



    /** The URI for the Dictionary table. */
//    public static final Uri CONTENT_URI = Uri.parse(
//            "content://" + AUTHORITY + "/" + DictionaryContract.DictionaryEntity.TABLE_NAME);


    /** The match code for some items in the Dictionary table. */
    private static final int CODE_DICTIONARY_DIR = 1;

    /** The match code for an item in the Dictionary table. */
    private static final int CODE_DICTIONARY_ITEM = 2;

    /** The URI matcher. */
    private static UriMatcher getMatcher(){
        UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(DictionaryContract.AUTHORITY, DictionaryContract.DictionaryEntity.TABLE_NAME, CODE_DICTIONARY_DIR);
        matcher.addURI(DictionaryContract.AUTHORITY, DictionaryContract.DictionaryEntity.TABLE_NAME + "/*", CODE_DICTIONARY_ITEM);
        return matcher;
    }
    private static final UriMatcher MATCHER=getMatcher();



//    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

//    static {
//        MATCHER.addURI(AUTHORITY, DictionaryContract.DictionaryEntity.TABLE_NAME, CODE_DICTIONARY_DIR);
//        MATCHER.addURI(AUTHORITY, DictionaryContract.DictionaryEntity.TABLE_NAME + "/*", CODE_DICTIONARY_ITEM);
//    }

    @Override
    public boolean onCreate() {
        Context context=getContext();
        if(context!=null){
            dictionaryDao= DictionaryDatabase.getInstance(getContext()).dictionaryDao();
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor=null;
        switch (MATCHER.match(uri)){
            case CODE_DICTIONARY_DIR:
                //return all data
                cursor=dictionaryDao.selectAll();
                break;
            case CODE_DICTIONARY_ITEM:
                //return specific data
                cursor=dictionaryDao.selectById(/*Find the id*/ContentUris.parseId(uri)/*Find the id*/);
                break;
            default:
                //throw exception
                throw new IllegalArgumentException("Unknown Uri"+uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_DICTIONARY_DIR:
                return new String("vnd.android.cursor.dir/"
                        + DictionaryContract.AUTHORITY
                        + "." + DictionaryContract.DictionaryEntity.TABLE_NAME);
            case CODE_DICTIONARY_ITEM:
                return new String("vnd.android.cursor.item/"
                        + DictionaryContract.AUTHORITY + "."
                        + DictionaryContract.DictionaryEntity.TABLE_NAME);
            default:
                throw new IllegalArgumentException("Illegal URI");
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (MATCHER.match(uri)){
            case CODE_DICTIONARY_DIR:
                final long id=dictionaryDao.insert(DictionaryEntry.fromContentValues(values));
                return ContentUris.withAppendedId(uri,id);
            case CODE_DICTIONARY_ITEM:
                throw new IllegalArgumentException("Invalid URI can not insert with ID"+uri);
            default:
                throw new IllegalArgumentException("Illegal URI"+uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (MATCHER.match(uri)){
            case CODE_DICTIONARY_DIR:
                throw new IllegalArgumentException("Please Specify ID");
            case CODE_DICTIONARY_ITEM:
                final int count=dictionaryDao.deleteById(ContentUris.parseId(uri));
                return count;
            default:
                throw new IllegalArgumentException("Illegal URI"+uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (MATCHER.match(uri)){
            case CODE_DICTIONARY_DIR:
                throw new IllegalArgumentException("Please Specify ID");
            case CODE_DICTIONARY_ITEM:
                final int count=dictionaryDao.update(DictionaryEntry.fromContentValues(values));
                return count;
            default:
                throw new IllegalArgumentException("Illegal URI"+uri);
        }
    }


    // Need to override six methods.
}
