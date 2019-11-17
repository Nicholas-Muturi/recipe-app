package m.nicholas.mealville.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.Adapters.myRecyclerCardAdapter;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.ApiSearchResult;
import m.nicholas.mealville.models.Result;
import m.nicholas.mealville.models.myOldRecipe;
import m.nicholas.mealville.network.RapidApi;
import m.nicholas.mealville.network.RapidApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {
    @BindView(R.id.rvRecyclerView) RecyclerView recipeRecyclerView;
    private myRecyclerCardAdapter recyclerCardAdapter;

    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this,view);

        RapidApi client = RapidApiClient.getClient();
        Call<ApiSearchResult> call = client.getRecipes("breakfast");
        call.enqueue(new Callback<ApiSearchResult>() {
            @Override
            public void onResponse(Call<ApiSearchResult> call, Response<ApiSearchResult> response) {
                if(response.isSuccessful()){
                    List<Result> resultList = response.body().getResults();
                    recyclerCardAdapter = new myRecyclerCardAdapter(getContext(),resultList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                    recipeRecyclerView.setAdapter(recyclerCardAdapter);
                    recipeRecyclerView.setLayoutManager(gridLayoutManager);
                    System.out.println("Success");
                }
                else {
                    System.out.println("Not successful");
                }
            }
            @Override
            public void onFailure(Call<ApiSearchResult> call, Throwable t) {
                System.out.println("super failed");
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recipeRecyclerView.setAdapter(recyclerCardAdapter);
        recipeRecyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }

}
