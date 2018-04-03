//package com.example.olijefavour.med_manager;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * Created by Olije favour on 4/2/2018.
// */
//
//    public class DosageAndTimeAdapter extends RecyclerView.Adapter<DosageAndTimeAdapter.ViewHolder> {
//
//    public List<DosageAndTime> mDosageTime;
//
//    public DosageAndTimeAdapter(List<DosageAndTime> dosage_time_list){
//
//        this.mDosageTime = dosage_time_list;
//    }
////
////        /**
////         * Makes a new blank list item view. No data is set (or bound) to the views yet.
////         *
////         * @param context app context
////         * @param cursor  The cursor from which to get the data. The cursor is already
////         *                moved to the correct position.
////         * @param parent  The parent to which the new view is attached to
////         * @return the newly created list item view.
////         */
////        @Override
////        public View newView(Context context, Cursor cursor, ViewGroup parent) {
////            // Inflate a list item view using the layout specified in list_item.xml
////            return LayoutInflater.from(context).inflate(R.layout.month_drug_list, parent, false);
////        }
////
////        /**
////         * This method binds the pet data (in the current row pointed to by cursor) to the given
////         * list item layout. For example, the name for the current pet can be set on the name TextView
////         * in the list item layout.
////         *
////         * @param view    Existing view, returned earlier by newView() method
////         * @param context app context
////         * @param cursor  The cursor from which to get the data. The cursor is already moved to the
////         *                correct row.
////         */
////        @Override
////        public void bindView(View view, Context context, Cursor cursor) {
////            // Find individual views that we want to modify in the list item layout
////            TextView timeTextView = (TextView) view.findViewById(R.id.time_of_the_day);
////            TextView nameOfDrugTextView = (TextView) view.findViewById(R.id.name_of_drug);
////
////            // Find the columns of pet attributes that we're interested in
////            int medNameColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MEDICATION_NAME);
////            int descriptionColumnIndex = cursor.getColumnIndex(MedManagerContract.MedManagerEntry.COLUMN_MED_DESCRIPTION);
////
////            // Read the pet attributes from the Cursor for the current pet
////            String medNameName = cursor.getString(medNameColumnIndex);
////            String medDescription = cursor.getString(descriptionColumnIndex);
////
////            // If the pet breed is
////            // empty string or null, then use some default text
////            // that says "Unknown breed", so the TextView isn't blank.
////            if (TextUtils.isEmpty(medDescription)) {
////                medDescription = context.getString(R.string.medication_name);
////            }
////
////            // Update the TextViews with the attributes for the current pet
////            timeTextView.setText(medNameName);
////            nameOfDrugTextView.setText(medDescription);
////        }
//
//    @Override
//    public DosageAndTimeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dosage_time_list, parent, false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(DosageAndTimeAdapter.ViewHolder holder, int position) {
//        final Context context =holder.mView.getContext();
//        final String dosage= mDosageTime.get(position).getDosage();
//        final String time= mDosageTime.get(position).getTime();
//        holder.timeTextView.setText(time);
//        holder.nameOfDrugTextView.setText(dosage);
////        holder.mView.setOnClickListener(new View.OnClickListener(){
////
////            @Override
////            public void onClick(View view) {
////
////
////                Intent sendIntent =new Intent(context,SendActivity.class);
////                sendIntent.putExtra("Username",dosage );
////                sendIntent.putExtra("UserId",time );
////                context.startActivity(sendIntent);
////
////            }
////        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDosageTime.size();
//    }
//
//
//    public class ViewHolder extends  RecyclerView.ViewHolder{
//        View mView;
//       public TextView timeTextView;
//       public  TextView nameOfDrugTextView;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            mView=itemView;
//
//            timeTextView=(TextView)mView.findViewById(R.id.time_of_the_day);
//            nameOfDrugTextView = (TextView) mView.findViewById(R.id.number_of_drugs);
//
//
//
//
//        }
//    }
//}
//
//
