package adaptor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Fragments.HomeFragment;

public class ViewPagerAdaptor extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    int mNumOfTabs;
    Fragment fragment = null;
    String[] categories;
    public ViewPagerAdaptor(FragmentManager manager, int mNumOfTabs, String[] items){
        super(manager);
        this.mNumOfTabs = mNumOfTabs;
        categories = items;
    }

    @Override
    public Fragment getItem(int position) {
        for (int i = 0; i < mNumOfTabs ; i++) {
            if (i == position) {
                 fragment = new HomeFragment();
                Bundle args = new Bundle();
                args.putInt("index", position);
                args.putString("category",categories[position]);
                //args.putStringArray("category",categories);
                //String x = getItem(position).toString();
                //args.putString("selectedCategory",x);

                fragment.setArguments(args);
                //fragment.setArguments;
                //System.out.println("Arguments"+fragment.getArguments().getInt("index"));


                return fragment;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        //return mFragmentTitleList.get(position);
        return super.getPageTitle(position);

    }
}
