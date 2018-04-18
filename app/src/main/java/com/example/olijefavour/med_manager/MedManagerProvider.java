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

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.olijefavour.med_manager.MedManagerContract.MedManagerEntry;

/**
 * {@link ContentProvider} for Pets app.
 */
public class MedManagerProvider extends ContentProvider {

    /** Tag for the log messages */
    public static final String LOG_TAG = MedManagerProvider.class.getSimpleName();

    /** URI matcher code for the content URI for the pets table */
    private static final int MED_MANAGER = 100;

    /** URI matcher code for the content URI for a single medication in the pets table */
    private static final int MED_MANAGER_ID = 101;

    /** URI matcher code for the content URI for a month in the pets table */
    private static final int MED_MANAGER_MONTH = 102;



    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI of the form "content://com.example.android.pets/pets" will map to the
        // integer code {@link #MED_MANAGER}. This URI is used to provide access to MULTIPLE rows
        // of the pets table.
        sUriMatcher.addURI(MedManagerContract.CONTENT_AUTHORITY, MedManagerContract.PATH_MED_MANAGER, MED_MANAGER);

        // The content URI of the form "content://com.example.android.pets/pets/#" will map to the
        // integer code {@link #MED_MANAGER_ID}. This URI is used to provide access to ONE single row
        // of the medication table.
        //
        // In this case, the "#" wildcard is used where "#" can be substituted for an integer.
        // For example, "content://com.example.android.pets/pets/3" matches, but
        // "content://com.example.android.pets/pets" (without a number at the end) doesn't match.
        sUriMatcher.addURI(MedManagerContract.CONTENT_AUTHORITY, MedManagerContract.PATH_MED_MANAGER + "/#", MED_MANAGER_ID);

