package com.example.zulup.authenticationdemo.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zulup.authenticationdemo.R;

public class login_activity extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        mViewPager = findViewById(R.id.tw_view_pagerr);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);

    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if (i == 0) {
                return new FirebaseAuthFragement();
            }else
                return new CustomSocialFragement();


        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0)
                return "Firebase Social auth";
            else
                return "Simple Social auth";

        }
    }
}
