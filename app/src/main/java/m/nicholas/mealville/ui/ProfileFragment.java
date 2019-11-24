package m.nicholas.mealville.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    @BindView(R.id.testFragment) TextView testFragment;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
