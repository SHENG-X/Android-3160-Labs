package com.example.musfiqrahman.samplecontentprovider.data;

import android.arch.persistence.room.*;
import android.database.Cursor;


/**
 * Created by musfiqrahman on 2018-01-21.
 */

@Dao
public interface DictionaryDao {
    /**
     * Counts the number of words in the dictionary.
     *
     * @return The number of words in the dictionary.
     */
    @Query("SELECT COUNT(*) FROM " + DictionaryContract.DictionaryEntity.TABLE_NAME)
    int count();

    /**
     * Inserts a words into the table.
     *
     * @param entry A new word.
     * @return The row ID of the newly inserted word.
     */
    @Insert
    long insert(DictionaryEntry entry);

    /**
     * Inserts multiple words into the database
     *
     * @param words An array of new cheeses.
     * @return The row IDs of the newly inserted words.
     */
    @Insert
    long[] insertAll(DictionaryEntry[] words);

    /**
     * Select all words.
     *
     * @return A {@link Cursor} of all the words in the table.
     */
    @Query("SELECT * FROM " + DictionaryContract.DictionaryEntity.TABLE_NAME)
    Cursor selectAll();


    /**
     * Select a word by the ID.
     *
     * @param id The row ID.
     * @return A {@link Cursor} of the selected word.
     */
    @Query("SELECT * FROM " + DictionaryContract.DictionaryEntity.TABLE_NAME + " WHERE " + DictionaryContract.DictionaryEntity.COLUMN_ID + " = :id")
    Cursor selectById(long id);



    /**
     * Delete a word by the ID.
     *
     * @param id The row ID.
     * @return A number of words deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + DictionaryContract.DictionaryEntity.TABLE_NAME + " WHERE " + DictionaryContract.DictionaryEntity.COLUMN_ID + " = :id")
    int deleteById(long id);


    /**
     * Update the words. The word is identified by the row ID.
     *
     * @param entry The word to update.
     * @return A number of words updated. This should always be {@code 1}.
     */
    @Update
    int update(DictionaryEntry entry);

}
