package com.example.musfiqrahman.samplecontentprovider.data;

/**
 * Created by musfiqrahman on 2018-01-21.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * The Room database.
 */
@Database(entities = {DictionaryEntry.class}, version = 1)
public abstract class DictionaryDatabase extends RoomDatabase{
    /**
     * @return The DAO for the DictionaryEntry table.
     */
    @SuppressWarnings("WeakerAccess")
    public abstract DictionaryDao dictionaryDao();


    /** The only instance */
    private static DictionaryDatabase sInstance;

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */
    public static synchronized DictionaryDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), DictionaryDatabase.class, "ex").allowMainThreadQueries()
                    .build();
            sInstance.populateInitialData();
        }
        return sInstance;
    }

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    private void populateInitialData() {


        if (dictionaryDao().count() == 0) {
            DictionaryEntry word = new DictionaryEntry();
            beginTransaction();
            try {
                for (int i = 0; i < DictionaryEntry.WORDS.length; i++) {
                    word.setWord(DictionaryEntry.WORDS[i][0]);
                    word.setMeaning(DictionaryEntry.WORDS[i][1]);
                    dictionaryDao().insert(word);
                }
                setTransactionSuccessful();
            } finally {
                endTransaction();
            }
        }
    }

}
