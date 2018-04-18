//package com.example.olijefavour.med_manager;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
///**
// * Created by Olije favour on 4/10/2018.
// */
//
//public class TimeOfTheDayRvAdapter extends  RecyclerView.Adapter<TimeOfTheDayRvAdapter.TimeOfTheDayRvAdapterViewHolder> {
//
//    Context mContext;
//
//    private Cursor mCursor;
//
//    public TimeOfTheDayRvAdapter(Context context, Cursor cursor) {
//        mContext = context;
//        mCursor = cursor;
//    }
//
//
//    @Override
//    public TimeOfTheDayRvAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//
//        View view = LayoutInflater
//                .from(mContext)
//                .inflate(R.layout.day_drug_list, viewGroup, false);
//
//
//        return new TimeOfTheDayRvAdapterViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(TimeOfTheDayRvAdapterViewHolder holder, int position) {
//
//
//        // Find the columns of pet attributes that we're interested in
//        int medNameColumnIndex = mCursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME);
//        int MedTimeColumnIndex = mCursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION);
//
//        // Read the pet attributes from the Cursor for the current pet
//        String medName= mCursor.getString(medNameColumnIndex);
//        String medTime = mCursor.getString(MedTimeColumnIndex);
//
//        // If the pet breed is
//        // empty string or null, then use some default text
//        // that says "Unknown breed", so the TextView isn't blank.
//        if (TextUtils.isEmpty(medTime)) {
//            medTime = mContext.getString(R.string.medication_name);
//        }
//
//
//        // If the pet breed is
//        // empty string or null, then use some default text
//        // that says "Unknown breed", so the TextView isn't blank.
//        if (TextUtils.isEmpty(medTime)) {
//            medTime = mContext.getString(R.string.medication_name);
//        }
//
//        // Update the TextViews with the attributes for the current pet
//        holder.timeoftheday.setText(medTime);
//        holder.nameofdrug.setText(medName);
//    }
//
//
//    public void swapCursor(Cursor newCursor) {
//        if(mCursor!=null){
//            mCursor.close();
//        }
//        mCursor = newCursor;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemCount() {
//        if (null == mCursor)
//            return 0;
//        return mCursor.getCount();
//    }
//
//    class TimeOfTheDayRvAdapterViewHolder extends RecyclerView.ViewHolder {
//        TextView timeoftheday;
//        TextView nameofdrug;
//
//        TimeOfTheDayRvAdapterViewHolder(View view) {
//            super(view);
//
//            timeoftheday = (TextView) view.findViewById(R.id.time_of_the_day);
//            nameofdrug = (TextView) view.findViewById(R.id.name_of_drug);
//        }
//
//    }
//}
