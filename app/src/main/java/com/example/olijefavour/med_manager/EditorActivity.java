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

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Allows user to create a new pet or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    /** Identifier for the pet data loader */
    private static final int EXISTING_PET_LOADER = 0;

    /** Content URI for the existing pet (null if it's a new pet) */
    private Uri mCurrentMedManagerUri;

    /** EditText field to enter the pet's name */
    private EditText mNameEditText;

    /** EditText field to enter the pet's breed */
    private EditText mDescriptionEditText;

    /** EditText field to enter the pet's gender */
    private Spinner mFrequencySpinner;


    /** EditText field to enter the pet's breed */
    private TextView mStartDate;

    /** EditText field to enter the pet's breed */
    private TextView mEndDate;

    String completeStartDate;

    TextView mTime;
    Calendar mCurrentDate;

    private int day,month,year;
    private int numberofTextViews;

    java.sql.Time timeValue;
    SimpleDateFormat format;
    int hour, min;
    public String gp;
    private LinearLayout linearLayout;
//
//
//    private RecyclerView mMainlist;
//    private DosageAndTimeAdapter DosageAndTimeAdapter;
//
//    private List<DosageAndTime> listDosageAndTime;
//    private DosageAndTime mDosageAndTime;
    private int mInterval = MedManagerContract.MedManagerEntry.FREQUENCY;


//    /**
//     * Gender of the pet. The possible valid values are in the MedManagerContract.java file:
//     * {@link MedManagerEntry#ONCE_A_DAY}, {@link MedManagerEntry#TWICE_A_DAY}, or
//     * {@link MedManagerEntry#TRICE_A_DAY}.
//     */


    /** Boolean flag that keeps track of whether the pet has been edited (true) or not (false) */
    private boolean mMedHasChanged = false;

    /**
     * OnTouchListener that listens for any user touches on a View, implying that they are modifying
     * the view, and we change the mMedHasChanged boolean to true.
     */
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mMedHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Examine the intent that was used to launch this activity,
        // in order to figure out if we're creating a new pet or editing an existing one.
        Intent intent = getIntent();
        mCurrentMedManagerUri = intent.getData();



        // If the intent DOES NOT contain a pet content URI, then we know that we are
        // creating a new pet.
        if (mCurrentMedManagerUri == null) {
            // This is a new pet, so change the app bar to say "Add a Pet"
            setTitle(getString(R.string.editor_activity_title_new_pet));
            numberofTextViews=1;

            // Invalidate the options menu, so the "Delete" menu option can be hidden.
            // (It doesn't make sense to delete a pet that hasn't been created yet.)
            invalidateOptionsMenu();
        } else {
            // Otherwise this is an existing pet, so change app bar to say "Edit Pet"
            setTitle(getString(R.string.editor_activity_title_edit_pet));

            // Initialize a loader to read the pet data from the database
            // and display the current values in the editor
            getLoaderManager().initLoader(EXISTING_PET_LOADER, null, this);
        }

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_med_name);
        mDescriptionEditText = (EditText) findViewById(R.id.drug_description);
        mFrequencySpinner = (Spinner) findViewById(R.id.spinner_interval);
        mStartDate=(TextView) findViewById(R.id.date_picker);
        mEndDate=(TextView) findViewById(R.id.end_date);

        mTime=(TextView) findViewById(R.id.time_picker);
        mCurrentDate=Calendar.getInstance();
        linearLayout=(LinearLayout)findViewById(R.id.dosage_time_);

        day=mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month=mCurrentDate.get(Calendar.MONTH);
        year=mCurrentDate.get(Calendar.YEAR);


//        month= month + 1;
        completeStartDate = day+"/"+ month+"/"+ year;
                mStartDate.setText(completeStartDate);

        hour = mCurrentDate.get(Calendar.HOUR_OF_DAY);
        String sHour= Integer.toString(hour);
        min = mCurrentDate.get(Calendar.MINUTE);
        String sMin= Integer.toString(min);
