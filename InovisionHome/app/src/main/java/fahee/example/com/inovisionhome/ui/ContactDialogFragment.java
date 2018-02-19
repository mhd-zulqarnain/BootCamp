package fahee.example.com.inovisionhome.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fahee.example.com.inovisionhome.R;
import fahee.example.com.inovisionhome.util.Messege;

/**
 * Created by Zul Qarnain on 2/14/2018.
 */

public class ContactDialogFragment extends DialogFragment {

    Button btnSelect;
    ImageView prfView;
    TextView prfName;
    public static final String CON_IMG="contact_img";
    public static final String CON_NAME="con_name";

    public static DialogFragment newInstance(String name,String img){
        Bundle obj= new Bundle();
        obj.putString(CON_NAME,name);
        obj.putString(CON_IMG,img);
        ContactDialogFragment frg = new ContactDialogFragment();
        frg.setArguments(obj);
        return frg;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        String nm = getArguments().getString(CON_NAME);
        String img = getArguments().getString(CON_IMG);

        Dialog alertDialog = new Dialog(getActivity());
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.contacts_dialog_view);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        btnSelect = alertDialog.findViewById(R.id.slect_btn);
        prfView = alertDialog.findViewById(R.id.contact_prf);
        prfName = alertDialog.findViewById(R.id.person_name);
        prfName.setText(nm);
        //Picasso.with(getActivity()).load(img).fit().into(prfView);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return alertDialog;
    }


}
