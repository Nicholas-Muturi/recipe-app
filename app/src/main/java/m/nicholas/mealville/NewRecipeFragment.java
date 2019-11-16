package m.nicholas.mealville;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.models.Recipe;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewRecipeFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.etRecipeTitle) EditText recipeTitle;
    @BindView(R.id.etRecipeDescription) EditText recipeDescr;
    @BindView(R.id.etRecipeIngredients) EditText recipeIngredients;
    @BindView(R.id.etRecipeSteps) EditText recipeSteps;
    @BindView(R.id.btnSubmitRecipe) Button btnSubmitRecipe;

    public NewRecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_recipe, container, false);
        ButterKnife.bind(this,view);
        btnSubmitRecipe.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == btnSubmitRecipe){
            String title = recipeTitle.getText().toString().trim();
            String description = recipeDescr.getText().toString().trim();
            String unsplitIngredients = recipeIngredients.getText().toString().trim();
            String unsplitSteps = recipeSteps.getText().toString().trim();
            /* -- Input Validation -- */
            if(title.isEmpty()){
                recipeTitle.setError("This field cannot be empty");
            } else  if(description.isEmpty()){
                recipeDescr.setError("This field cannot be empty");
            } else  if(unsplitIngredients.isEmpty()){
                recipeIngredients.setError("This field cannot be empty");
            } else  if(unsplitSteps.isEmpty()){
                recipeSteps.setError("This field cannot be empty");
            } else {
                Recipe recipe = new Recipe(title,description,unsplitIngredients,unsplitSteps);
                recipeTitle.setText("");
                recipeDescr.setText("");
                recipeIngredients.setText("");
                recipeSteps.setText("");
                Toast.makeText(getContext(),"Recipe Added",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
