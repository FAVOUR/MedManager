package com.example.olijefavour.med_manager;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

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
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView timeoftheday;
        TextView nameofdrug;

            timeoftheday = (TextView) view.findViewById(R.id.time_of_the_day);
            nameofdrug = (TextView) view.findViewById(R.id.name_of_drug);
        // Find the columns of pet attributes that we're interested in
        int medNameColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME);
        int MedTimeColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION);

        // Read the pet attributes from the Cursor for the current pet
        String medName= cursor.getString(medNameColumnIndex);
        String medTime = cursor.getString(MedTimeColumnIndex);

        // If the pet breed is
        // empty string or null, then use some default text
        // that says "Unknown breed", so the TextView isn't blank.
        if (TextUtils.isEmpty(medTime)) {
            medTime = context.getString(R.string.medication_name);
        }


        // Update the TextViews with the attributes for the current pet
        timeoftheday.setText(medTime);
         nameofdrug.setText(medName);





    }
}
