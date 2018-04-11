package com.example.olijefavour.med_manager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Olije favour on 4/5/2018.
 */

public class MonthsssAdapter extends BaseAdapter {
    private  Context context;
    private final int [] day;
    private View  inflatedLayout;
    LayoutInflater inflater;

    public MonthsssAdapter(Context context, int[] day){
        this.context = context;
        this.day = day;
//        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return day.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

//        TextView dummyTextView = new TextView(mContext);
//        dummyTextView.setText(String.valueOf(position));
//        dummyTextView.setBackgroundColor(Color.parseColor("#bdbdbd"));
//
//        return dummyTextView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (view == null) {


        gridView = inflater.inflate(R.layout.drug_image_name_grid, null, false);
//            String number_of_children= Integer.toString(linearLayout.getChildCount());
//           LinearLayout child = (LinearLayout) linearLayout.findViewById(R.id.dosage_time_);

//        LinearLayout root = (LinearLayout) gridView.findViewById(R.id.drug_name_image);

        ImageView drug_image =(ImageView) gridView.findViewById(R.id.drug_image);
        drug_image.setImageResource(R.drawable.ic_add_pet);


        TextView nameOfDrug =(TextView) gridView.findViewById(R.id.drug_name);
        nameOfDrug.setText("gosilla");
        }
        else{
            gridView = (View) view;
        }
        return gridView;


//                return  setupTextViews(5);
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

//    private void clearViews() {
//////        now to remove the whole layout
//        linearLayout.removeAllViews();
//        dosage="";
//        time="";
//        toSave.clear();
//    }

    private View setupTextViews(int numberofTextViews) {
//        infaltedParentView.clear();
//        for (int i = 0; i < numberofTextViews; i++) {
            LayoutInflater inflater = LayoutInflater.from(context);
            inflatedLayout= inflater.inflate(R.layout.drug_image_name_grid, null, false);
//            String number_of_children= Integer.toString(linearLayout.getChildCount());
//           LinearLayout child = (LinearLayout) linearLayout.findViewById(R.id.dosage_time_);

            LinearLayout root = (LinearLayout) inflatedLayout.findViewById(R.id.drug_name_image);

            ImageView drug_image =(ImageView)inflatedLayout.findViewById(R.id.drug_image);
//            drug_image.setText("gosilla");

            final TextView nameOfDrug =(TextView)inflatedLayout.findViewById(R.id.drug_name);
            nameOfDrug.setText("gosilla");
//           TextView dosage =(TextView)inflatedLayout.findViewById(R.id.number_of_drugs);


//            nameOfDrug.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    TimePickerDialog td = new TimePickerDialog(EditorActivity.this,new TimePickerDialog.OnTimeSetListener() {
//                        @Override
//                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                            try {
//                                String dtStart = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
//                                format = new SimpleDateFormat("HH:mm");
//
//                                timeValue = new java.sql.Time(format.parse(dtStart).getTime());
////
//                                String     amPm   = hourOfDay % 12 + ":" + minute + " " + ((hourOfDay >= 12) ? "PM" : "AM");
//                                gp=amPm;
//                                nameOfDrug.setText(amPm );
////                                mTime.setText(amPm + "\n" + String.valueOf(timeValue));
//                            } catch (Exception ex) {
//                                nameOfDrug.setText(ex.getMessage().toString());
//                            }
//                        }
//                    },
//                            hour, min,
//                            DateFormat.is24HourFormat(EditorActivity.this)
//                    );
//                    td.show();
//                }
//            });

//            String date_time = getNumbers(gp);
//            dosage.setText(date_time);
//          String done= nameOfDrug.getText().toString();

//            root.addView(inflatedLayout);
//            infaltedParentView.add(inflatedLayout);


//        }
        return inflatedLayout;
    }

//    private ArrayList<String> iteratethroughViews(ArrayList<View> DosageAndTimeViews){
//        String dosageToBeTaken="";
//        String sTimeOfTheDay="";
//        String getSize= Integer.toString(DosageAndTimeViews.size()) ;
//
//        for(View eachDosageAndTimeView : DosageAndTimeViews){
//            TextView timeOfTheDay =(TextView) eachDosageAndTimeView.findViewById(R.id.time_of_the_day);
//            TextView dosage =(TextView) eachDosageAndTimeView.findViewById(R.id.number_of_drugs);
//            dosageToBeTaken= dosage.getText().toString();
//            sTimeOfTheDay= timeOfTheDay.getText().toString();
//            String sDate_time = dosageToBeTaken+ "-"+sTimeOfTheDay +"-"+getSize;
//            date_time.add(sDate_time);
//        }
//
//        return date_time;
//    }
//    private void saveDosageAndTime(ArrayList<String> allDosageAndTime){
//        String goal="";
//        for(String eachDosageAndTime : allDosageAndTime){
//            getNumbers(eachDosageAndTime);
//            Toast.makeText(getBaseContext(), dosage + " " + time,Toast.LENGTH_SHORT).show();
//        }
////        return goal;
//    }
}
