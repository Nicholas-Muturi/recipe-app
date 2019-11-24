package m.nicholas.mealville.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import m.nicholas.mealville.Constants;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.Recipe;
import m.nicholas.mealville.models.Result;
import m.nicholas.mealville.ui.ViewRecipeActivity;


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
        int position = getAdapterPosition();
        List<Result> allResults = new ArrayList<>();

        DatabaseReference resultRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESULT);
        resultRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    allResults.add(snapshot.getValue(Result.class));
                }
                getRecipeFromResultId(allResults.get(position));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getRecipeFromResultId(Result result){
        DatabaseReference recipeRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPE);
        recipeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Recipe selectedRecipe = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    if(result.getFirebaseId().equals(snapshot.getValue(Recipe.class).getFirebaseId())){
                        selectedRecipe = snapshot.getValue(Recipe.class);
                        break;
                    }

                Intent intent = new Intent(mContext, ViewRecipeActivity.class);
                intent.putExtra("key","customRecipe");
                intent.putExtra("recipe", Parcels.wrap(selectedRecipe));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
