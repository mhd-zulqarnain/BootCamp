package fahee.example.com.inovisionhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fahee.example.com.inovisionhome.R;
import fahee.example.com.inovisionhome.calenderstuff.CustomCalenderActvity;

/**
 * Created by Zul Qarnain on 2/9/2018.
 */

public class ServiceAdapter  extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    private Context ctx;
    private ArrayList<String> dList;
    public ServiceAdapter(Context ctx, ArrayList<String> dList) {
        this.ctx=ctx;
        this.dList=dList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_service_row,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    holder.tv.setText(dList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return dList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv= itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ctx.startActivity(new Intent(ctx, CustomCalenderActvity.class));
                }
            });
        }
    }
}
