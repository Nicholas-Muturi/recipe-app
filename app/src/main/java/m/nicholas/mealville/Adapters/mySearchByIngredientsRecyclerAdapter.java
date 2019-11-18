package m.nicholas.mealville.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.FindByIngredients;
import m.nicholas.mealville.models.Result;
import m.nicholas.mealville.ui.ViewRecipeActivity;

public class mySearchByIngredientsRecyclerAdapter extends RecyclerView.Adapter<mySearchByIngredientsRecyclerAdapter.myViewHolder> {
    private Context mContext;
    private List<FindByIngredients> allResults;

    public mySearchByIngredientsRecyclerAdapter(Context mContext, List<FindByIngredients> results) {
        this.mContext = mContext;
        this.allResults = results;
    }

    @NonNull
    @Override
    public mySearchByIngredientsRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recyclerview_recipe_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mySearchByIngredientsRecyclerAdapter.myViewHolder holder, int position) {
        holder.bindItems(allResults.get(position));
    }

    @Override
    public int getItemCount() {
        return allResults.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipeImage) ImageView recipeImage;
        @BindView(R.id.recipeCardTitle) TextView recipeTitle;
        @BindView(R.id.recipeCardReadyTime) TextView tvMissingIngredients;
        private Context mContext;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindItems(FindByIngredients result){
            String missingIngredients = "Missing Ingredients: "+result.getMissedIngredientCount();
            recipeTitle.setText(result.getTitle());
            tvMissingIngredients.setText(missingIngredients);
            Picasso.get().load(result.getImage()).into(recipeImage);
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ViewRecipeActivity.class);
            intent.putExtra("key","apiRecipe");
            intent.putExtra("rId",allResults.get(itemPosition).getId());
            mContext.startActivity(intent);
        }

    }
}
