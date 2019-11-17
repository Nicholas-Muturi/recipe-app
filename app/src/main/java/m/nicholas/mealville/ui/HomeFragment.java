package m.nicholas.mealville.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.imgBreakfast) ImageView imgBreakfast;
    @BindView(R.id.imgLunch) ImageView imgLunch;
    @BindView(R.id.imgDinner) ImageView imgDinner;
    @BindView(R.id.imgSoup) ImageView imgSoup;
    @BindView(R.id.imgSnacks) ImageView imgSnacks;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        imgBreakfast.setOnClickListener(this);
        imgLunch.setOnClickListener(this);
        imgDinner.setOnClickListener(this);
        imgSoup.setOnClickListener(this);
        imgSnacks.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == imgBreakfast){
            NewRecipeFragment recipeFragment = new NewRecipeFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,recipeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }
        if(view == imgLunch){
            System.out.println("Works 2");
        }
        if(view == imgDinner){
            System.out.println("Works 3");
        }
        if(view == imgSoup){
            System.out.println("Works 4");
        }
        if(view == imgSnacks){
            System.out.println("Works 5");
        }

    }
}
