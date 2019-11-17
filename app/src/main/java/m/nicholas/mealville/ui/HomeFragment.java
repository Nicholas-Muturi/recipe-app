package m.nicholas.mealville.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
            BreakfastListFragment breakfastListFragment = new BreakfastListFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,breakfastListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }
        if(view == imgLunch){
            LunchListFragment lunchListFragment = new LunchListFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,lunchListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }
        if(view == imgDinner){
            DinnerListFragment dinnerListFragment = new DinnerListFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,dinnerListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }
        if(view == imgSoup){
            SoupListFragment soupListFragment = new SoupListFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,soupListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }
        if(view == imgSnacks){
            SnacksListFragment snacksListFragment = new SnacksListFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,snacksListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }

    }
}
