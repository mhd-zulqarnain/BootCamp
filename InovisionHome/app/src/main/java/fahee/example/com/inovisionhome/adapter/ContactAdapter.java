package fahee.example.com.inovisionhome.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fahee.example.com.inovisionhome.model.Contacts;
import fahee.example.com.inovisionhome.R;
import fahee.example.com.inovisionhome.ui.ContactDialogFragment;
import fahee.example.com.inovisionhome.util.Messege;

/**
 * Created by Zul Qarnain on 2/9/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private Context ctx;
    private ArrayList<Contacts> dList;
    public ContactAdapter(Context ctx, ArrayList<Contacts> dList) {
        this.ctx=ctx;
        this.dList=dList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_contact_row,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    holder.tv.setText(dList.get(position).getName());
    holder.bindView(dList.get(position));
    }

    @Override
    public int getItemCount() {
        return dList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView imageView;
        Contacts contacts;
        View itemView;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView= itemView;
            tv= itemView.findViewById(R.id.textView);
            imageView= itemView.findViewById(R.id.user_img);
        }

        public void bindView(final Contacts contacts) {
            this.contacts = contacts;
            Picasso.with(ctx).load(contacts.getImgAddress()).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment dialog =ContactDialogFragment.newInstance(contacts.getName(),contacts.getImgAddress());
                    dialog.show(((FragmentActivity) ctx).getSupportFragmentManager().beginTransaction(), "mydialog");

                }
            });
        }
    }
}
