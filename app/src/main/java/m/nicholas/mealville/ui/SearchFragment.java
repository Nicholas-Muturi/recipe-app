package m.nicholas.mealville.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.imgSingleMealSearch) ImageView imgSingleMeal;
    @BindView(R.id.imgIngredientSearch) ImageView imgIngredients;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,view);
        imgSingleMeal.setOnClickListener(this);
        imgIngredients.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == imgSingleMeal){
            SearchByMealFragment search = new SearchByMealFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,search).addToBackStack(null).commit();
        }
        if(view == imgIngredients){
            SearchByIngredientsFragment search = new SearchByIngredientsFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,search).addToBackStack(null).commit();
        }
    }
}
