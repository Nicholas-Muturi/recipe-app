package m.nicholas.mealville;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewRecipeActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.etRecipeTitle) EditText recipeTitle;
    @BindView(R.id.etRecipeDescription) EditText recipeDescr;
    @BindView(R.id.etRecipeIngredients) EditText recipeIngredients;
    @BindView(R.id.etRecipeSteps) EditText recipeSteps;
    @BindView(R.id.btnSubmitRecipe) Button btnSubmitRecipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        ButterKnife.bind(this);
        btnSubmitRecipe.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSubmitRecipe){
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
                /* -- Validation Passed -- */
                Intent intent = new Intent(NewRecipeActivity.this,MainActivity.class);
                intent.putExtra("recipeTitle",title);
                intent.putExtra("recipeDescr",description);
                intent.putExtra("recipeIngredients",unsplitIngredients);
                intent.putExtra("recipeSteps",unsplitSteps);
                startActivity(intent);
            }

        }//end IF
    }
}
