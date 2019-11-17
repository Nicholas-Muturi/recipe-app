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
    @BindView(R.id.tvViewIngredients) TextView tvViewIngredients;
    @BindView(R.id.tvViewSteps) TextView tvViewSteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        ButterKnife.bind(this);
        Intent recipeIntent = getIntent();

    }
}
