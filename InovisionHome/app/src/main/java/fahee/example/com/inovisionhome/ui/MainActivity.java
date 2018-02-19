package fahee.example.com.inovisionhome.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fahee.example.com.inovisionhome.R;
import fahee.example.com.inovisionhome.util.Messege;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private int layout[];
    private TextView dots[];
    private LinearLayout dotView;
    private Button btnSkip, btnNext;
     ImageView slideImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Messege.message(getApplicationContext(),"launched");
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);

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
          /*  if(position==0){
                slideImg = view.findViewById(R.id.slide_img_1);
                slideImg.setImageBitmap(
                        decodeSampledBitmapFromResource(getResources(), R.drawable.slide1, 100, 100));
            }
            if(position==1){
                slideImg = view.findViewById(R.id.slide_img_2);
                slideImg.setImageBitmap(
                        decodeSampledBitmapFromResource(getResources(), R.drawable.slide2, 100, 100));
            }if(position==2){
                slideImg = view.findViewById(R.id.slide_img_3);
                slideImg.setImageBitmap(
                        decodeSampledBitmapFromResource(getResources(), R.drawable.slide_3, 100, 100));
            }*/
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
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // Decode bitmap with inSampleSize set
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
