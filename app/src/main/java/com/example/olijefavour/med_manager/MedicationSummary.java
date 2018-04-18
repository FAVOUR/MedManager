package com.example.olijefavour.med_manager;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MedicationSummary extends AppCompatActivity {
    /**
     * Content URI for the clicked medication (null if it's a new pet)
     */
    private Uri mCurrentMedManagerUri;

    TextView medName;
    TextView medDescription;
    TextView medFrequency;
    TextView medStartDate;
    TextView duration;
    TextView medEndDate;
    long id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_summary);
        Intent intent = getIntent();
        id=intent.getLongExtra("id",0);
        mCurrentMedManagerUri = intent.getData();
        medName =(TextView)findViewById(R.id.med_name);
        medDescription =(TextView)findViewById(R.id.drug_description);
        medFrequency =(TextView)findViewById(R.id.frequency);
        medStartDate =(TextView)findViewById(R.id.start_date);
        medEndDate =(TextView)findViewById(R.id.end_date);
        duration =(TextView)findViewById(R.id.number_of_days);

        getDetails(mCurrentMedManagerUri);



        // Setup FAB to open EditorActivity
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab_edit);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MedicationSummary.this, EditorActivity.class);

                mCurrentMedManagerUri = ContentUris.withAppendedId(MedManagerContract.MedManagerEntry.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(mCurrentMedManagerUri);

                // Launch the {@link EditorActivity} to display the data for the current medication.
                startActivity(intent);
                finish();
            }
        });


    }


    private void getDetails(Uri mCurrentMedManagerUri) {
        String[] projection = {
                MedManagerContract.MedManagerEntry._ID,
                MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME,
                MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION,

                MedManagerContract.MedManagerEntry.COLUMN_NUMBER_OF_MED_DAYS,
                MedManagerContract.MedManagerEntry.COLUMN_START_MONTH,
//                MedManagerContract.MedManagerEntry.COLUMN_START_YEAR,
                MedManagerContract.MedManagerEntry.COLUMN_MED_END_DATE,
                MedManagerContract.MedManagerEntry.COLUMN_START_DATE,
                MedManagerContract.MedManagerEntry.COLUMN_FREQUENCY_INTERVAL,};

        // This loader will execute the ContentProvider's query method on a background thread
        Cursor cursor = getContentResolver().query(mCurrentMedManagerUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order

    if(cursor ==null||cursor.getCount() < 1)

    {
        return;
    }

    // Proceed with moving to the first row of the cursor and reading data from it
    // (This should be the only row in the cursor)
        if(cursor.moveToFirst())
    {
        // Find the columns of pet attributes that we're interested in
        int medNameColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME);
        int DescriptionColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION);
        int FrequencyColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_FREQUENCY_INTERVAL);
        int startDateColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_START_DATE);
        int endDateColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_END_DATE);
        int durationColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_NUMBER_OF_MED_DAYS);
//        int startMonthColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_START_MONTH);
//            int startYearColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_START_YEAR);

//            int endDateColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_END_DATE);

        // Extract out the value from the Cursor for the given column index
        String Medname = cursor.getString(medNameColumnIndex);
        String Description = cursor.getString(DescriptionColumnIndex);
        int frequency = cursor.getInt(FrequencyColumnIndex);
        String startDate = cursor.getString(startDateColumnIndex);
        String numberOfDays = cursor.getString(durationColumnIndex);
        if (numberOfDays.equals(1)){
           numberOfDays= numberOfDays + " day";
        }else{
            numberOfDays=numberOfDays + " days";
        }
        // Update the views on the screen with the values from the database
        medName.setText(Medname);
        medDescription.setText(Description);

        Calendar calStartDate= Calendar.getInstance();
        calStartDate.setTimeInMillis(Long.parseLong(startDate));

        Date dStartDate = calStartDate.getTime();
        SimpleDateFormat convertDateTo = new SimpleDateFormat("dd/MMM/yyyy");
        String dateToShow = convertDateTo.format(dStartDate);
//        Toast.makeText(this, dateToShow,
//                Toast.LENGTH_SHORT).show();


        medStartDate.setText(dateToShow);

        duration.setText(numberOfDays);


        String sEndDate = cursor.getString(endDateColumnIndex);
        Calendar calEndDate= Calendar.getInstance(); calEndDate.setTimeInMillis(Long.parseLong(sEndDate));
        Date dEndDate = calEndDate.getTime();
        String showEndDate = convertDateTo.format(dEndDate);

        medEndDate.setText(showEndDate);


        // Gender is a dropdown spinner, so map the constant value from the database
        // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
        // Then call setSelection() so that option is displayed on screen as the current selection.
        switch (frequency) {

            case 0:
               medFrequency.setText(getString(R.string.default_frequency));
                break;
            case 1:
                medFrequency.setText(getString(R.string.once_a_day));
                break;
            case 2:
                medFrequency.setText(getString(R.string.twice_a_day));
                break;
            case 3:
                medFrequency.setText(getString(R.string.trice_a_day));
                break;
            case 4:
                medFrequency.setText(getString(R.string.four_times_a_day));
                break;
            case 5:
                medFrequency.setText(getString(R.string.five_times_a_day));
                break;
            case 6:
                medFrequency.setText(getString(R.string.six_times_a_day));
                break;
            case 7:
                medFrequency.setText(getString(R.string.seven_times_a_day));
                break;
            case 8:
                medFrequency.setText(getString(R.string.eight_times_a_day));
                break;
            case 9:
                medFrequency.setText(getString(R.string.nine_times_day));
                break;
            case 10:
                medFrequency.setText(getString(R.string.ten_times_day));
                break;
            case 11:
                medFrequency.setText(getString(R.string.eleven_times_day));
                break;
            case 12:
                medFrequency.setText(getString(R.string.twelve_times_day));
                break;

            default:
                medFrequency.setText(getString(R.string.once_a_day));
                break;
        }
    }
}
}


