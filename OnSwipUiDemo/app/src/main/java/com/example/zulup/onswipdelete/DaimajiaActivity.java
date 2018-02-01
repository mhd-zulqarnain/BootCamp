package com.example.zulup.onswipdelete;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.zulup.onswipdelete.adapter.StudentAdapter;
import com.example.zulup.onswipdelete.model.Student;

import java.util.ArrayList;

public class DaimajiaActivity extends AppCompatActivity implements SwipListener {

    private ArrayList<Student> stdList;
    private RecyclerView mRecyclerView;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_custom_swip);
        mRecyclerView = findViewById(R.id.recycler_view);
        stdList = new ArrayList<>();
        updateUi();
    }

    private void updateUi() {
        stdList.add(new Student(1, "test1"));
        stdList.add(new Student(2, "test2"));
        stdList.add(new Student(3, "test3"));
        stdList.add(new Student(4, "test4"));
        stdList.add(new Student(5, "test5"));

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));

        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentAdapter(stdList, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void removeItem(int id) {
        int index = getIdToRemove(id);
        Log.d("", "removeItem: " + id + " index " + index);
        if (index != -1) {
            if (stdList.size() == index) {
                index = index - 1;
            } else {
                stdList.remove(index);
                adapter.notifyItemRemoved(index);
            }
        }
    }

    private int getIdToRemove(int id) {
        int uid = -1;
        for (int i = 0; i < stdList.size(); i++) {
            if (stdList.get(i).getId() == id) {
                uid = i;
                break;
            }
        }
        return uid;
    }


}
