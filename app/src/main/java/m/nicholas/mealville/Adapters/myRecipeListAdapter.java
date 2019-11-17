package m.nicholas.mealville.Adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import m.nicholas.mealville.R;
import m.nicholas.mealville.models.myOldRecipe;

public class myRecipeListAdapter extends ArrayAdapter<myOldRecipe> {
    private Context mContext;
    private List<myOldRecipe> mMyOldRecipes;

    public myRecipeListAdapter(@NonNull Context context, int resource, List<myOldRecipe> myOldRecipes) {
        super(context, resource);
        this.mContext = context;
        this.mMyOldRecipes = myOldRecipes;
    }

    @Override
    public int getCount() {
        return mMyOldRecipes.size();
    }

    @Nullable
    @Override
    public myOldRecipe getItem(int position) {
        return mMyOldRecipes.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String mealTitle = getItem(position).getMealTitle();
        String description = getItem(position).getDescription();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listView;

        if(convertView == null){
            listView = inflater.inflate(R.layout.single_recipe_list_item,null);
            TextView tvMealTitle = listView.findViewById(R.id.tvMealTitle);
            TextView tvMealDescription = listView.findViewById(R.id.tvMealDescription);
            tvMealTitle.setText(mealTitle);
            tvMealDescription.setSingleLine(true);
            tvMealDescription.setEllipsize(TextUtils.TruncateAt.END);
            tvMealDescription.setText(description);
        } else {
            listView = convertView;
        }

        return listView;
    }



}
