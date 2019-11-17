package m.nicholas.mealville.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.Adapters.myRecyclerCardAdapter;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.ApiSearchResult;
import m.nicholas.mealville.models.Result;
import m.nicholas.mealville.network.RapidApi;
import m.nicholas.mealville.network.RapidApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SnacksListFragment extends Fragment {
    @BindView(R.id.rvRecyclerView) RecyclerView recipeRecyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.errorTextView) TextView mErrorTextView;

    private myRecyclerCardAdapter recyclerCardAdapter;

    public SnacksListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this,view);

        RapidApi client = RapidApiClient.getClient();
        Call<ApiSearchResult> call = client.getRecipes("snack","snacks",30);
        call.enqueue(new Callback<ApiSearchResult>() {
            @Override
            public void onResponse(Call<ApiSearchResult> call, Response<ApiSearchResult> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    List<Result> resultList = response.body().getResults();
                    recyclerCardAdapter = new myRecyclerCardAdapter(getContext(),resultList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                    recipeRecyclerView.setAdapter(recyclerCardAdapter);
                    recipeRecyclerView.setLayoutManager(gridLayoutManager);
                    showResults();
                }
                else {
                    showUnsuccessfulMessage();
                }
            }
            @Override
            public void onFailure(Call<ApiSearchResult> call, Throwable t) {
                showFailureMessage();
                hideProgressBar();
            }
        });
        return view;
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }

    private void showFailureMessage() {
        mErrorTextView.setText(getString(R.string.failure_message));
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText(getString(R.string.unsuccessful_message));
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showResults() {
        recipeRecyclerView.setVisibility(View.VISIBLE);
    }

}