//        mDosageAndTime=new DosageAndTime();
////        mDosageAndTime.setDosage("1");
////        mDosageAndTime.setTime("8","45");
//        listDosageAndTime = new ArrayList<>();
////        mMainlist.setHasFixedSize(true);
////        listDosageAndTime.add(mDosageAndTime);
//        LinearLayoutManager  wo=new LinearLayoutManager(this);
//        mMainlist.setLayoutManager(wo);
//        DosageAndTimeAdapter =new DosageAndTimeAdapter(listDosageAndTime);


//        mMainlist.setAdapter(DosageAndTimeAdapter);

        mStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog mDatePickerDialog=new DatePickerDialog(EditorActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yearChoosen, int monthOftheYear, int dayOfTheMonth) {
                        monthOftheYear =monthOftheYear +1;
                        month=monthOftheYear;
                        day=dayOfTheMonth;
                        year=yearChoosen;
                        completeStartDate= dayOfTheMonth + "/" + monthOftheYear+ "/" + yearChoosen;
                        mStartDate.setText(completeStartDate);
                    }
                },year,month,day);


                mDatePickerDialog.show();

            }
        });

//        mEndDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog mDatePickerDialog=new DatePickerDialog(EditorActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int eYear, int eMonthOftheYear, int eDayOfTheMonth) {
////
//
//                        mEndDate.setText(eDayOfTheMonth + "/" + eMonthOftheYear + "/" +  eMonthOftheYear);
//                    }
//                },year,month,day);
//
//
//                mDatePickerDialog.show();
//
//            }
//        });


        //Time picker
       mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TimePickerDialog td = new TimePickerDialog(EditorActivity.this,new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                try {
                                    String dtStart = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                                    format = new SimpleDateFormat("HH:mm");

                                    timeValue = new java.sql.Time(format.parse(dtStart).getTime());
                                    mTime.setText(String.valueOf(timeValue));
                                    String amPm = hourOfDay % 12 + ":" + minute + " " + ((hourOfDay >= 12) ? "PM" : "AM");
                                    mTime.setText(amPm + "\n" + String.valueOf(timeValue));
                                } catch (Exception ex) {
                                    mTime.setText(ex.getMessage().toString());
                                }
                            }
                        },
                        hour, min,
                        DateFormat.is24HourFormat(EditorActivity.this)
                );
                td.show();
            }
        });

        // Setup OnTouchListeners on all the input fields, so we can determine if the user
        // has touched or modified them. This will let us know if there are unsaved changes
        // or not, if the user tries to leave the editor without saving.
        mNameEditText.setOnTouchListener(mTouchListener);
        mDescriptionEditText.setOnTouchListener(mTouchListener);
        mStartDate.setOnTouchListener(mTouchListener);
        mEndDate.setOnTouchListener(mTouchListener);
        mFrequencySpinner.setOnTouchListener(mTouchListener);

        setupSpinner();


    }


    private void deleteViews() {
////        now to remove the whole layout
        linearLayout.removeAllViews();
    }

        private void setupTextViews(int numberofTextViews) {

        for (int i = 0; i < numberofTextViews; i++) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View inflatedLayout= inflater.inflate(R.layout.dosage_time_list, null, false);
//           String number_of_children= Integer.toString(linearLayout.getChildCount());
//           LinearLayout child = (LinearLayout) inflatedLayout.findViewById(R.id.dosage_time);
//           View yeel = child.getChildAt(0);
//           TextView ok=(TextView) yeel.findViewById(R.id.number_of_drugs);
//            ok.setText("gosilla");
            
           final TextView timeOfTheDay =(TextView)inflatedLayout.findViewById(R.id.time_of_the_day);
//           TextView dosage =(TextView)inflatedLayout.findViewById(R.id.number_of_drugs);


            timeOfTheDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    TimePickerDialog td = new TimePickerDialog(EditorActivity.this,new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            try {
                                String dtStart = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                                format = new SimpleDateFormat("HH:mm");

                                timeValue = new java.sql.Time(format.parse(dtStart).getTime());
//
                                String     amPm   = hourOfDay % 12 + ":" + minute + " " + ((hourOfDay >= 12) ? "PM" : "AM");
                                gp=amPm;
                                timeOfTheDay.setText(amPm );
//                                mTime.setText(amPm + "\n" + String.valueOf(timeValue));
                            } catch (Exception ex) {
                                timeOfTheDay.setText(ex.getMessage().toString());
                            }
                        }
                    },
                            hour, min,
                            DateFormat.is24HourFormat(EditorActivity.this)
                    );
                    td.show();
                }
            });


