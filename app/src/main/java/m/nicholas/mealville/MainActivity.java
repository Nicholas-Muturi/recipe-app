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
        if(Recipe.getAllRecipes().isEmpty()){
            Toast.makeText(MainActivity.this,"Add a new recipe to have it appear here",Toast.LENGTH_LONG).show();
        }

        myRecipeListAdapter recipeListAdapter = new myRecipeListAdapter(this,R.layout.single_recipe_list_item);
        recipeListView.setAdapter(recipeListAdapter);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == fab) {
            Intent intent = new Intent(MainActivity.this,NewRecipeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
