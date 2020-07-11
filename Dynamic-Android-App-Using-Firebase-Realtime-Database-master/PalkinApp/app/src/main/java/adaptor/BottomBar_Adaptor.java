package adaptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import Fragments.BuyProductFragment;
import Fragments.DevelopersFragment;

public class BottomBar_Adaptor extends FragmentStatePagerAdapter {
    int mnooftabs;

    public BottomBar_Adaptor(FragmentManager fm, int mnooftabs) {
        super(fm);
        this.mnooftabs = mnooftabs;
    }
    public BottomBar_Adaptor(FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                System.out.println("Home Fragment Calling");
                //HomeFragment tab1 = new HomeFragment();
                return null;
            case 1:
                BuyProductFragment tab2 = new BuyProductFragment();
                return tab2;
            case 2:
                DevelopersFragment tab3 = new DevelopersFragment();
                return tab3;

            default:
                return null;
        }
    }
    @Override
    public int getCount () {
        return 3;
    }
}

