package com.example.olijefavour.med_manager;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ListView;

public class EachDayActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>{

    private ListView medListView;
    private int LOADER_ID=0;
    private EachDayAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drug_and_time_list);

        medListView = (ListView)findViewById(R.id.lv_list);


//      COMPLETED (4) Pass in this again as the ForecastAdapter now requires a Context
        /*
         * The ForecastAdapter is responsible for linking our weather data with the Views that
         * will end up displaying our weather data.
         *
         * Although passing in "this" twice may seem strange, it is actually a sign of separation
         * of concerns, which is best programming practice. The ForecastAdapter requires an
         * Android Context (which all Activities are) as well as an onClickHandler. Since our
         * MainActivity implements the ForecastAdapter ForecastOnClickHandler interface, "this"
         * is also an instance of that type of handler.
         */
        View emptyView = findViewById(R.id.empty_view);
        medListView.setEmptyView(emptyView);

        // Setup an Adapter to create a list item for each row of pet data in the Cursor.
        // There is no pet data yet (until the loader finishes) so pass in null for the Cursor.
        mCursorAdapter = new EachDayAdapter(this, null);
        medListView.setAdapter(mCursorAdapter);
        /*
         * Ensures a loader is initialized and active. If the loader doesn't already exist, one is
         * created and (if the activity/fragment is currently started) starts the loader. Otherwise
         * the last created loader is re-used.
         */

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                MedManagerContract.MedManagerEntry._ID,
                MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME,
                MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION};

        // This loader will execute the ContentProvider's query method on a background thread
        return new android.content.CursorLoader(this,   // Parent activity context
                MedManagerContract.MedManagerEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);

    }
}
