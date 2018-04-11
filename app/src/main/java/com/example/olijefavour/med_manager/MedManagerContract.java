/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.olijefavour.med_manager;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API Contract for the Pets app.
 */
public final class MedManagerContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private MedManagerContract() {}

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.example.olijefavour.med_manager";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_MED_MANAGER = "med_manager";

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class MedManagerEntry implements BaseColumns {

        /** The content URI to access the pet data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MED_MANAGER);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of medications.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MED_MANAGER;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single medications.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MED_MANAGER;

        /** Name of database table for pets */
        public final static String TABLE_NAME = "medications";

        /**
         * Unique ID number for the pet (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the medication.
         *
         * Type: TEXT
         */
        public final static String COLUMN_MEDICATION_NAME ="medication";

        /**
         * Description of the medication.
         *
         * Type: TEXT
         */
        public final static String COLUMN_MED_DESCRIPTION = "description";

        /**
         * frequency/Interval for taking the medication.
         *
         *
         * Type: INTEGER
//         */
        public final static String COLUMN_FREQUENCY_INTERVAL = "interval";

        /**
         * Weight of the pet.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_NUMBER_OF_MED_DAYS = "duration_of_medication";

//        /**
//         * Weight of the pet.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_START_MONTH= "start_month";


//        /**
//         * Weight of the pet.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_START_YEAR= "start_year";

//        /**
//         * Weight of the pet.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_END_DAY= "end_day";

//        /**
//         * Weight of the pet.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_END_MONTH= "end_month";


//        /**
//         * Weight of the pet.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_END_YEAR= "end_year";

        /**
         * Started taken the medication.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_START_DATE = "started_this_month";



        /**
         * Stoped takinhg the medication.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MED_END_DATE = "med_ends";

//        /**
//         * Stoped takinhg the medication.
//         *
//         * Type: INTEGER
//         */
//        public final static String  COLUMN_NUMBER_OF_MED_DAYS = "sday";
//        /**
//         * Stoped takinhg the medication.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_START_MONTH = "smonth";
//        /**
//         * Stoped takinhg the medication.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_START_YEAR = "syear";
//
//        /**
//         * Stoped takinhg the medication.
//         *
//         * Type: INTEGER
//         */
//        public final static String  COLUMN_END_DAY = "eday";
//        /**
//         * Stoped takinhg the medication.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_END_MONTH = "emonth";
//        /**
//         * Stoped takinhg the medication.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_END_YEAR = "eyear";
//        /**
//         * Stoped takinhg the medication.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_MILLS_TIME_START = "smiltime";
//        /**
//         * Stoped takinhg the medication.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_MILLS_TIME_END = "emilltime";
//        /**
//         * Stoped takinhg the medication.
//         *
//         * Type: INTEGER
//         */
//        public final static String COLUMN_TIME_FREQUENCY = "timefreq";
        /**
         * Possible frequencies.
         */
        public static final int FREQUENCY = 0;
        public static final int ONCE_A_DAY = 1;
        public static final int TWICE_A_DAY = 2;
        public static final int TRICE_A_DAY = 3;
        public static final int FOUR_TIMES_A_DAY = 4;
        public static final int FIVE_TIMES_A_DAY = 5;
        public static final int SIX_TIMES_A_DAY = 6;
        public static final int SEVEN_TIMES_A_DAY = 7;
        public static final int EIGHT_TIMES__A_DAY = 8;
        public static final int NINE_TIMES_A_DAY = 9;
        public static final int TEN_TIMES_A_DAY = 10;
        public static final int ELEVEN_TIMES_A_DAY = 11;
        public static final int TWELVE_TIMES_A_DAY = 12;
        public static final int EVERY_ONE_HOUR = 1;
        public static final int EVERY_TWO_HOUR = 2;
        public static final int EVERY_THREE_HOUR = 3;
        public static final int EVERY_FOUR_HOUR = 4;
        public static final int EVERY_FIVE_HOUR = 5;
        public static final int EVERY_SIX_HOUR = 6;
        public static final int EVERY_SEVEN_HOUR = 7;
        public static final int EVERY_EIGHT_HOUR = 8;
        public static final int EVERY_NINE_HOUR = 9;
        public static final int EVERY_TEN_HOUR = 10;
        public static final int EVERY_ELEVEN_HOUR = 11;
        public static final int EVERY_TWELVE_HOUR = 12;

//
//        /**
//         * Returns whether or not the given frequency is {@link #ONCE_A_DAY}, {@link #TWICE_A_DAY},
//         * or {@link #TRICE_A_DAY}.
//         */
//        public static boolean isValidFrequency(int frequency) {
//            if (frequency == ONCE_A_DAY || frequency == TWICE_A_DAY || frequency == TRICE_A_DAY) {
//                return true;
//            }
//            return false;
//        }
    }

}

