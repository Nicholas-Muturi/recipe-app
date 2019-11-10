package m.nicholas.mealville;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import m.nicholas.mealville.Adapters.myRecipeListAdapter;
import m.nicholas.mealville.models.Recipe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mainRecipesList) ListView recipeListView;
    @BindView(R.id.floatingActionButton) FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        List<String> testIngredients = new ArrayList<>();
        List<String> testSteps = new ArrayList<>();
        testIngredients.add("Dhania");
        testIngredients.add("Pilau Masala");
        testSteps.add("Boil rice");
        testSteps.add("Cut Onions");
        Recipe testRecipe = new Recipe("Pilau", "Amazing Dish",testIngredients,testSteps);
        myRecipeListAdapter recipeListAdapter = new myRecipeListAdapter(this,R.layout.single_recipe_list_item,testRecipe);
        recipeListView.setAdapter(recipeListAdapter);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == fab) {

        }
    }
}
