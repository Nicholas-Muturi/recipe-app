package m.nicholas.mealville.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.Result;
import m.nicholas.mealville.ui.ViewRecipeActivity;

public class myRecyclerCardAdapter extends RecyclerView.Adapter<myRecyclerCardAdapter.myViewHolder> {
    private Context mContext;
    private List<Result> allResults;

    public myRecyclerCardAdapter(Context mContext, List<Result> results) {
        this.mContext = mContext;
        this.allResults = results;
    }

    @NonNull
    @Override
    public myRecyclerCardAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_recyclerview_recipe_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerCardAdapter.myViewHolder holder, int position) {
        holder.bindItems(allResults.get(position));
    }

    @Override
    public int getItemCount() {
        return allResults.size();
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
            itemView.setOnClickListener(this);
        }

        public void bindItems(Result result){
            String prepTime;
            String imageUrl = "https://spoonacular.com/recipeImages/" +result.getImage();
            if(result.getReadyInMinutes()<60){
                prepTime = "Prep Time: "+result.getReadyInMinutes()+ " min";
            } else {
                double time = (double) result.getReadyInMinutes()/60.0;
                DecimalFormat format = new DecimalFormat("##.0");
                prepTime = "Prep Time: "+ format.format(time)+ " hrs";
            }
            recipeTitle.setText(result.getTitle());
            recipePrepTime.setText(prepTime);
            Picasso.get().load(imageUrl).into(recipeImage);
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
