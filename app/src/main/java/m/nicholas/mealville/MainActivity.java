package m.nicholas.mealville;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import m.nicholas.mealville.Adapters.myRecipeListAdapter;
import m.nicholas.mealville.models.Recipe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mainRecipesList) ListView recipeListView;
    @BindView(R.id.floatingActionButton) FloatingActionButton fab;
    private String title;
    private String descr;
    private List<String> ingredients = new ArrayList<>();
    private List<String> steps = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Recipe testRecipe = null;

        /* -- PREVENTS CRASH -- */
        if(Recipe.getAllRecipes().isEmpty()){
            title = "Meal 1";
            descr = "Lorem ipsum description";
            ingredients.add("water");
            ingredients.add("Boil said water");
            testRecipe = new Recipe(title,descr,ingredients,steps);
        }


        Intent newRecipeIntent = getIntent();
        if(newRecipeIntent.getExtras() != null) {
            title = newRecipeIntent.getStringExtra("recipeTitle");
            descr = newRecipeIntent.getStringExtra("recipeDescr");
            ingredients = Arrays.asList(newRecipeIntent.getStringExtra("recipeIngredients").split(","));
            steps = Arrays.asList(newRecipeIntent.getStringExtra("recipeSteps").split("."));
            testRecipe = new Recipe(title,descr,ingredients,steps);
        }


        myRecipeListAdapter recipeListAdapter = new myRecipeListAdapter(this,R.layout.single_recipe_list_item,testRecipe);
        recipeListView.setAdapter(recipeListAdapter);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == fab) {
            Intent intent = new Intent(MainActivity.this,NewRecipeActivity.class);
            startActivity(intent);
        }
    }
}