//            String output = getNumbers(gp);
//            dosage.setText(output);
//          String done= timeOfTheDay.getText().toString();

            linearLayout.addView(inflatedLayout);
            ArrayList<LinearLayout> infaltedParentView=new ArrayList<>();


        }
    }

//        TextView textView1 = new TextView(this);
//        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//        textView1.setText("programmatically created TextView1");
//        textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
//        textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
//        linearLayout.addView(textView1);


//kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk;


//    private String getNumbers(String data){
//        String [] yo= data.split(":");
//      String go= yo[1] +", " +yo[2]  +", " +yo[3];
//      return go;
//    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_frequency, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mFrequencySpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mFrequencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {

                    switch (position) {
                        // Respond to a click on the "Save" menu option
                        case 1:
                            // Save pet to database
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.ONCE_A_DAY;
                            setupTextViews(1);
                            mEndDate.setText(selection);

                            break;
                        case 2:
                            deleteViews();
                            // Save pet to database
                            mInterval = MedManagerContract.MedManagerEntry.TWICE_A_DAY;
                            setupTextViews(2);
                            mEndDate.setText(selection);
                            break;
                        case 3:
                            // Save pet to database
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.TRICE_A_DAY;
                            setupTextViews(3);
                            mEndDate.setText(selection);

                            break;
                        case 4:
                            // Save pet to database
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.FOUR_TIMES_A_DAY;
                            setupTextViews(4);
                            mEndDate.setText(selection);
                            break;
                        case 5:
                            // Save pet to database
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.FIVE_TIMES_A_DAY;
                            setupTextViews(5);
                            mEndDate.setText(selection);
                            break;
                        case 6:
                            // Save pet to database
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.SIX_TIMES_A_DAY;
                            mEndDate.setText(selection);
                            break;
                        case 7:
                            // Save pet to database
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.SEVEN_TIMES_A_DAY;
                            mEndDate.setText(selection);
                            break;
                        case 8:
                            // Save pet to database
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.EIGHT_TIMES__A_DAY;
                            mEndDate.setText(selection);
                            break;
                        case 9:
                            // Save pet to database
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.NINE_TIMES_A_DAY;
                            mEndDate.setText(selection);
                            break;
                            case 10:
                            // Save pet to database
                                deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.TEN_TIMES_A_DAY;
                            mEndDate.setText(selection);
                            break;
                        case 11:
                            // Save pet to database
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.ELEVEN_TIMES_A_DAY;
                            mEndDate.setText(selection);
                            break;
                        case 12:
                            // Save pet to
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.TWELVE_TIMES_A_DAY;
                            mEndDate.setText(selection);
                            break;
                        default:
                            deleteViews();
                            mInterval = MedManagerContract.MedManagerEntry.FREQUENCY;
                            mEndDate.setText(selection);
                            break;

//                    if (selection.equals(getString(R.string.twice_a_day))) {
//                        mFrequency_Interval = MedManagerContract.MedManagerEntry.TWICE_A_DAY;
//                    } else if (selection.equals(getString(R.string.trice_a_day))) {
//                        mFrequency_Interval = MedManagerContract.MedManagerEntry.TRICE_A_DAY;
//                    } else {
//                        mFrequency_Interval = MedManagerContract.MedManagerEntry.ONCE_A_DAY;
//                    }

                    }
                }
            }
            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mInterval = MedManagerContract.MedManagerEntry.FREQUENCY;
            }
        });
    }

    /**
     * Get user input from editor and save pet into database.
     */
    private void saveInfo() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String name = mNameEditText.getText().toString().trim();
        String description = mDescriptionEditText.getText().toString().trim();
        String startDate = mStartDate.getText().toString();
        String endDate = mEndDate.getText().toString().trim();


        // Check if this is supposed to be a new pet
        // and check if all the fields in the editor are blank
        if (mCurrentMedManagerUri == null &&
                TextUtils.isEmpty(name) && TextUtils.isEmpty(description) &&
                TextUtils.isEmpty(startDate) && TextUtils.isEmpty(endDate)&&
                mInterval == MedManagerContract.MedManagerEntry.FREQUENCY) {
            // Since no fields were modified, we can return early without creating a new pet.
            // No need to create ContentValues and no need to do any ContentProvider operations.
            return;
        }

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME, name);
        values.put(MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION, description);
        values.put(MedManagerContract.MedManagerEntry.COLUMN_FREQUENCY_INTERVAL, mInterval);
        values.put(MedManagerContract.MedManagerEntry.COLUMN_START_DATE, completeStartDate);
