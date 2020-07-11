package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import Fragments.HomeFragment;

public class TablayoutAdaptor_Home extends FragmentStatePagerAdapter {
    int mnooftabs;

    public TablayoutAdaptor_Home(FragmentManager fm, int mnooftabs) {
        super(fm);
        this.mnooftabs = mnooftabs;
    }
    public TablayoutAdaptor_Home(FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                System.out.println("Home Fragment Calling");
                HomeFragment tab1 = new HomeFragment();
                return tab1;

            case 1:
                BlazarFragment tab2 = new BlazarFragment();
                return tab2;

            case 3:
                SuitFragment tab3 = new SuitFragment();
                return tab3;

            case 4:
                NightDressFragment tab4 = new NightDressFragment();
                return tab4;

            default:
                return null;
        }
    }
    @Override
    public int getCount () {
        return 4;
    }
}

