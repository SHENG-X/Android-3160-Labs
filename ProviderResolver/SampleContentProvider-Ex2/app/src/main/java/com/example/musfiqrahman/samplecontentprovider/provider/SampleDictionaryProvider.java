package com.example.musfiqrahman.samplecontentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.musfiqrahman.samplecontentprovider.data.DictionaryContract;
import com.example.musfiqrahman.samplecontentprovider.data.DictionaryDao;
import com.example.musfiqrahman.samplecontentprovider.data.DictionaryDatabase;
import com.example.musfiqrahman.samplecontentprovider.data.DictionaryEntry;

import java.util.ArrayList;

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


    /** The authority of this content provider. */
    public static final String AUTHORITY = "com.example.musfiqrahman.samplecontentprovider.provider";



    /** The URI for the Dictionary table. */
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY + "/" + DictionaryContract.DictionaryEntity.TABLE_NAME);


    /** The match code for some items in the Dictionary table. */
    private static final int CODE_DICTIONARY_DIR = 1;

    /** The match code for an item in the Dictionary table. */
    private static final int CODE_DICTIONARY_ITEM = 2;

    /** The URI matcher. */
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, DictionaryContract.DictionaryEntity.TABLE_NAME, CODE_DICTIONARY_DIR);
        MATCHER.addURI(AUTHORITY, DictionaryContract.DictionaryEntity.TABLE_NAME + "/*", CODE_DICTIONARY_ITEM);
    }


    // Need to override six methods.
}
