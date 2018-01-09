package com.example.zulqarnain.features.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.zulqarnain.R;
import com.example.zulqarnain.features.demo.custom.holder.CustomHolderDialogsActivity;
import com.example.zulqarnain.features.demo.custom.layout.CustomLayoutDialogsActivity;
import com.example.zulqarnain.features.demo.custom.media.CustomMediaMessagesActivity;
import com.example.zulqarnain.features.demo.def.DefaultDialogsActivity;
import com.example.zulqarnain.features.demo.styled.StyledDialogsActivity;
import com.example.zulqarnain.features.main.adapter.DemoCardFragment;
import com.example.zulqarnain.features.main.adapter.MainActivityPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

/*
 * Created by troy379 on 04.04.17.
 */
public class MainActivity extends AppCompatActivity
        implements DemoCardFragment.OnActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MainActivityPagerAdapter(this, getSupportFragmentManager()));
        pager.setPageMargin((int) getResources().getDimension(R.dimen.card_padding) / 4);
        pager.setOffscreenPageLimit(3);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);

    }

    @Override
    public void onAction(int id) {
        switch (id) {
            case MainActivityPagerAdapter.ID_DEFAULT:
                DefaultDialogsActivity.open(this);
                break;
            case MainActivityPagerAdapter.ID_STYLED:
                StyledDialogsActivity.open(this);
                break;
            case MainActivityPagerAdapter.ID_CUSTOM_LAYOUT:
                CustomLayoutDialogsActivity.open(this);
                break;
            case MainActivityPagerAdapter.ID_CUSTOM_VIEW_HOLDER:
                CustomHolderDialogsActivity.open(this);
                break;
            case MainActivityPagerAdapter.ID_CUSTOM_CONTENT:
                CustomMediaMessagesActivity.open(this);
                break;
        }
    }
}
