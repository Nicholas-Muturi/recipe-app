package m.nicholas.mealville.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.myOldRecipe;

public class myRecyclerCardAdapter extends RecyclerView.Adapter<myRecyclerCardAdapter.myViewHolder> {
    private Context mContext;
    private List<myOldRecipe> myOldRecipes;

    public myRecyclerCardAdapter(Context mContext, List<myOldRecipe> myOldRecipes) {
        this.mContext = mContext;
        this.myOldRecipes = myOldRecipes;
    }

    @NonNull
    @Override
    public myRecyclerCardAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recyclerview_recipe_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerCardAdapter.myViewHolder holder, int position) {
        holder.bindItems(myOldRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return myOldRecipes.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.recipeImage) ImageView recipeImage;
        @BindView(R.id.recipeCardTitle) TextView recipeTitle;
        @BindView(R.id.recipeCardReadyTime) TextView recipePrepTime;
        private Context mContext;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
        }

        @Override
        public void onClick(View view) {

        }

        public void bindItems(myOldRecipe recipe){
            recipeTitle.setText(recipe.getMealTitle());
        }

    }
}
