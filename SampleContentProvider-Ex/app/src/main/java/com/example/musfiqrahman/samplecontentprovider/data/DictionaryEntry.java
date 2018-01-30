package com.example.musfiqrahman.samplecontentprovider.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;

/**
 * Created by musfiqrahman on 2018-01-21.
 */
@Entity (tableName = DictionaryContract.DictionaryEntity.TABLE_NAME)
public class DictionaryEntry {
    /** The unique ID of the dictionary. */

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = DictionaryContract.DictionaryEntity.COLUMN_ID)
    private long id;


    /** The word field. */
    @ColumnInfo(name = DictionaryContract.DictionaryEntity.COLUMN_WORD)
    private String word;

    /** The word field. */
    @ColumnInfo(name = DictionaryContract.DictionaryEntity.COLUMN_MEANING)
    private String meaning;


    /**
     * Create a new {@link DictionaryEntry} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain on column name.
     * @return A newly created {@link DictionaryEntry} instance.
     */
    public static DictionaryEntry fromContentValues(ContentValues values) {
        final DictionaryEntry word = new DictionaryEntry();
        if (values.containsKey(DictionaryContract.DictionaryEntity.COLUMN_ID)) {
            word.setId(values.getAsLong(DictionaryContract.DictionaryEntity.COLUMN_ID));
        }
        if (values.containsKey(DictionaryContract.DictionaryEntity.COLUMN_WORD)) {
            word.setWord(values.getAsString(DictionaryContract.DictionaryEntity.COLUMN_WORD));
        }
        if (values.containsKey(DictionaryContract.DictionaryEntity.COLUMN_MEANING)) {
            word.setMeaning(values.getAsString(DictionaryContract.DictionaryEntity.COLUMN_MEANING));
        }
        return word;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }



    public static final String [][] WORDS = {   {"Word", "a single distinct meaningful element of speech or writing"},
                                                {"Easy", "achieved without great effort"},
                                                {"Car", "a road vehicle"},
                                            };

}
