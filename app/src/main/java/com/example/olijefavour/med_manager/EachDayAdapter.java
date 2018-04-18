package com.example.olijefavour.med_manager;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Olije favour on 4/11/2018.
 */

public class EachDayAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link MedCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public EachDayAdapter(Context context, Cursor c) {

        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.day_drug_list, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the searchName for the current pet can be set on the searchName TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView description;
        TextView nameofdrug;
        TextView dosageofdrug;
        TextView drugtiming;
        TextView endDate;
        TextView  intakeTimes;


            description = (TextView) view.findViewById(R.id._description);
            nameofdrug = (TextView) view.findViewById(R.id._name_of_drug);
            dosageofdrug = (TextView) view.findViewById(R.id._quantity);
            drugtiming = (TextView) view.findViewById(R.id._timing);
            endDate    = (TextView) view.findViewById(R.id._endate);
            intakeTimes    = (TextView) view.findViewById(R.id._total_intake_time);


        // Find the columns of pet attributes that we're interested in
        int medNameColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME);
        int descriptionColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION);
        int dosagecolumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_DOSAGE);
        int timecolumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_TIME);
        int endDatecolumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_END_DATE);
        int intakeTimescolumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_INTAKE_TIME);
        // Read the pet attributes from the Cursor for the current pet
        String medName= cursor.getString(medNameColumnIndex);
        String medTime = cursor.getString(timecolumnIndex);
         String dosage=  cursor.getString(dosagecolumnIndex);
        String _description=  cursor.getString(descriptionColumnIndex);
        int _intakeTimes=  cursor.getInt(intakeTimescolumnIndex);
        long _endDate=  Long.parseLong(cursor.getString(endDatecolumnIndex));

        // If the pet breed is
        // empty string or null, then use some default text
//        // that says "Unknown breed", so the TextView isn't blank.
//        if (TextUtils.isEmpty(medTime)) {
//            medTime = context.getString(R.string.medication_name);
//        }


        // Update the TextViews with the attributes for the current pet
        description.setText(_description);
         nameofdrug.setText(medName);
        drugtiming.setText(medTime);
        dosageofdrug.setText(dosage);


        intakeTimes.setText(String.valueOf(_intakeTimes));

        if (_intakeTimes < 1){
            intakeTimes.append("day");
        }
        else{
            intakeTimes.append("days");
        }


        Calendar calEndDate =Calendar.getInstance();
        calEndDate.setTimeInMillis(_endDate);
        Date dStartDate = calEndDate.getTime();
        SimpleDateFormat convertDateTo = new SimpleDateFormat("dd-MMMM-yyyy");
        String dateToShow = convertDateTo.format(dStartDate);
        endDate.setText(dateToShow);





    }
}
