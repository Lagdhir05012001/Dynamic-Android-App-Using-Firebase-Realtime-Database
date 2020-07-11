package com.example.retrive_image_tablayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
//import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import Fragments.BuyProductFragment;
import Fragments.DevelopersFragment;
import Fragments.HomeFragment;
import adaptor.ViewPagerAdaptor;

public class MainActivity extends AppCompatActivity  {

    TabLayout tabLayout1;
    ViewPager viewPager;
    //TablayoutAdaptor_Home adapter;
    Fragment fragment=null;
    FragmentTransaction fragmentTransaction;
    final FragmentManager fm = getSupportFragmentManager();

    final Fragment buyProductFragment = new BuyProductFragment();
    final Fragment devloperFragment = new DevelopersFragment();
    //final Fragment mUserMeFragment = new UserMeFragment();
    Fragment active = new HomeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        //choli = (TextView)findViewById(R.id.tab_choli);
        //blazar = (TextView)findViewById(R.id.tab_blazar);
        //final FragmentManager fm = new getSu

        viewPager = (ViewPager) findViewById(R.id.viewpager_temp);
        tabLayout1=(TabLayout) findViewById(R.id.tablayout_temp);
        viewPager.setOffscreenPageLimit(2);

       bottomNav.setOnNavigationItemSelectedListener(navListener);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.viewpager_temp,
                    new HomeFragment()).commit();
        }
        fm.beginTransaction().add(R.id.viewpager_temp,buyProductFragment,"nav_profile").hide(active).commit();
        fm.beginTransaction().add(R.id.viewpager_temp,devloperFragment,"nav_Tag").hide(active).commit();


        //viewPager.setOffscreenPageLimit(10);
        ArrayList<String> categories = new ArrayList<>();

        categories = getIntent().getStringArrayListExtra("myArrayList");

        tabLayout1.setTabGravity(TabLayout.GRAVITY_FILL);
        String[] items = new String[categories.size()];

        for(int i=0;i<categories.size();i++){
            items[i] = categories.get(i).toString();
            tabLayout1.addTab(tabLayout1.newTab().setText(items[i]));
        }

        ViewPagerAdaptor adapter = new ViewPagerAdaptor(getSupportFragmentManager(), tabLayout1.getTabCount(),items);

        viewPager.setAdapter(adapter);

        //BottomBar_Adaptor ad = new BottomBar_Adaptor(getSupportFragmentManager(),bottomNav.getMaxItemCount());
        //viewPager.setAdapter(ad);


        // adapter  =new TablayoutAdaptor_Home(getSupportFragmentManager());

        //viewPager.setAdapter(adapter);
        wrapTabIndicatorToTitle(tabLayout1, 50, 50);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout1));
        for (int i = 0; i < tabLayout1.getTabCount(); i++) {

            TabLayout.Tab tab = tabLayout1.getTabAt(i);
            if (tab != null) {

                TextView tabTextView = new TextView(this);
                tab.setCustomView(tabTextView);

                tabTextView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                tabTextView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

                tabTextView.setText(tab.getText());
                tabTextView.setTextColor(Color.parseColor("#acacac"));

                // First tab is the selected tab, so if i==0 then set BOLD typeface
                if (i == 0) {
                    tabTextView.setTypeface(null, Typeface.BOLD);
                    tabTextView.setTextColor(Color.parseColor("#000000"));
                }
            }
        }



        tabLayout1.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                TextView text = (TextView) tab.getCustomView();

                assert text != null;
                text.setTextColor(Color.parseColor("#000000"));
                text.setTypeface(null, Typeface.BOLD);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView text = (TextView) tab.getCustomView();
                text.setTextColor(Color.parseColor("#acacac"));
                text.setTypeface(null, Typeface.NORMAL);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void wrapTabIndicatorToTitle(TabLayout tabLayout, int externalMargin, int internalMargin) {
        View tabStrip = tabLayout.getChildAt(0);
        if (tabStrip instanceof ViewGroup) {
            ViewGroup tabStripGroup = (ViewGroup) tabStrip;
            int childCount = ((ViewGroup) tabStrip).getChildCount();
            for (int i = 0; i < childCount; i++) {
                View tabView = tabStripGroup.getChildAt(i);
                //set minimum width to 0 for instead for small texts, indicator is not wrapped as expected
                tabView.setMinimumWidth(0);
                // set padding to 0 for wrapping indicator as title
                tabView.setPadding(0, tabView.getPaddingTop(), 0, tabView.getPaddingBottom());
                // setting custom margin between tabs
                if (tabView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
                    if (i == 0) {
                        // left
                        settingMargin(layoutParams, externalMargin, internalMargin);
                    } else if (i == childCount - 1) {
                        // right
                        settingMargin(layoutParams, internalMargin, externalMargin);
                    } else {
                        // internal
                        settingMargin(layoutParams, internalMargin, internalMargin);
                    }
                }
            }

            tabLayout.requestLayout();
        }
    }

    private void settingMargin(ViewGroup.MarginLayoutParams layoutParams, int start, int end) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.setMarginStart(start);
            layoutParams.setMarginEnd(end);
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        } else {
            layoutParams.leftMargin = start;
            layoutParams.rightMargin = end;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            return true;
                        case R.id.nav_tag:
                            startActivity(new Intent(MainActivity.this,Devloper_Activity.class));

                            return true;
                        case R.id.nav_profile:
                            startActivity(new Intent(MainActivity.this,Product_activity.class));
                            return true;
                    }
                    return false;
                }
            };
}