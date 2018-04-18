package com.example.olijefavour.med_manager.MonthsFragment;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olijefavour.med_manager.EachDayActivity;
import com.example.olijefavour.med_manager.MedManagerContract;
import com.example.olijefavour.med_manager.MedicationDetails;
import com.example.olijefavour.med_manager.MonthsssAdapter;
import com.example.olijefavour.med_manager.R;

import java.util.ArrayList;
import java.util.Calendar;

public class SeptemberFragment extends Fragment implements  LoaderManager.LoaderCallbacks<Cursor> {

        int totalDaysOfTheMonth ;
        int firstDayofTheMonth;
        int startDate;
        int endDate;
private Calendar sepCalender;
private int currentYear;
private long firstDayTimemills;
private long lastDayTimemils;
private final int MONTH =8;
private String lastDayOfTheMonth;
private String firstDayOfTheMonth;
private ArrayList<MedicationDetails> listOfmedDetails;
private MedicationDetails medDetails;
private final int FORECAST_LOADER = 0;
private MonthsssAdapter septemberAdapter;

public SeptemberFragment() {
        // Required empty public constructor
        }


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_january, container, false);
//        message=(TextView)view.findViewById(R.id.januaryfragment);


        listOfmedDetails =new ArrayList<>();

        firstDayofTheMonth=1;

        sepCalender = Calendar.getInstance();

        currentYear= sepCalender.get(Calendar.YEAR);

        sepCalender.set(currentYear, MONTH,firstDayofTheMonth);

        firstDayTimemills= sepCalender.getTimeInMillis();
        firstDayOfTheMonth = String.valueOf(firstDayTimemills);


        totalDaysOfTheMonth= sepCalender.getActualMaximum(Calendar.DAY_OF_MONTH);



        sepCalender.set(currentYear,MONTH,firstDayofTheMonth);



        String  sTotalDaysOfTheMonth= String.valueOf(totalDaysOfTheMonth);


//                final int days[]=new int[totalDaysOfTheMonth];


        Calendar januaryEnding = Calendar.getInstance();

        januaryEnding.set(currentYear,MONTH,totalDaysOfTheMonth);
        lastDayTimemils=januaryEnding.getTimeInMillis();
        lastDayOfTheMonth = String.valueOf(lastDayTimemils);
        String addedaday =String.valueOf(firstDayTimemills+86400000);

//        message.append("Timemills of the firstDayOfTheMonth day: " + firstDayOfTheMonth);
//        message.append("Timemills for he lastDayOfTheMonth day : " + lastDayOfTheMonth);
//        message.append("time mills after adding a day to the firstDayOfTheMonth day: " + addedaday );
//        message.append("cursor count: " + String.valueOf(numberOfRows)  );
        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        septemberAdapter = new MonthsssAdapter(getActivity(), null);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

@Override
public void onItemClick(AdapterView<?> parent, View view,
        int position, long id) {
        int clickedposition =position;
        Uri currentPetUri = ContentUris.withAppendedId(MedManagerContract.MedManagerEntry.CONTENT_URI, id);
        Toast.makeText(getActivity(), "you clicked at..."+ clickedposition , Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getActivity(), EachDayActivity.class);

        // Set the URI on the data field of the intent
        intent.setData(currentPetUri);

        // Launch the {@link EditorActivity} to display the data for the current medication.
        startActivity(intent);

        startActivity(intent);

        }
        });
        gridView.setAdapter(septemberAdapter);
        return view;

        }

@Override
public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(FORECAST_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
        }


@Override
public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
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
        String  selection =  MedManagerContract.MedManagerEntry.COLUMN_START_DATE + "> ?" + " AND " +  MedManagerContract.MedManagerEntry.COLUMN_MED_END_DATE + "< ?";

        String [] selectionArgs = new String[]{firstDayOfTheMonth,lastDayOfTheMonth};






        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(getActivity(),   // Parent activity context
        MedManagerContract.MedManagerEntry.CONTENT_URI,   // Provider content URI to query
        projection,             // Columns to include in the resulting Cursor
        selection,                   // No selection clause
        selectionArgs,                   // No selection arguments
        null);



        }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        septemberAdapter.swapCursor(data);
    }



@Override
public void onLoaderReset(Loader<Cursor> loader) {
        septemberAdapter.swapCursor(null);
        }
}