        // The content URI of the form "content://com.example.android.pets/pets/#" will map to the
        // integer code {@link #MED_MANAGER_ID}. This URI is used to provide access to ONE single row
        // of the medication table.
        //
        // In this case, the "#" wildcard is used where "#" can be substituted for an integer.
        // For example, "content://com.example.android.pets/pets/3" matches, but
        // "content://com.example.android.pets/pets" (without a number at the end) doesn't match.
        sUriMatcher.addURI(MedManagerContract.CONTENT_AUTHORITY, MedManagerContract.PATH_MED_MANAGER + "/*", MED_MANAGER_MONTH);

    }

    /** Database helper object */
    private MedDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new MedDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case MED_MANAGER:
                // For the MED_MANAGER code, query the pets table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the pets table.
                cursor = database.query(MedManagerContract.MedManagerEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case MED_MANAGER_ID:
                // For the MED_MANAGER_ID code, extract out the ID from the URI.
                // For an example URI such as "content://com.example.android.pets/pets/3",
                // the selection will be "_id=?" and the selection argument will be a
                // String array containing the actual ID of 3 in this case.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.
                selection = MedManagerContract.MedManagerEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                // This will perform a query on the pets table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(MedManagerEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;



            case MED_MANAGER_MONTH:
                // For the MED_MANAGER_ID code, extract out the ID from the URI.
                // For an example URI such as "content://com.example.android.pets/pets/3",
                // the selection will be "_id=?" and the selection argument will be a
                // String array containing the actual ID of 3 in this case.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.
                selection = MedManagerEntry.COLUMN_START_MONTH + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};

                // This will perform a query on the pets table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(MedManagerEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the cursor
        return cursor;
    }


    private Cursor searchResult(String selection, String[] selectionArgs, String[] columns) {
        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;
        cursor = database.query(MedManagerEntry.TABLE_NAME,columns,selection,selectionArgs,null,null,null);
//
//        if (cursor == null) {
//            return null;
//        } else if (!cursor.moveToFirst()) {
//            cursor.close();
//            return null;
//        }
//        cursor.close();
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MED_MANAGER:
                return insertMedData(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /**
     * Insert a pet into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertMedData(Uri uri, ContentValues values) {
        // Check that the searchName is not null
        String name = values.getAsString(MedManagerEntry.COLUMN_MEDICATION_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Medication requires a searchName");
        }

        // Check that the frequency is valid
        String frequency = values.getAsString(MedManagerEntry.COLUMN_MED_DESCRIPTION);
        if (frequency == null) {
            throw new IllegalArgumentException("Medication requires valid Interval");
        }

        // If the weight is provided, check that it's greater than or equal to 0 kg
        Integer medInterval = values.getAsInteger(MedManagerEntry.COLUMN_FREQUENCY_INTERVAL);
        if (medInterval != null && medInterval < 0) {
            throw new IllegalArgumentException("Medication requires valid interval");
        }
        String startMedication = values.getAsString(MedManagerEntry.COLUMN_START_DATE);
        if (startMedication.isEmpty()) {
            throw new IllegalArgumentException("Medication requires valid StartDate");
        }
        String endMedicatio = values.getAsString(MedManagerEntry.COLUMN_MED_END_DATE);
        if (endMedicatio.isEmpty()) {
            throw new IllegalArgumentException("Medication requires valid EndDate");
        }


        Integer medNumberOfDays = values.getAsInteger(MedManagerEntry.COLUMN_NUMBER_OF_MED_DAYS);
        if (medNumberOfDays<0) {
            throw new IllegalArgumentException("Medication requires valid numberofDays");
        }


        String medStartMonth = values.getAsString(MedManagerEntry.COLUMN_START_MONTH);
        if (medStartMonth.isEmpty()) {
            throw new IllegalArgumentException("Medication requires valid startmonth");
        }


        String medDosage= values.getAsString(MedManagerEntry.COLUMN_DOSAGE);
        if (medDosage.isEmpty()) {
            throw new IllegalArgumentException("Medication requires valid dosage");
        }

        String medTiming= values.getAsString(MedManagerEntry.COLUMN_TIME);
        if (medTiming.isEmpty()) {
            throw new IllegalArgumentException("Medication requires valid time");
        }
        // If the weight is provided, check that it's greater than or equal to 0 kg
        Integer timeIntervalaDay = values.getAsInteger(MedManagerEntry.COLUMN_INTAKE_TIME);
        if ( timeIntervalaDay!= null && medInterval < 0) {
            throw new IllegalArgumentException("Medication requires valid interval");
        }
//
//        String medicationStartYear = values.getAsString(MedManagerEntry.COLUMN_START_YEAR);
//        if (medicationStartYear.isEmpty()) {
//            throw new IllegalArgumentException("Medication requires valid startyear");
//        }
        // No need to check the breed, any value is valid (including null).

        // Get writable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(MedManagerEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the pet content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MED_MANAGER:
                return updateMedication(uri, contentValues, selection, selectionArgs);
            case MED_MANAGER_ID:
                // For the MED_MANAGER_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = MedManagerContract.MedManagerEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateMedication(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    /**
     * Update pets in the database with the given content values. Apply the changes to the rows
     * specified in the selection and selection arguments (which could be 0 or 1 or more pets).
     * Return the number of rows that were successfully updated.
     */
    private int updateMedication(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // If the {@link MedManagerEntry#COLUMN_MEDICATION_NAME} key is present,
        // check that the searchName value is not null.
        if (values.containsKey(MedManagerEntry.COLUMN_MEDICATION_NAME)) {
            String name = values.getAsString(MedManagerEntry.COLUMN_MEDICATION_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Medication requires a searchName");
            }
        }

        // If the {@link MedManagerEntry#COLUMN_FREQUENCY_INTERVAL} key is present,
        // check that the gender value is valid.
        if (values.containsKey(MedManagerEntry.COLUMN_FREQUENCY_INTERVAL)) {
            Integer frequency_interval = values.getAsInteger(MedManagerEntry.COLUMN_FREQUENCY_INTERVAL);
            if (frequency_interval == null) {
                throw new IllegalArgumentException("Medication requires valid frequency_interval");
            }
        }

        // If the {@link MedManagerEntry#COLUMN_FREQUENCY_INTERVAL} key is present,
        // check that the gender value is valid.
        if (values.containsKey(MedManagerEntry.COLUMN_INTAKE_TIME)) {
            Integer timeIntervalaDay = values.getAsInteger(MedManagerEntry.COLUMN_INTAKE_TIME);
            if (timeIntervalaDay == null) {
                throw new IllegalArgumentException("Medication requires valid time_interval_a_day");
            }
        }

// If the {@link MedManagerEntry#COLUMN_PET_WEIGHT} key is present,
//         check that the weight value is valid.
        if (values.containsKey(MedManagerEntry.COLUMN_START_DATE)) {
            // Check that the weight is greater than or equal to 0 kg
            String startDate = values.getAsString(MedManagerEntry.COLUMN_START_DATE);
            if (startDate.isEmpty()) {
                throw new IllegalArgumentException("Medication requires valid startdate");
            }
        }

        if (values.containsKey(MedManagerEntry.COLUMN_MED_END_DATE)) {
            // Check that the weight is greater than or equal to 0 kg
            String startDate = values.getAsString(MedManagerEntry.COLUMN_MED_END_DATE);
            if (startDate.isEmpty()) {
                throw new IllegalArgumentException("Medication requires valid startdate");
            }
        }

//        If the {@link MedManagerEntry#COLUMN_PET_WEIGHT} key is present,
        // check that the weight value is valid.
        if (values.containsKey(MedManagerEntry.COLUMN_NUMBER_OF_MED_DAYS)) {
            // Check that the weight is greater than or equal to 0 kg
            Integer medNumberOfDays = values.getAsInteger(MedManagerEntry.COLUMN_NUMBER_OF_MED_DAYS);
            if (medNumberOfDays<0) {
                throw new IllegalArgumentException("Medication requires valid number of days");
            }
        }
//
 //       If the {@link MedManagerEntry#COLUMN_PET_WEIGHT} key is present,
        // check that the weight value is valid.
        if (values.containsKey(MedManagerEntry.COLUMN_START_MONTH)) {
            // Check that the weight is greater than or equal to 0 kg
            String startDate = values.getAsString(MedManagerEntry.COLUMN_START_MONTH);
            if (startDate.isEmpty()) {
                throw new IllegalArgumentException("Medication requires valid start month");
            }
        }
        if (values.containsKey(MedManagerEntry.COLUMN_DOSAGE)) {
            // Check that the weight is greater than or equal to 0 kg
            String dosge = values.getAsString(MedManagerEntry.COLUMN_DOSAGE);
            if (dosge.isEmpty()) {
                throw new IllegalArgumentException("Medication requires valid dosage");
            }
        }

        if (values.containsKey(MedManagerEntry.COLUMN_TIME)) {
            // Check that the weight is greater than or equal to 0 kg
            String times = values.getAsString(MedManagerEntry.COLUMN_TIME);
            if (times.isEmpty()) {
                throw new IllegalArgumentException("Medication requires valid time");
            }
        }

//        String medDosage = values.getAsString(MedManagerEntry.COLUMN_DOSAGE);
//        if (medStartMonth.isEmpty()) {
//            throw new IllegalArgumentException("Medication requires valid dosage");
//        }
//
//        String medTiming = values.getAsString(MedManagerEntry.COLUMN_TIME);
//        if (medStartMonth.isEmpty()) {
//            throw new IllegalArgumentException("Medication requires valid time");
//        }

// //       If the {@link MedManagerEntry#COLUMN_PET_WEIGHT} key is present,
//        // check that the weight value is valid.
//        if (values.containsKey(MedManagerEntry.COLUMN_START_DATE)) {
//            // Check that the weight is greater than or equal to 0 kg
//            String startDate = values.getAsString(MedManagerEntry.COLUMN_START_DATE);
//            if (startDate.isEmpty()) {
//                throw new IllegalArgumentException("Medication requires valid weight");
//            }
//        }
//// If the {@link MedManagerEntry#COLUMN_PET_WEIGHT} key is present,
//        // check that the weight value is valid.
//        if (values.containsKey(MedManagerEntry.COLUMN_MED_END_DATE)) {
//            // Check that the weight is greater than or equal to 0 kg
//            String endDate = values.getAsString(MedManagerEntry.COLUMN_MED_END_DATE);
//            if (endDate.isEmpty()) {
//                throw new IllegalArgumentException("Medication requires valid weight");
//            }
//        }

        // No need to check the breed, any value is valid (including null).

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(MedManagerContract.MedManagerEntry.TABLE_NAME, values, selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MED_MANAGER:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(MedManagerContract.MedManagerEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case MED_MANAGER_ID:
                // Delete a single row given by the ID in the URI
                selection = MedManagerContract.MedManagerEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(MedManagerContract.MedManagerEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MED_MANAGER:
                return MedManagerContract.MedManagerEntry.CONTENT_LIST_TYPE;
            case MED_MANAGER_ID:
                return MedManagerContract.MedManagerEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}
