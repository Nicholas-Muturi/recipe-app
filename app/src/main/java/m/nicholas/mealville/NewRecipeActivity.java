package m.nicholas.mealville;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import butterknife.BindView;

public class NewRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

    }

    @Override
    public void onClick(View view) {

    }
}
