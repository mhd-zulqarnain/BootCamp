package com.example.faizrehman.inovision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    TextView currentMonth;

    ArrayList<Mood> moods;
    DisplayMetrics metrics;

    Button btnNext,btnBefore;
    int month;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        ButterKnife.bind(this);
        recyclerView=findViewById(R.id.gridview);
        currentMonth=findViewById(R.id.currentMonth);
        btnBefore=findViewById(R.id.btnBefore);
        btnNext=findViewById(R.id.btn_next);
        setCalendar();
        setUi();
    }

    private void setUi() {
     btnNext.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             month = month + 1;

             if (month > 11){
                 month = 0;
                 year = year + 1;
             }

             currentMonth.setText(Util.getMonth(month + 1) + " / " + String.valueOf(year));
             recyclerView.setAdapter(new MonthAdapter(this, month, year, metrics, this.moods));
         }
     });
     btnBefore.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

         }
     });
    }

    public void setCalendar(){
        Calendar mCalendar = Calendar.getInstance();

        month = mCalendar.get(Calendar.MONTH); // zero based
        year = mCalendar.get(Calendar.YEAR);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        recyclerView.setHasFixedSize(false);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));

        currentMonth.setText(Util.getMonth(month + 1) + " / " + String.valueOf(year));


        populateMoods(mCalendar);
    }

    public void populateMoods(Calendar mCalendar){
        this.moods = new ArrayList<>();

        Mood mood = new Mood();
        mood.day = mCalendar.get(Calendar.DAY_OF_MONTH);
        mood.month = mCalendar.get(Calendar.MONTH) + 1;
        mood.year = mCalendar.get(Calendar.YEAR);

        this.moods.add(mood);

        recyclerView.setAdapter(new MonthAdapter(this, month, year, metrics, this.moods));

    }

    @OnClick(R.id.btnNext)
    public void onClickNext(){

    }


    public void onClickBefore(View v){
        month = month - 1;

        if (month < 0) {
            month = 11;
            year = year - 1;
        }
        currentMonth.setText(Util.getMonth(month + 1) + " / " + String.valueOf(year));
        recyclerView.setAdapter(new MonthAdapter(this, month, year, metrics, this.moods));
    }

}
