package m.nicholas.mealville.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.Adapters.myRecyclerCardAdapter;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.myOldRecipe;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {
    @BindView(R.id.rvRecyclerView) RecyclerView recipeRecyclerView;
    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this,view);

        myRecyclerCardAdapter cardAdapter = new myRecyclerCardAdapter(getContext(), myOldRecipe.getAllMyOldRecipes());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recipeRecyclerView.setAdapter(cardAdapter);
        recipeRecyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }

}
