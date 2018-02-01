package com.example.zulup.onswipdelete.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.zulup.onswipdelete.R;
import com.example.zulup.onswipdelete.SwipListener;
import com.example.zulup.onswipdelete.model.Student;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Zul Qarnain on 1/31/2018.
 */

public class StudentAdapter extends RecyclerSwipeAdapter<StudentAdapter.SwipViewHolder> {


    private ArrayList<Student> stuList;
    public Context mContext;
    SwipListener listener;
    final android.os.Handler handler = new android.os.Handler();

    public StudentAdapter(ArrayList<Student> stuList, Context mContext) {
        this.stuList = stuList;
        this.mContext = mContext;
        listener = (SwipListener) mContext;
    }


    @Override
    public SwipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_row_item, parent, false);
        return new SwipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SwipViewHolder viewHolder,  int position) {
        Student student = stuList.get(position);
        viewHolder.mstd.setText(student.getName());

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        //left
//        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left,viewHolder.swipeLayout.findViewById(R.id.view_right_left));
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.view_left_right));

        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            Runnable runnable;
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

                runnable = new Runnable() {
                    @Override
                    public void run() {
                        viewHolder.swipeLayout.close();

                    }
                };
                handler.postDelayed(runnable, 2000);


            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {
                handler.removeCallbacks(runnable);
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });



        viewHolder.mdlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.removeItem(stuList.get(viewHolder.getAdapterPosition()).getId());

            }
        });  viewHolder.mundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.swipeLayout.close();

            }
        });

    }


    @Override
    public int getItemCount() {
        return stuList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swip_view;
    }



    public class SwipViewHolder extends RecyclerView.ViewHolder {
        TextView  mstd;
        ImageView mdlt;
        ImageView mundo;
        SwipeLayout swipeLayout;

        public SwipViewHolder(View itemView) {
            super(itemView);
            mdlt = itemView.findViewById(R.id.t_dlt);
            mundo = itemView.findViewById(R.id.t_undo);
            mstd = itemView.findViewById(R.id.std_name);
            swipeLayout = itemView.findViewById(R.id.swip_view);

        }
    }
}
