package com.example.olijefavour.med_manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.olijefavour.med_manager.MonthsFragment.AprilFragment;
import com.example.olijefavour.med_manager.MonthsFragment.FebuaryFragment;
import com.example.olijefavour.med_manager.MonthsFragment.MarchFragment;
import com.example.olijefavour.med_manager.TimeFragment.AfternoonFragment;
import com.example.olijefavour.med_manager.TimeFragment.EveningFragment;
import com.example.olijefavour.med_manager.TimeFragment.MorningFragment;

/**
 * Created by Olije favour on 4/10/2018.
 */

public class TimeOfTheDayFragmentAdapter extends FragmentPagerAdapter {

    public TimeOfTheDayFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                MorningFragment morningFragment= new MorningFragment();
                return morningFragment;
            case 1:
                AfternoonFragment afternoonFragment= new AfternoonFragment();
                return afternoonFragment;
            case 2:
                EveningFragment eveningFragment= new EveningFragment();
                return eveningFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
