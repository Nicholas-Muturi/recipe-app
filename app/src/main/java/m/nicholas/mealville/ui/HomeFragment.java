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
    private Bundle args = new Bundle();

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
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
            HomeListFragment homeListFragment = new HomeListFragment();
            args.putString("break_key","breakfast");
            homeListFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,homeListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }
        if(view == imgLunch){
            HomeListFragment homeListFragment = new HomeListFragment();
            args.putString("lunch_key","lunch");
            homeListFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,homeListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }
        if(view == imgDinner){
            HomeListFragment homeListFragment = new HomeListFragment();
            args.putString("dinner_key","dinner");
            homeListFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,homeListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }
        if(view == imgSoup){
            HomeListFragment homeListFragment = new HomeListFragment();
            args.putString("soups_key","soups");
            homeListFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,homeListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }
        if(view == imgSnacks){
            HomeListFragment homeListFragment = new HomeListFragment();
            args.putString("snacks_key","snacks");
            homeListFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,homeListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }

    }
}
