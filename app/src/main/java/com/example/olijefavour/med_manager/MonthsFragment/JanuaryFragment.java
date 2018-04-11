package com.example.olijefavour.med_manager.MonthsFragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olijefavour.med_manager.CatalogActivity;
import com.example.olijefavour.med_manager.EachDayActivity;
import com.example.olijefavour.med_manager.EditorActivity;
import com.example.olijefavour.med_manager.MonthsssAdapter;
import com.example.olijefavour.med_manager.R;
import com.example.olijefavour.med_manager.Timeinterval;

import java.util.Calendar;


public class JanuaryFragment extends Fragment {

    int totalDaysOfTheMonth ;
    int firstDayofTheMonth;
    int startDate;
    int endDate;
    private Calendar janCalender;
    private int currentYear;
    private TextView message;
    private long firstDayTimemills;
    private long lastDayTimemils;

    public JanuaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_january, container, false);
        message=(TextView)view.findViewById(R.id.januaryfragment);

        firstDayofTheMonth=1;

        janCalender = Calendar.getInstance();

        currentYear=janCalender.get(Calendar.YEAR);

        janCalender.set(currentYear,0,firstDayofTheMonth);

        firstDayTimemills=janCalender.getTimeInMillis();
        String first= String.valueOf(firstDayTimemills);


       totalDaysOfTheMonth= janCalender.getActualMaximum(Calendar.DAY_OF_MONTH);



        janCalender.set(currentYear,0,firstDayofTheMonth);


       String  sTotalDaysOfTheMonth= String.valueOf(totalDaysOfTheMonth);
        message.setText(sTotalDaysOfTheMonth);

                final int days[]=new int[totalDaysOfTheMonth];


        Calendar januaryEnding = Calendar.getInstance();

        januaryEnding.set(currentYear,0,totalDaysOfTheMonth);
        lastDayTimemils=januaryEnding.getTimeInMillis();
        String last= String.valueOf(lastDayTimemils);
        String addedaday =String.valueOf(firstDayTimemills+86400000);
        message.append("Timemills of the first day: " +first);
        message.append("Timemills for he last day : " +last);
        message.append("time mills after adding a day to the first day: " + addedaday );

//        for ()


//          days.length
        //      currentDay =mCurrentDate.get(Calendar.DAY_OF_MONTH);
//        month=mCurrentDate.get(Calendar.MONTH);


        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        MonthsssAdapter januaryAdapter = new MonthsssAdapter(getActivity(), days);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getActivity(), "you clicked at..." + days[position], Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), EachDayActivity.class);
                startActivity(intent);

            }
        });

        gridView.setAdapter(januaryAdapter);
        return view;

    }


}
