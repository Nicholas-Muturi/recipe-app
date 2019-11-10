package m.nicholas.mealville.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import m.nicholas.mealville.models.Recipe;

public class myRecipeListAdapter extends ArrayAdapter<Recipe> {
    private Context mContext;
    private Recipe mRecipe;

    public myRecipeListAdapter(@NonNull Context context, int resource, Recipe mRecipe) {
        super(context, resource);
        this.mContext = context;
        this.mRecipe = mRecipe;
    }

    @Override
    public int getCount() {
        return Recipe.getAllRecipes().size();
    }

    @Nullable
    @Override
    public Recipe getItem(int position) {
        return Recipe.getAllRecipes().get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
