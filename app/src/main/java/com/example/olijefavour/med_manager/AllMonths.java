package com.example.olijefavour.med_manager;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class AllMonths extends AppCompatActivity {

    private TextView mJanuary;
    private TextView mFebuary;
    private TextView mMarch;
    private TextView mApril;
    private TextView mMay;
    private TextView mJune;
    private TextView mJuly;
    private TextView mAugust;
    private TextView mSeptember;
    private TextView mOctober;
    private TextView mNovember;
    private TextView mDecember;

    private ViewPager mViewPager;

    private Calendar calender;
    private  PagerViewAdapter mPagerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_month);

        mJanuary = (TextView) findViewById(R.id.january);
        mFebuary = (TextView) findViewById(R.id.febrary);
        mMarch = (TextView) findViewById(R.id.march);
        mApril = (TextView) findViewById(R.id.april);
        mMay = (TextView) findViewById(R.id.may);
        mJune = (TextView) findViewById(R.id.june);
        mJuly = (TextView) findViewById(R.id.july);
        mAugust = (TextView) findViewById(R.id.august);
        mSeptember = (TextView) findViewById(R.id.september);
        mOctober = (TextView) findViewById(R.id.october);
        mNovember = (TextView) findViewById(R.id.november);
        mDecember = (TextView) findViewById(R.id.december);

        calender=Calendar.getInstance();


