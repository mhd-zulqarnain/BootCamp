package com.example.faizrehman.inovision.ui;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.faizrehman.inovision.R;
import com.example.faizrehman.inovision.util.Messege;

public class UiActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private int layout[];
    private TextView dots[];
    private LinearLayout dotView;
    private Button btnSkip, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        layout = new int[]{R.layout.slider_view_1, R.layout.slider_view_2, R.layout.slider_view_3};
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);

        initView();
    }

    private void initView() {
        dotView = findViewById(R.id.dot_view);
        adddotstoView(0);
        ViewPager.OnPageChangeListener changelistener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                adddotstoView(position);
                if (position == layout.length - 1){
                    btnNext.setText("Start");
                    btnSkip.setVisibility(View.GONE);
                }
                else {
                    btnNext.setText("Next");
                    btnSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager.setAdapter(new WellComePageAdapter());
        viewPager.addOnPageChangeListener(changelistener);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getCurrentPage();
                if(current<layout.length){
                    viewPager.setCurrentItem(current);
                }else
                    lanumchLogin();
            }
        });
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanumchLogin();
            }
        });

    }

    private void lanumchLogin() {
        Messege.messege(getApplicationContext(),"launched");

    }

    private void adddotstoView(int currentPage) {
        dots = new TextView[layout.length];
        int colorsActive = getResources().getColor(R.color.array_dot_active);
        int colorsInactive = getResources().getColor(R.color.array_dot_inactive);
        dotView.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive);
            dotView.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[currentPage].setTextColor(colorsActive);
        }
    }

    public int getCurrentPage() {
        return viewPager.getCurrentItem() + 1;
    }

    class WellComePageAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(layout[position], container, false);
            container.addView(view);
            return view;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public int getCount() {
            return layout.length;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
