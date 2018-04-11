package com.example.olijefavour.med_manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.olijefavour.med_manager.MonthsFragment.AprilFragment;
import com.example.olijefavour.med_manager.MonthsFragment.AugustFragment;
import com.example.olijefavour.med_manager.MonthsFragment.DecemberFragment;
import com.example.olijefavour.med_manager.MonthsFragment.FebuaryFragment;
import com.example.olijefavour.med_manager.MonthsFragment.JanuaryFragment;
import com.example.olijefavour.med_manager.MonthsFragment.JulyFragment;
import com.example.olijefavour.med_manager.MonthsFragment.JuneFragment;
import com.example.olijefavour.med_manager.MonthsFragment.MarchFragment;
import com.example.olijefavour.med_manager.MonthsFragment.MayFragment;
import com.example.olijefavour.med_manager.MonthsFragment.NovemberFragment;
import com.example.olijefavour.med_manager.MonthsFragment.OctoberFragment;
import com.example.olijefavour.med_manager.MonthsFragment.SeptemberFragment;

/**
 * Created by Olije favour on 4/6/2018.
 */

public class PagerViewAdapter extends FragmentPagerAdapter {

    public PagerViewAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                JanuaryFragment januaryFragment= new JanuaryFragment();
                return januaryFragment;
            case 1:
                FebuaryFragment febuaryFragment= new FebuaryFragment();
                return febuaryFragment;
            case 2:
                MarchFragment marchFragment= new MarchFragment();
                return marchFragment;
            case 3:
                AprilFragment aprilFragment= new AprilFragment();
                return aprilFragment;
            case 4:
               MayFragment mayFragment= new MayFragment();
                return mayFragment;
            case 5:
                JuneFragment juneFragment= new JuneFragment();
                return juneFragment;
            case 6:
                JulyFragment julyFragment= new JulyFragment();
                return julyFragment;
            case 7:
                AugustFragment augustFragment= new AugustFragment();
                return augustFragment;
            case 8:
                SeptemberFragment septemberFragment = new SeptemberFragment();
                return septemberFragment;
            case 9:
                OctoberFragment octoberFragment= new OctoberFragment();
                return octoberFragment;
            case 10:
                NovemberFragment novemberFragment= new NovemberFragment();
                return novemberFragment;
            case 11:
                DecemberFragment decemberFragment= new DecemberFragment();
                return  decemberFragment;

            default:
                return null;

        }


    }

    @Override
    public int getCount() {
        return 12;
    }
}
