//package com.example.olijefavour.med_manager;
//
//import android.graphics.Color;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.TextView;
//
//public class Timeinterval extends AppCompatActivity {
//
//    private ViewPager mViewPager;
//    private TimeOfTheDayFragmentAdapter timeOfTheDayViewrAdapter;
//    private TextView mMorninng;
//    private TextView mAfternoon;
//    private TextView mEvening;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_time_interval);
//
//
//        mMorninng = (TextView) findViewById(R.id.morning);
//        mAfternoon = (TextView) findViewById(R.id.afternoon);
//        mEvening = (TextView) findViewById(R.id.evening);
//
//        mViewPager = (ViewPager) findViewById(R.id.time_interval_viewpager);
//        timeOfTheDayViewrAdapter = new TimeOfTheDayFragmentAdapter(getSupportFragmentManager());
//        mViewPager.setAdapter(timeOfTheDayViewrAdapter);
//
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                changeTab(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//
//            }
//        });
//    }
//
//    public void changeTab(int position) {
//
//
//            switch(position){
//                case 0:
//                   mMorninng.setTextColor(Color.parseColor("#303F9F"));
//                    mMorninng.setTextSize(22);
//                    mAfternoon.setTextColor(Color.parseColor("#3F51B5"));
//                    mAfternoon.setTextSize(16);
//                    mEvening.setTextColor(Color.parseColor("#3F51B5"));
//                    mEvening.setTextSize(16);
//                    break;
//                case 1:
//                    mAfternoon.setTextColor(Color.parseColor("#303F9F"));
//                    mAfternoon.setTextSize(22);
//                    mMorninng.setTextColor(Color.parseColor("#3F51B5"));
//                    mMorninng.setTextSize(16);
//                    mEvening.setTextColor(Color.parseColor("#3F51B5"));
//                    mEvening.setTextSize(16);
//                    break;
//                case 2:
//                    mEvening.setTextColor(Color.parseColor("#303F9F"));
//                    mEvening.setTextSize(22);
//                    mAfternoon.setTextColor(Color.parseColor("#3F51B5"));
//                    mAfternoon.setTextSize(16);
//                    mMorninng.setTextColor(Color.parseColor("#3F51B5"));
//                    mMorninng.setTextSize(16);
//                    break;
//                default: mMorninng.setTextColor(Color.parseColor("#303F9F"));
//                    mMorninng.setTextSize(22);
//                    mAfternoon.setTextColor(Color.parseColor("#3F51B5"));
//                    mAfternoon.setTextSize(16);
//                    mEvening.setTextColor(Color.parseColor("#3F51B5"));
//                    mEvening.setTextSize(16);
//
//            }
//        }
//    }
//
