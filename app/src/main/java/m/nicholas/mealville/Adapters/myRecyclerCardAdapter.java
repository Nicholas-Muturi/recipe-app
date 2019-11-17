package m.nicholas.mealville.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;

public class myRecyclerCardAdapter extends RecyclerView.Adapter<myRecyclerCardAdapter.myViewHolder> {

    @NonNull
    @Override
    public myRecyclerCardAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerCardAdapter.myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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

        public void bindItems(){

        }

    }
}