//        int days[]={1,2,3,4,5,6,7,8,9,10,11};
//
//        GridView gridView = (GridView)findViewById(R.id.gridview);
//        MonthsssAdapter booksAdapter = new MonthsssAdapter(this,days );
//        gridView.setAdapter(booksAdapter);


        mViewPager = (ViewPager) findViewById(R.id.mainviewpager);
        mPagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerViewAdapter);



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTab( position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                
            }
        });
    }
         public void  changeTab(int position){

        switch(position){
            case 0:
                 mJanuary.setTextColor(Color.parseColor("#303F9F"));
                 mJanuary.setTextSize(22);
                 mJanuary.setVisibility(View.VISIBLE);
                 mFebuary.setVisibility(View.GONE);
//                 mFebuary.setTextColor(Color.parseColor("#3F51B5"));
//                 mMarch.setTextSize(16);
                 mMarch.setVisibility(View.GONE);
//                 mApril.setTextSize(16);
                 mApril.setVisibility(View.GONE);
//                 mMay.setTextSize(16);
                 mMay.setVisibility(View.GONE);
//                 mJune.setTextSize(16);
                 mJune.setVisibility(View.GONE);
//                 mJuly.setTextSize(16);
                 mJuly.setVisibility(View.GONE);
//                 mAugust.setTextSize(16);
                 mAugust.setVisibility(View.GONE);
//                 mSeptember.setTextSize(16);
                 mSeptember.setVisibility(View.GONE);
//                 mOctober.setTextSize(16);
                 mOctober.setVisibility(View.GONE);
//                 mNovember.setTextSize(16);
                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
                break;
            case 1:
                mJanuary.setVisibility(View.GONE);
//                mJanuary.setTextSize(16);
                mFebuary.setTextSize(22);
                mFebuary.setTextColor(Color.parseColor("#303F9F"));
                mFebuary.setVisibility(View.VISIBLE);
//                 mMarch.setTextSize(16);
                mMarch.setVisibility(View.GONE);
//                 mApril.setTextSize(16);
                mApril.setVisibility(View.GONE);
//                 mMay.setTextSize(16);
                mMay.setVisibility(View.GONE);
//                 mJune.setTextSize(16);
                mJune.setVisibility(View.GONE);
//                 mJuly.setTextSize(16);
                mJuly.setVisibility(View.GONE);
//                 mAugust.setTextSize(16);
                mAugust.setVisibility(View.GONE);
//                 mSeptember.setTextSize(16);
                mSeptember.setVisibility(View.GONE);
//                 mOctober.setTextSize(16);
                mOctober.setVisibility(View.GONE);
//                 mNovember.setTextSize(16);
                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
                break;
            case 2:
               mJanuary.setVisibility(View.GONE);
//                mJanuary.setTextSize(16);
                mFebuary.setTextSize(22);
                mFebuary.setVisibility(View.GONE);
                mMarch.setTextSize(22);
                mMarch.setTextColor(Color.parseColor("#303F9F"));
                mMarch.setVisibility(View.VISIBLE);

//                 mApril.setTextSize(16);
                mApril.setVisibility(View.GONE);
//                 mMay.setTextSize(16);
                mMay.setVisibility(View.GONE);
//                 mJune.setTextSize(16);
                mJune.setVisibility(View.GONE);
//                 mJuly.setTextSize(16);
                mJuly.setVisibility(View.GONE);
//                 mAugust.setTextSize(16);
                mAugust.setVisibility(View.GONE);
//                 mSeptember.setTextSize(16);
                mSeptember.setVisibility(View.GONE);
//                 mOctober.setTextSize(16);
                mOctober.setVisibility(View.GONE);
//                 mNovember.setTextSize(16);
                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
                  break;
            case 3:
                mJanuary.setVisibility(View.GONE);
//                mJanuary.setTextSize(16);
                mFebuary.setVisibility(View.GONE);
                mMarch.setVisibility(View.GONE);
                mApril.setTextSize(22);
                mApril.setTextColor(Color.parseColor("#303F9F"));
                mApril.setVisibility(View.VISIBLE);
//                 mMay.setTextSize(16);
                mMay.setVisibility(View.GONE);
//                 mJune.setTextSize(16);
                mJune.setVisibility(View.GONE);
//                 mJuly.setTextSize(16);
                mJuly.setVisibility(View.GONE);
//                 mAugust.setTextSize(16);
                mAugust.setVisibility(View.GONE);
//                 mSeptember.setTextSize(16);
                mSeptember.setVisibility(View.GONE);
//                 mOctober.setTextSize(16);
                mOctober.setVisibility(View.GONE);
//                 mNovember.setTextSize(16);
                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
                break;
            case 4:
                mJanuary.setVisibility(View.GONE);
                mJanuary.setTextSize(22);
                mFebuary.setTextSize(22);
                mFebuary.setVisibility(View.GONE);
                mMarch.setVisibility(View.GONE);
                mApril.setVisibility(View.GONE);
                mMay.setTextSize(22);
                mMay.setTextColor(Color.parseColor("#303F9F"));
                mMay.setVisibility(View.VISIBLE);
//                 mJune.setTextSize(16);
                mJune.setVisibility(View.GONE);
//                 mJuly.setTextSize(16);
                mJuly.setVisibility(View.GONE);
//                 mAugust.setTextSize(16);
                mAugust.setVisibility(View.GONE);
//                 mSeptember.setTextSize(16);
                mSeptember.setVisibility(View.GONE);
//                 mOctober.setTextSize(16);
                mOctober.setVisibility(View.GONE);
//                 mNovember.setTextSize(16);
                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
                break;
            case 5:
                mJanuary.setVisibility(View.GONE);
                mJanuary.setTextSize(16);
                mFebuary.setVisibility(View.GONE);
                mMarch.setVisibility(View.GONE);
                mApril.setVisibility(View.GONE);
                mMay.setVisibility(View.GONE);
            mJune.setTextSize(22);
            mJune.setTextColor(Color.parseColor("#303F9F"));
                mJune.setVisibility(View.VISIBLE);
                mJuly.setVisibility(View.GONE);
//                 mAugust.setTextSize(16);
                mAugust.setVisibility(View.GONE);
//                 mSeptember.setTextSize(16);
                mSeptember.setVisibility(View.GONE);
//                 mOctober.setTextSize(16);
                mOctober.setVisibility(View.GONE);
//                 mNovember.setTextSize(16);
                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
            break;
            case 6:
                mJanuary.setVisibility(View.GONE);
                mJanuary.setTextSize(22);
                mFebuary.setTextSize(22);
                mFebuary.setVisibility(View.GONE);
                mMarch.setTextSize(22);

                mMarch.setVisibility(View.GONE);
                mApril.setTextSize(22);
                mApril.setVisibility(View.GONE);
                mMay.setTextSize(22);
                mMay.setVisibility(View.GONE);
                mJune.setTextSize(22);
                mJune.setVisibility(View.GONE);
                mJuly.setTextSize(22);
                mJuly.setTextColor(Color.parseColor("#303F9F"));
                mJuly.setVisibility(View.VISIBLE);

                //mAugust.setTextSize(16);
                mAugust.setVisibility(View.GONE);
//                 mSeptember.setTextSize(16);
                mSeptember.setVisibility(View.GONE);
//                 mOctober.setTextSize(16);
                mOctober.setVisibility(View.GONE);
//                 mNovember.setTextSize(16);
                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
                break;
            case 7:
                mJanuary.setVisibility(View.GONE);
                mJanuary.setTextSize(22);
                mFebuary.setTextSize(22);
                mFebuary.setVisibility(View.GONE);
                mMarch.setTextSize(22);
                mMarch.setVisibility(View.GONE);
                mApril.setTextSize(22);
                mApril.setVisibility(View.GONE);
                mMay.setTextSize(22);
                mMay.setVisibility(View.GONE);
                mJune.setTextSize(22);
                mJune.setVisibility(View.GONE);
                mJuly.setTextSize(22);
                mJuly.setVisibility(View.GONE);
                mAugust.setTextSize(22);
                mAugust.setTextColor(Color.parseColor("#303F9F"));
                mAugust.setVisibility(View.VISIBLE);

                mSeptember.setVisibility(View.GONE);
//                 mOctober.setTextSize(16);
                mOctober.setVisibility(View.GONE);
//                 mNovember.setTextSize(16);
                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
                break;
            case 8:
                mJanuary.setVisibility(View.GONE);
                mJanuary.setTextSize(22);
                mFebuary.setTextSize(22);
                mFebuary.setVisibility(View.GONE);
                mMarch.setTextSize(22);
                mMarch.setVisibility(View.GONE);
                mApril.setTextSize(22);
                mApril.setVisibility(View.GONE);
                mMay.setTextSize(22);
                mMay.setVisibility(View.GONE);
                mJune.setTextSize(22);
                mJune.setVisibility(View.GONE);
                mJuly.setTextSize(22);
                mJuly.setVisibility(View.GONE);
                mAugust.setTextSize(22);
                mAugust.setVisibility(View.GONE);
                mSeptember.setTextSize(22);
                mSeptember.setTextColor(Color.parseColor("#303F9F"));
                mSeptember.setVisibility(View.VISIBLE);
//                mOctober.setTextSize(16);
                mOctober.setVisibility(View.GONE);
//                 mNovember.setTextSize(16);
                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
                break;
            case 9:
                mJanuary.setVisibility(View.GONE);
                mJanuary.setTextSize(22);
                mFebuary.setTextSize(22);
                mFebuary.setVisibility(View.GONE);
                mMarch.setTextSize(22);
                mMarch.setVisibility(View.GONE);
                mApril.setTextSize(22);
                mApril.setVisibility(View.GONE);
                mMay.setTextSize(22);
                mMay.setVisibility(View.GONE);
                mJune.setTextSize(22);
                mJune.setVisibility(View.GONE);
                mJuly.setTextSize(22);
                mJuly.setVisibility(View.GONE);
                mAugust.setTextSize(22);
                mAugust.setVisibility(View.GONE);
                mSeptember.setTextSize(22);
                mSeptember.setVisibility(View.GONE);
                mOctober.setTextSize(22);
                mOctober.setTextColor(Color.parseColor("#303F9F"));
                mOctober.setVisibility(View.VISIBLE);
//                 mNovember.setTextSize(16);
                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
                break;
            case 10:
                mJanuary.setVisibility(View.GONE);
                mJanuary.setTextSize(22);
                mFebuary.setTextSize(22);
                mFebuary.setVisibility(View.GONE);
                mMarch.setTextSize(22);
                mMarch.setVisibility(View.GONE);
                mApril.setTextSize(22);
                mApril.setVisibility(View.GONE);
                mMay.setTextSize(22);
                mMay.setVisibility(View.GONE);
                mJune.setTextSize(22);
                mJune.setVisibility(View.GONE);
                mJuly.setTextSize(22);
                mJuly.setVisibility(View.GONE);
                mAugust.setTextSize(22);
                mAugust.setVisibility(View.GONE);
                mSeptember.setTextSize(22);
                mSeptember.setVisibility(View.GONE);
                mOctober.setVisibility(View.GONE);
                mNovember.setTextSize(22);
                mNovember.setTextColor(Color.parseColor("#303F9F"));
                mNovember.setVisibility(View.VISIBLE);
//                mDecember.setTextSize(16);
                mDecember.setVisibility(View.GONE);
                break;
            case 11:
//                     mJanuary.setVisibility(View.GONE);
                mJanuary.setTextSize(22);
                mFebuary.setTextSize(22);
                mFebuary.setVisibility(View.GONE);
                mMarch.setTextSize(22);
                mMarch.setTextColor(Color.parseColor("#303F9F"));
                mMarch.setVisibility(View.GONE);
                mApril.setTextSize(22);
                mApril.setTextColor(Color.parseColor("#303F9F"));
                mApril.setVisibility(View.GONE);
                mMay.setTextSize(22);
                mMay.setVisibility(View.GONE);
                mJune.setTextSize(22);
                mJune.setVisibility(View.GONE);
                mJuly.setTextSize(22);
                mJuly.setVisibility(View.GONE);
                mAugust.setTextSize(22);
                mAugust.setVisibility(View.GONE);
                mSeptember.setTextSize(22);
                mSeptember.setVisibility(View.GONE);
//                mOctober.setTextSize(16);
                mSeptember.setVisibility(View.GONE);
                mNovember.setTextSize(22);
                mNovember.setVisibility(View.GONE);
//                mNovember.setTextColor(Color.parseColor("#3F51B5"));
                mDecember.setTextSize(22);
                mDecember.setTextColor(Color.parseColor("#303F9F"));
                mDecember.setVisibility(View.VISIBLE);
                break;

//            default:
//                mJanuary.setVisibility(View.VISIBLE);
//                mJanuary.setTextSize(16);
//                mFebuary.setTextSize(16);
//                mFebuary.setVisibility(View.GONE);
//                mMarch.setTextSize(16);
//                mMarch.setVisibility(View.GONE);
//                mApril.setTextSize(16);
//                mApril.setVisibility(View.GONE);
//                mMay.setTextSize(16);
//                mMay.setVisibility(View.GONE);
//                mJune.setTextSize(16);
//                mJune.setVisibility(View.GONE);
//                mJuly.setTextSize(16);
//                mJuly.setVisibility(View.GONE);
//                mAugust.setTextSize(16);
//                mAugust.setVisibility(View.GONE);
//                mSeptember.setTextSize(16);
//                mSeptember.setVisibility(View.GONE);
//                mOctober.setTextSize(16);
//                mOctober.setVisibility(View.GONE);
//                mNovember.setTextSize(16);
//                mNovember.setVisibility(View.GONE);
//                mDecember.setTextSize(16);
//                mDecember.setVisibility(View.GONE);



        }

    }
}
