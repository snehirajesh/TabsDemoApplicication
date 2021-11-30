package com.test.tabsdemoapplicication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Priyabrat on 21-08-2015.
 */
public class ViewPagerAdapter extends FragmentStateAdapter {

    private List<FragmentA> fragmentAList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        createListFragment();
    }

    private void createListFragment() {
        fragmentAList.clear();
        fragmentAList.add(new FragmentA());
        fragmentAList.add(new FragmentA());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentAList.get(position);
    }

    public List<FragmentA> getFragmentAList() {
        return fragmentAList;
    }

    @Override
    public int getItemCount() {
        return fragmentAList.size();
    }
}
