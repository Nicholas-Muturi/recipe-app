package m.nicholas.mealville.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.AnalyzedInstruction;
import m.nicholas.mealville.models.ExtendedIngredient;
import m.nicholas.mealville.models.Recipe;
import m.nicholas.mealville.models.Step;
import m.nicholas.mealville.network.RapidApi;
import m.nicholas.mealville.network.RapidApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewRecipeActivity extends AppCompatActivity {
    @BindView(R.id.tvViewTitle) TextView tvViewTitle;
    @BindView(R.id.tvViewIngredients) TextView tvViewIngredients;
    @BindView(R.id.tvViewSteps) TextView tvViewSteps;
    @BindView(R.id.tvViewPrepTime) TextView tvViewPrepTime;
    @BindView(R.id.tvViewServingNo) TextView tvServingNo;
    @BindView(R.id.tvViewAuthor) TextView tvViewAuthor;
    @BindView(R.id.foodImage) ImageView ivFoodImage;
    private int recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        ButterKnife.bind(this);

        Intent recipeIntent = getIntent();
        recipeId = recipeIntent.getIntExtra("rId",0);
        String key = recipeIntent.getStringExtra("key");

        switch (key){
            case "apiRecipe":
                getApiRecipe();
                break;
            case "customRecipe":
                getCustomRecipe();
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void getApiRecipe(){
        RapidApi client = RapidApiClient.getClient();
        Call<Recipe> call = client.getRecipes(recipeId);
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if(response.isSuccessful()){
                    Recipe recipe = response.body();

                    //Format time
                    String prepTime;
                    if(recipe.getReadyInMinutes()<60){
                        prepTime = recipe.getReadyInMinutes()+ " min";
                    } else {
                        double time = (double) recipe.getReadyInMinutes()/60.0;
                        DecimalFormat format = new DecimalFormat("##.0");
                        prepTime = format.format(time)+ " hrs";
                    }

                    //Get all names of ingredients
                    List<ExtendedIngredient> allIngredients = recipe.getExtendedIngredients();
                    for(ExtendedIngredient ingredient :allIngredients){
                        tvViewIngredients.append("- "+ ingredient.getOriginalString() +"\n");
                    }

                    //get Steps
                    List<Step> stepList = recipe.getAnalyzedInstructions().get(0).getSteps();
                    for(Step step : stepList){
                        tvViewSteps.append(step.getNumber()+": "+step.getStep()+ "\n\n");
                    }
                    tvViewTitle.setText(recipe.getTitle());
                    tvViewPrepTime.setText(prepTime);
                    tvServingNo.setText(String.valueOf(recipe.getServings()));
                    tvViewAuthor.setText(getResources().getString(R.string.api_recipe_author));
                    Picasso.get().load(recipe.getImage()).into(ivFoodImage);
                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                System.out.println("Massive Failure");
            }
        });
    }

    public void getCustomRecipe(){
        Recipe recipe = Parcels.unwrap(getIntent().getParcelableExtra("recipe"));
        String prepTime;
        if(recipe.getReadyInMinutes()<60){
            prepTime = recipe.getReadyInMinutes()+ " min";
        } else {
            double time = (double) recipe.getReadyInMinutes()/60.0;
            DecimalFormat format = new DecimalFormat("##.0");
            prepTime = format.format(time)+ " hrs";
        }

        //Get all names of ingredients
        List<ExtendedIngredient> allIngredients = recipe.getExtendedIngredients();
        for(ExtendedIngredient ingredient :allIngredients){
            tvViewIngredients.append("- "+ ingredient.getOriginalString() +"\n");
        }

        //get Steps
        List<Step> stepList = recipe.getAnalyzedInstructions().get(0).getSteps();
        for(Step step : stepList){
            tvViewSteps.append(step.getNumber()+": "+step.getStep()+ "\n\n");
        }
        tvViewTitle.setText(recipe.getTitle());
        tvViewPrepTime.setText(prepTime);
        tvServingNo.setText(String.valueOf(recipe.getServings()));
        tvViewAuthor.setText(recipe.getAuthor());
    }
}
