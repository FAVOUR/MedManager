//package com.example.olijefavour.med_manager.TimeFragment;
//
//
//
//import android.database.Cursor;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.CursorLoader;
//import android.support.v4.content.Loader;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.olijefavour.med_manager.MedManagerContract;
//import com.example.olijefavour.med_manager.R;
//import com.example.olijefavour.med_manager.TimeOfTheDayRvAdapter;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class MorningFragment extends Fragment implements  LoaderManager.LoaderCallbacks<Cursor>{
//
//
////    public static final String[] MAIN_FORECAST_PROJECTION = {
////            MedManagerContract.MedManagerEntry.COLUMN_START_DATE,
//////            MedManagerContract.MedManagerEntry.COLUMN_MED_END_DATE,
////            MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME,
////    };
//
//    private RecyclerView mRecyclerView;
//    private static final int LOADER_ID = 44;
//    private TimeOfTheDayRvAdapter timeOfTheDayRvAdapter;
//
//
//    public MorningFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.drug_and_time_list, container, false);
//
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.lv_list);
//
////        View emptyView = view.findViewById(R.id.empty_view);
////        mRecyclerView.setEmptyView(emptyView);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//
//        /* setLayoutManager associates the LayoutManager we created above with our RecyclerView */
//        mRecyclerView.setLayoutManager(layoutManager);
//
//        /*
//         * Use this setting to improve performance if you know that changes in content do not
//         * change the child layout size in the RecyclerView
//         */
//        mRecyclerView.setHasFixedSize(true);
//
////      COMPLETED (4) Pass in this again as the ForecastAdapter now requires a Context
//        /*
//         * The ForecastAdapter is responsible for linking our weather data with the Views that
//         * will end up displaying our weather data.
//         *
//         * Although passing in "this" twice may seem strange, it is actually a sign of separation
//         * of concerns, which is best programming practice. The ForecastAdapter requires an
//         * Android Context (which all Activities are) as well as an onClickHandler. Since our
//         * MainActivity implements the ForecastAdapter ForecastOnClickHandler interface, "this"
//         * is also an instance of that type of handler.
//         */
//        timeOfTheDayRvAdapter = new TimeOfTheDayRvAdapter(getActivity(),null);
//
//        /* Setting the adapter attaches it to the RecyclerView in our layout. */
//        mRecyclerView.setAdapter(timeOfTheDayRvAdapter);
//
//
//        /*
//         * Ensures a loader is initialized and active. If the loader doesn't already exist, one is
//         * created and (if the activity/fragment is currently started) starts the loader. Otherwise
//         * the last created loader is re-used.
//         */
//
//        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
//        return view;
//    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        // Define a projection that specifies the columns from the table we care about.
//        String[] projection = {
//                MedManagerContract.MedManagerEntry._ID,
//                MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME,
//                MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION};
//
//        // This loader will execute the ContentProvider's query method on a background thread
//        return new CursorLoader(getActivity(),   // Parent activity context
//                MedManagerContract.MedManagerEntry.CONTENT_URI,   // Provider content URI to query
//                projection,             // Columns to include in the resulting Cursor
//                null,                   // No selection clause
//                null,                   // No selection arguments
//                null);
//
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        timeOfTheDayRvAdapter.swapCursor(data);
//    }
//
//
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        timeOfTheDayRvAdapter.swapCursor(null);
//    }
//}
