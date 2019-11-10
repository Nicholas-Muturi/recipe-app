package m.nicholas.mealville;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ViewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        Intent recipeIntent = getIntent();
        String recipeTitle = recipeIntent.getStringExtra("recipeTitle");
        String recipeDescr = recipeIntent.getStringExtra("recipeDescr");
        String[] recipeIngredients = recipeIntent.getStringArrayExtra("recipeIngr");
        String[] recipeSteps = recipeIntent.getStringArrayExtra("recipeSteps");

    }
}
