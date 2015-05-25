package com.example.yuchi.coming.common.database;

import android.provider.BaseColumns;

/**
 * Created by choes_000 on 2015/4/6.
 */
public final class TimerContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public TimerContract() {};

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_NULLABLE = "null";

        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_EVENT_CONTENT = "event";
        public static final String COLUMN_TIMER_CHANGETOSECOND = "second";

    }
}
