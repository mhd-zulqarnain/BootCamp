package fahee.example.com.inovisionhome.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import fahee.example.com.inovisionhome.adapter.RecyclerDivider;
import fahee.example.com.inovisionhome.model.Contacts;
import fahee.example.com.inovisionhome.R;
import fahee.example.com.inovisionhome.adapter.ContactAdapter;

/**
 * Created by Zul Qarnain on 2/9/2018.
 */

public class ContactsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.contacts_fragment_view, container, false);
        recyclerView = view.findViewById(R.id.rcyc_veiw);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ContactAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new RecyclerDivider(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    public ArrayList<Contacts> getData() {
        ArrayList<Contacts> data = new ArrayList<>();
        data.add(new Contacts("Giavani", "https://www.shareicon.net/data/128x128/2016/03/20/433477_people_128x128.png"));
        data.add(new Contacts("Noell", "https://www.shareicon.net/data/128x128/2015/12/14/207815_face_300x300.png"));
        data.add(new Contacts("Bhaskar", "https://www.shareicon.net/data/128x128/2015/12/14/207873_face_300x300.png"));
        data.add(new Contacts("Dasard", "https://www.shareicon.net/data/128x128/2015/12/14/207904_face_300x300.png"));
        data.add(new Contacts("VaDas", "https://www.shareicon.net/data/128x128/2016/03/20/433477_people_128x128.png"));
        data.add(new Contacts("Vohitk", "https://www.shareicon.net/data/128x128/2015/12/14/207815_face_300x300.png"));

        return data;
    }
}
