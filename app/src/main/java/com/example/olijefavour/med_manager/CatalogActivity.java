package com.example.olijefavour.med_manager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.app.LoaderManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import java.util.Calendar;

import io.fabric.sdk.android.Fabric;

public class CatalogActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Identifier for the pet data loader
     */
    private static final int PET_LOADER = 0;

    /**
     * Adapter for the ListView
     */
    MedCursorAdapter mCursorAdapter;
    private Calendar today;
    private int currentYear;
    private long lastDayTimemils;
    private int lastDayOfTheMonth;
    private int currentMonth;
    private long firstDayTimemills;
    private Calendar firstdayTime;
    private long lastDayatMidnight;
    private long firstDayatMidnight;
    private int firstDayofTheMonth;
    private Cursor cursor;
    private Calendar callastDayOfTheMonth;
    private int currentDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Material Search");


        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });



        // Find the ListView which will be populated with the pet data
        ListView MedicationView = (ListView) findViewById(R
                .id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        MedicationView.setEmptyView(emptyView);

        // Setup an Adapter to create a list item for each row of pet data in the Cursor.
        // There is no pet data yet (until the loader finishes) so pass in null for the Cursor.
        mCursorAdapter = new MedCursorAdapter(this, null);
        MedicationView.setAdapter(mCursorAdapter);

        // Setup the item click listener
        MedicationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create new intent to go to {@link EditorActivity}
                Intent intent = new Intent(CatalogActivity.this, MedicationSummary.class);

                // Form the content URI that represents the specific pet that was clicked on,
                // by appending the "id" (passed as input to this method) onto the
                // {@link MedManagerEntry#CONTENT_URI}.
                // For example, the URI would be "content://com.example.android.pets/pets/2"
                // if the pet with ID 2 was clicked on.
                Uri currentPetUri = ContentUris.withAppendedId(MedManagerContract.MedManagerEntry.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(currentPetUri);

                intent.putExtra("id",id );

                // Launch the {@link EditorActivity} to display the data for the current medication.
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        firstDayofTheMonth=1;

        today = Calendar.getInstance();

        currentYear= today.get(Calendar.YEAR);
        currentMonth= today.get(Calendar.MONTH);
        currentDay = today.get(Calendar.DAY_OF_MONTH);

        today.set(currentYear,currentMonth,firstDayofTheMonth);

        firstDayTimemills= today.getTimeInMillis();
//        firstDayOfTheMonth = String.valueOf(firstDayTimemills);


        lastDayOfTheMonth = today.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//
//        firstdayTime = Calendar.getInstance();
//
//        firstdayTime.set(currentYear,currentMonth,firstDayofTheMonth,23,59);
//
//        firstDayatMidnight= today.getTimeInMillis();

//        today.set(currentYear,MONTH,firstDayofTheMonth);


//        String  sTotalDaysOfTheMonth= String.valueOf(lastDayOfTheMonth);
//        message.setText(sTotalDaysOfTheMonth);

//                final int days[]=new int[lastDayOfTheMonth];


        callastDayOfTheMonth = Calendar.getInstance();

//        lastDayOfTheMonth.set(currentYear,currentMonth, this.lastDayOfTheMonth);
//        lastDayTimemils= lastDayOfTheMonth.getTimeInMillis();
//        lastDayOfTheMonth = String.valueOf(lastDayTimemils);
//        setAlarm(Calendar.getInstance().getTimeInMillis(););


        callastDayOfTheMonth.set(currentYear,currentMonth, lastDayOfTheMonth,23,59);

        lastDayatMidnight= callastDayOfTheMonth.getTimeInMillis();

//

        Toast.makeText(this,"First days Midnight: " +firstDayTimemills + "Last days midnight" + lastDayatMidnight, Toast.LENGTH_LONG).show();

         cursor=getMedDays();

        int outCome= cursor.getCount();

        Toast.makeText(this,"Cursor count :" + outCome, Toast.LENGTH_LONG).show();


        // Kick off the loader
        getLoaderManager().initLoader(PET_LOADER, null, this);
    }

   private Cursor getMedDays(){
    String[] projection = {
            MedManagerContract.MedManagerEntry._ID,
            MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME,
            MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION,
            MedManagerContract.MedManagerEntry.COLUMN_NUMBER_OF_MED_DAYS,
            MedManagerContract.MedManagerEntry.COLUMN_START_MONTH,
            MedManagerContract.MedManagerEntry.COLUMN_MED_END_DATE,
            MedManagerContract.MedManagerEntry.COLUMN_START_DATE,
            MedManagerContract.MedManagerEntry.COLUMN_INTAKE_TIME,
            MedManagerContract.MedManagerEntry.COLUMN_FREQUENCY_INTERVAL,};
    String  selection =  MedManagerContract.MedManagerEntry.COLUMN_START_DATE + "> ?" + " AND " +  MedManagerContract.MedManagerEntry.COLUMN_MED_END_DATE + "< ?";

      String today = String.valueOf(firstDayTimemills);
       String lastDay =String.valueOf(lastDayatMidnight);

    String [] selectionArgs = new String[]{today,lastDay};

    Cursor cursor= getContentResolver().query(MedManagerContract.MedManagerEntry.CONTENT_URI,projection,selection,selectionArgs,null);

        return cursor;

    }

    private void setTheAlarm(Cursor cursor) {

        String time;
        int hour;
        int minute;

   while(cursor.moveToNext()) {
       int medNameColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME);
       int descriptionColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION);
       int dosagecolumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_DOSAGE);
       int timeIntakecolumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_INTAKE_TIME);


       int endDatecolumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_END_DATE);

       // Read the pet attributes from the Cursor for the current pet
       String medName = cursor.getString(medNameColumnIndex);
       String dosage = cursor.getString(dosagecolumnIndex);
       String _description = cursor.getString(descriptionColumnIndex);

       long _endDate = Long.parseLong(cursor.getString(endDatecolumnIndex));


       int timecolumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_TIME);
       String medTime = cursor.getString(timecolumnIndex);

       String[] parts = medTime.split(",");
       int totalTime = parts.length;

       Toast.makeText(this, "All time : " + totalTime, Toast.LENGTH_LONG).show();

       for (int i = 0; i < totalTime; i++) {
           time = parts[i];


           while (callastDayOfTheMonth.after(lastDayatMidnight) == false) {

               if (cursor != = null) {
                   String[] hourandMinute = time.split(":");
                   hour = Integer.parseInt(hourandMinute[0]);
                   minute = Integer.parseInt(hourandMinute[1]);

                   Toast.makeText(this, "hour : " + hour + "minute : " + minute, Toast.LENGTH_LONG).show();

                   Calendar eachTime = Calendar.getInstance();

                   eachTime.set(currentYear, currentMonth, currentDay, hour, minute);

                   ContentResolver cr = getBaseContext().getContentResolver();
                   ContentValues values = new ContentValues();
                   values.put(CalendarContract.Events.DTSTART, eachTime.getTimeInMillis());
                   values.put(CalendarContract.Events.DTEND, eachTime.getTimeInMillis() * 1000);
                   values.put(CalendarContract.Events.TITLE, medName);
                   values.put(CalendarContract.Events.DESCRIPTION, _description);
                   values.put(CalendarContract.Events.CALENDAR_ID, i);
                   values.put(CalendarContract.Events.EVENT_TIMEZONE, eachTime.getTimeZone().getID());

                   values.put(CalendarContract.Events.HAS_ALARM, 1);

                   // insert event to calendar
                   if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                       Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
                   }
               }
           }

       }
   }
    }
    private void setAlarm(long timeinmills) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeinmills, AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//
