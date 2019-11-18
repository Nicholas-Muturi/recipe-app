package m.nicholas.mealville.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchByIngredientsFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.ingredientSearchTextView) TextView tvIngredients;
    @BindView(R.id.btnIngredientSearch) Button btnSearch;

    public SearchByIngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_by_ingredients, container, false);
        ButterKnife.bind(this,view);
        btnSearch.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == btnSearch){
            String searchTerm = tvIngredients.getText().toString().trim();
            if(searchTerm.isEmpty()) tvIngredients.setError("Please insert something to search");
            else {
                SearchListFragment searchListFragment = new SearchListFragment();
                Bundle args = new Bundle();
                args.putString("ingredients", searchTerm);
                searchListFragment.setArguments(args);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, searchListFragment).addToBackStack(null).commit();
            }
        }
    }
}