//        values.put(MedManagerContract.MedManagerEntry.COLUMN_END_DATE, endDate);
        values.put(MedManagerContract.MedManagerEntry.COLUMN_START_DAY, day);
        values.put(MedManagerContract.MedManagerEntry.COLUMN_START_MONTH, month);
        values.put(MedManagerContract.MedManagerEntry.COLUMN_START_YEAR, year);
//        values.put(MedManagerContract.MedManagerEntry.COLUMN_END_DAY, endDate);
//        values.put(MedManagerContract.MedManagerEntry.COLUMN_END_MONTH, completeStartDate);
//        values.put(MedManagerContract.MedManagerEntry.COLUMN_END_YEAR, endDate);
//        values.put(MedManagerContract.MedManagerEntry.COLUMN_START_DAY, completeStartDate);
//        values.put(MedManagerContract.MedManagerEntry.COLUMN_END_DATE, endDate);
//        // If the weight is not provided by the user, don't try to parse the string into an
//        // integer value. Use 0 by default.
//        int weight = 0;
//        if (!TextUtils.isEmpty(startDate)) {
//            weight = Integer.parseInt(startDate);
//        }


        // Determine if this is a new or existing pet by checking if mCurrentMedManagerUri is null or not
        if (mCurrentMedManagerUri == null) {
            // This is a NEW pet, so insert a new pet into the provider,
            // returning the content URI for the new pet.
            Uri newUri = getContentResolver().insert(MedManagerContract.MedManagerEntry.CONTENT_URI, values);

            // Show a toast message depending on whether or not the insertion was successful.
            if (newUri == null) {

                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, getString(R.string.editor_insert_pet_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, name + description+ mInterval+completeStartDate,Toast.LENGTH_SHORT).show();
                Toast.makeText(this, getString(R.string.editor_insert_pet_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            // Otherwise this is an EXISTING pet, so update the pet with content URI: mCurrentMedManagerUri
            // and pass in the new ContentValues. Pass in null for the selection and selection args
            // because mCurrentMedManagerUri will already identify the correct row in the database that
            // we want to modify.
            int rowsAffected = getContentResolver().update(mCurrentMedManagerUri, values, null, null);

            // Show a toast message depending on whether or not the update was successful.
            if (rowsAffected == 0) {
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, getString(R.string.editor_update_pet_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_update_pet_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    /**
     * This method is called after invalidateOptionsMenu(), so that the
     * menu can be updated (some menu items can be hidden or made visible).
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new pet, hide the "Delete" menu item.
        if (mCurrentMedManagerUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                saveInfo();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Pop up confirmation dialog for deletion
                showDeleteConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the pet hasn't changed, continue with navigating up to parent activity
                // which is the {@link CatalogActivity}.
                if (!mMedHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                // Create a click listener to handle the user confirming that
                // changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        // If the data hasn't changed, continue with handling back button press
        if (!mMedHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Since the editor shows all pet attributes, define a projection that contains
        // all columns from the pet table
        String[] projection = {
                MedManagerContract.MedManagerEntry._ID,
                MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME,
                MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION,
                MedManagerContract.MedManagerEntry.COLUMN_FREQUENCY_INTERVAL,
                MedManagerContract.MedManagerEntry.COLUMN_START_DAY,
                MedManagerContract.MedManagerEntry.COLUMN_START_MONTH,
                MedManagerContract.MedManagerEntry.COLUMN_START_YEAR,
//                MedManagerContract.MedManagerEntry.COLUMN_END_DATE,
                MedManagerContract.MedManagerEntry.COLUMN_START_DATE };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentMedManagerUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of pet attributes that we're interested in
            int medNameColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME);
            int DescriptionColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION);
            int FrequencyColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_FREQUENCY_INTERVAL);
            int startDateColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_START_DATE);
            int startDayColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_START_DAY);
            int startMonthColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_START_MONTH);
            int startYearColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_START_YEAR);

//            int endDateColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_END_DATE);

            // Extract out the value from the Cursor for the given column index
            String Medname = cursor.getString(medNameColumnIndex);
            String Description = cursor.getString(DescriptionColumnIndex);
            int frequency = cursor.getInt(FrequencyColumnIndex);
            String startDate = cursor.getString(startDateColumnIndex);
            String startDay = cursor.getString(startDayColumnIndex);
            int startMonth = cursor.getInt(startMonthColumnIndex);
            String startYear = cursor.getString(startYearColumnIndex);
//            String  endDate = cursor.getString(endDateColumnIndex);

            // Update the views on the screen with the values from the database
            mNameEditText.setText(Medname);
            mDescriptionEditText.setText(Description);
            mStartDate.setText(startDate);

            mEndDate.setText(startDay+startMonth+startYear);

            // Gender is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.
            switch (frequency) {
                case MedManagerContract.MedManagerEntry.ONCE_A_DAY:
                    mFrequencySpinner.setSelection(1);
                    break;
                case MedManagerContract.MedManagerEntry.TWICE_A_DAY:
                    mFrequencySpinner.setSelection(2);
                    break;
                case MedManagerContract.MedManagerEntry.TRICE_A_DAY:
                    mFrequencySpinner.setSelection(3);
                    break;
                case MedManagerContract.MedManagerEntry.FOUR_TIMES_A_DAY:
                    mFrequencySpinner.setSelection(4);
                    break;
                case MedManagerContract.MedManagerEntry.FIVE_TIMES_A_DAY:
                    mFrequencySpinner.setSelection(5);
                    break;
                case MedManagerContract.MedManagerEntry.SIX_TIMES_A_DAY:
                    mFrequencySpinner.setSelection(6);
                    break;
                case MedManagerContract.MedManagerEntry.SEVEN_TIMES_A_DAY:
                    mFrequencySpinner.setSelection(7);
                    break;
                case MedManagerContract.MedManagerEntry.EIGHT_TIMES__A_DAY:
                    mFrequencySpinner.setSelection(8);
                    break;
                case MedManagerContract.MedManagerEntry.NINE_TIMES_A_DAY:
                    mFrequencySpinner.setSelection(9);
                    break;
                case MedManagerContract.MedManagerEntry.TEN_TIMES_A_DAY:
                    mFrequencySpinner.setSelection(10);
                    break;

                default:
                    mFrequencySpinner.setSelection(0);
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        mNameEditText.setText("");
        mDescriptionEditText.setText("");
        mStartDate.setText("");
        mEndDate.setText("");
        mFrequencySpinner.setSelection(0); // Select "Unknown" Date
    }

    /**
     * Show a dialog that warns the user there are unsaved changes that will be lost
     * if they continue leaving the editor.
     *
     * @param discardButtonClickListener is the click listener for what to do when
     *                                   the user confirms they want to discard their changes
     */
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Prompt the user to confirm that they want to delete this pet.
     */
    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                deletePet();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Perform the deletion of the pet in the database.
     */
    private void deletePet() {
        // Only perform the delete if this is an existing pet.
        if (mCurrentMedManagerUri != null) {
            // Call the ContentResolver to delete the pet at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentMedManagerUri
            // content URI already identifies the pet that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentMedManagerUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.editor_delete_pet_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_delete_pet_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }
}