//
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.all_months) {
            Intent intent= new Intent(CatalogActivity.this,AllMonths.class);
            startActivity(intent);
        } else if (id == R.id.all_drugs) {
            Intent intent= new Intent(CatalogActivity.this,CatalogActivity.class);
            startActivity(intent);

        }
//        else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertPet() {
        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
//        ContentValues values = new ContentValues();
//        values.put(MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME, "Toto");
//        values.put(MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION, "Terrier");
//        values.put(MedManagerContract.MedManagerEntry.COLUMN_FREQUENCY_INTERVAL, MedManagerContract.MedManagerEntry.TWICE_A_DAY);
//        values.put(MedManagerContract.MedManagerEntry.COLUMN_PET_WEIGHT, 7);
//
//        // Insert a new row for Toto into the provider using the ContentResolver.
//        // Use the {@link MedManagerEntry#CONTENT_URI} to indicate that we want to insert
//        // into the pets database table.
//        // Receive the new content URI that will allow us to access Toto's data in the future.
//        Uri newUri = getContentResolver().insert(MedManagerContract.MedManagerEntry.CONTENT_URI, values);
    }

    /**
     * Helper method to delete all pets in the database.
     */
    private void deleteAllPets() {
        int rowsDeleted = getContentResolver().delete(MedManagerContract.MedManagerEntry.CONTENT_URI, null, null);
        Log.v("CatalogActivity", rowsDeleted + " rows deleted from pet database");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);

//        MenuItem searchItem = menu.findItem(R.id.action_search);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        if (searchView != null) {
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
        } else {
            Toast.makeText(this, "can't find search bar", Toast.LENGTH_LONG).show();
        }

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//
//                Toast.makeText(CatalogActivity.this, "Query Submitted!", Toast.LENGTH_LONG).show();
//
//                Intent intent= new Intent(CatalogActivity.this, SearchActivity.class);
//                startActivity(intent);
////                cursor=studentRepo.getStudentListByKeyword(s);
////                if (cursor==null){
////                    Toast.makeText(MainActivity.this,"No records found!",Toast.LENGTH_LONG).show();
////                }else{
////                    Toast.makeText(MainActivity.this, cursor.getCount() + " records found!",Toast.LENGTH_LONG).show();
////                }
////                customAdapter.swapCursor(cursor);
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                Toast.makeText(CatalogActivity.this, "Query Changed!", Toast.LENGTH_LONG).show();
////                Log.d(TAG, "onQueryTextChange ");
////                cursor=studentRepo.getStudentListByKeyword(s);
////                if (cursor!=null){
////                    customAdapter.swapCursor(cursor);
////                }
//                return false;
//            }
//
//        });
        return true;
//
//
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // User clicked on a menu option in the app bar overflow menu
//        switch (item.getItemId()) {
//            // Respond to a click on the "Insert dummy data" menu option
//            case R.id.search:
////                super.onSearchRequested();
//                Toast.makeText(this, "Search is clicked!", Toast.LENGTH_LONG).show();
//                return true;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }


//    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                MedManagerContract.MedManagerEntry._ID,
                MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME,
                MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION,
                MedManagerContract.MedManagerEntry.COLUMN_MED_END_DATE,
        };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                MedManagerContract.MedManagerEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update {@link MedCursorAdapter} with this new cursor containing updated pet data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);
    }


    }



