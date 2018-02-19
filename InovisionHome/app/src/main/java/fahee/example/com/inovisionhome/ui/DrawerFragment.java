package fahee.example.com.inovisionhome.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fahee.example.com.inovisionhome.R;

/**
 * Created by Zul Qarnain on 2/10/2018.
 */

public class DrawerFragment  extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.drawer_fragment_view,
                container,false);
        return view;
    }

}
