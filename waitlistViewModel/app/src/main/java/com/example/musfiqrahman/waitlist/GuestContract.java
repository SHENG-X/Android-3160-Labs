package com.example.musfiqrahman.waitlist;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ShengXiao on 2018-01-31.
 */

public class GuestContract {
    public static final String AUTHORITY="com.example.musfiqrahman.waitlist";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    public static final String PATH_GUEST = "guestinfo";

    private GuestContract(){

    }

    public static class GuestEntity implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon()
                        .appendPath(PATH_GUEST).build();

        /** The name of the DictionaryEntry table. */
        public static final String TABLE_NAME = "guestinfo";

        /** The name of the ID column. */
        public static final String COLUMN_ID = BaseColumns._ID;

        /** The name of the word column. */
        public static final String COLUMN_GUEST_NAME = "guestName";

        /** The name of the meaning column. */
        public static final String COLUMN_PARTY_SIZE = "partySize";
    }


}
