package m.nicholas.mealville.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import m.nicholas.mealville.R;
import m.nicholas.mealville.models.Result;


public class myFirebaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private View mView;
    private Context mContext;

    public myFirebaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mView = itemView;
        this.mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindItems(Result result){
        // TODO: 24-Nov-19 bind image for individual recipe items
        TextView recipeTitle = mView.findViewById(R.id.recipeCardTitle);
        TextView recipePrepTime = mView.findViewById(R.id.recipeCardReadyTime);

        recipeTitle.setText(result.getTitle());
        recipePrepTime.setText(String.valueOf(result.getReadyInMinutes()));
    }

    @Override
    public void onClick(View view) {
        Log.d("lol", "onClick: Works");
    }
}
