package com.example.a10117265_abdurrahmansidiq;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class SlidePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;

    public SlidePagerAdapter(FragmentManager fm, List<Fragment> fragmentList){
    super(fm);
    this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
//08-05-2020,10117265,AbdurrahmanSidiq,IF-8