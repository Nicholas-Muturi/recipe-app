package m.nicholas.mealville.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;

public class ViewRecipeActivity extends AppCompatActivity {
    @BindView(R.id.tvViewTitle) TextView tvViewTitle;
    @BindView(R.id.tvViewDescr) TextView tvViewDescr;
    @BindView(R.id.tvViewIngredients) TextView tvViewIngredients;
    @BindView(R.id.tvViewSteps) TextView tvViewSteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        ButterKnife.bind(this);
        Intent recipeIntent = getIntent();
        String recipeTitle = recipeIntent.getStringExtra("recipeTitle");
        String recipeDescr = recipeIntent.getStringExtra("recipeDescr");
        String[] recipeIngredients = recipeIntent.getStringArrayExtra("recipeIngr");
        String[] recipeSteps = recipeIntent.getStringArrayExtra("recipeSteps");

        for(int i = 0; i < recipeIngredients.length; i++){
            int position = i+1;
            tvViewIngredients.append(position +". " +recipeIngredients[i]+"\n");
        }

        for(int i = 0; i < recipeSteps.length; i++){
            int position = i+1;
            tvViewSteps.append(position+ ". " +recipeSteps[i]+"\n");
        }

        tvViewTitle.setText(recipeTitle);
        tvViewDescr.setText(recipeDescr);

    }
}
