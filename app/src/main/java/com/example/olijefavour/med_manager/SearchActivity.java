package com.example.olijefavour.med_manager;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class SearchActivity extends AppCompatActivity {


    MedDbHelper  mDbHelper;
    private Cursor searchCursor;
    private ArrayList<String> searchList;
    private SearchAdapter searchAdapter;
    private RecyclerView rv;
    private LinearLayoutManager llm;
    private int valueOfSearchResult;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        handleIntent(getIntent());
          mDbHelper = new MedDbHelper(this);
         searchList=new ArrayList<>();




        rv = (RecyclerView)findViewById(R.id.rv);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        searchAdapter = new  SearchAdapter(this,searchList);
        rv.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);


            searchCursor = getWordMatches(query, null);

            valueOfSearchResult = searchCursor.getCount();
            Toast.makeText(SearchActivity.this, "Query :" + query, Toast.LENGTH_LONG).show();

            if (searchCursor == null) {
                Toast.makeText(SearchActivity.this, "No records found!", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(SearchActivity.this, "Found these matches" + valueOfSearchResult, Toast.LENGTH_LONG).show();
                for (int i = 0; i < valueOfSearchResult; i++) {

                    int medNameColumnIndex = searchCursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION);

                    // Read the pet attributes from the Cursor for the current pet
                    String medName = searchCursor.getString(medNameColumnIndex);
                    searchList.add(medName);

                }
            }

        }
    }


    public Cursor getWordMatches(String query, String[] columns) {
        String selection = MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME + " LIKE ?";
        String[] selectionArgs = new String[] {"'%" +query+ "%'" };
//        searchAdapter.notifyDataSetChanged();
        return query(selection, selectionArgs, columns);




    }


// rv.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
//    final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        rv.setLayoutManager(layoutManager);


    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
//        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
//        builder.setTables(FTS_VIRTUAL_TABLE);
        Cursor cursor = getContentResolver().query(MedManagerContract.MedManagerEntry.CONTENT_URI, columns,selection,selectionArgs,null);
        return cursor;
    }
}
