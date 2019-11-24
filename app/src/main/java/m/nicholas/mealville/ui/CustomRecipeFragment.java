package m.nicholas.mealville.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.Adapters.myFirebaseViewHolder;
import m.nicholas.mealville.Constants;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.Custom_Recipe;
import m.nicholas.mealville.models.Result;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomRecipeFragment extends Fragment {
    @BindView(R.id.rvRecyclerView) RecyclerView recipeRecyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    private DatabaseReference mResultRef;
    private FirebaseRecyclerAdapter<Result, myFirebaseViewHolder> mFirebaseRecyclerAdapter;
    private ValueEventListener valueEventListener;

    public CustomRecipeFragment() {
        // Required empty public constructor
    }

    public static CustomRecipeFragment newInstance(){
        return new CustomRecipeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this,view);

        mResultRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESULT);
        setUpFirebaseAdapter();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFirebaseRecyclerAdapter.stopListening();
    }

    private void setUpFirebaseAdapter(){
        FirebaseRecyclerOptions<Result> options = new FirebaseRecyclerOptions.Builder<Result>()
                .setQuery(mResultRef,Result.class).build();

        mFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Result, myFirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myFirebaseViewHolder myFirebaseViewHolder, int i, @NonNull Result result) {
                myFirebaseViewHolder.bindItems(result);
            }

            @NonNull
            @Override
            public myFirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recyclerview_recipe_item,parent,false);
                return new myFirebaseViewHolder(view);
            }
        };

        recipeRecyclerView.setAdapter(mFirebaseRecyclerAdapter);
        recipeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        hideProgressBar();
        showResults();
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
