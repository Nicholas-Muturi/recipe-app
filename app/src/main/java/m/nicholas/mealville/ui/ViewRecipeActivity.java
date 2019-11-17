package m.nicholas.mealville.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.ExtendedIngredient;
import m.nicholas.mealville.models.Recipe;
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
    @BindView(R.id.tvViewServingNo) TextView tvServing;
    @BindView(R.id.foodImage) ImageView ivFoodImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        ButterKnife.bind(this);

        Intent recipeIntent = getIntent();
        int recipeId = recipeIntent.getIntExtra("rId",0);

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
                    List<String> ingredientNames = new ArrayList<>();
                    for(ExtendedIngredient ingredient :allIngredients){
                        tvViewIngredients.append("- "+ ingredient.getName() +"\n");
                    }

                    //Split Steps String
                    String[] steps = recipe.getInstructions().split("\\.");
                    for(int i = 0; i < steps.length; i++){
                        int counter = i+1;
                        tvViewSteps.append(counter +": "+ steps[i] +"\n");
                        System.out.println(counter +": "+ steps[i] +"\n");
                    }

                    tvViewTitle.setText(recipe.getTitle());
                    tvViewPrepTime.setText(prepTime);
                    tvServing.setText(0); // TODO: 17-Nov-19 Sort this out 
                    Picasso.get().load(recipe.getImage()).into(ivFoodImage);
                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                System.out.println("Massive Failure");
            }
        });

    }

    public void ApiRecipe(){

    }

}
