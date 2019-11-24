package m.nicholas.mealville.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.Constants;
import m.nicholas.mealville.R;
import m.nicholas.mealville.models.AnalyzedInstruction;
import m.nicholas.mealville.models.ExtendedIngredient;
import m.nicholas.mealville.models.Recipe;
import m.nicholas.mealville.models.Result;
import m.nicholas.mealville.models.Step;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewRecipeFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "NewRecipeFragment";
    @BindView(R.id.etRecipeTitle) EditText recipeTitle;
    @BindView(R.id.etRecipePrepTime) EditText recipePrepTime;
    @BindView(R.id.etRecipeServing) EditText recipeServing;
    @BindView(R.id.etRecipeIngredients) EditText recipeIngredients;
    @BindView(R.id.etRecipeSteps) EditText recipeSteps;
    @BindView(R.id.btnSubmitRecipe) Button btnSubmitRecipe;

    public NewRecipeFragment() {
        // Required empty public constructor
    }

    public static NewRecipeFragment newInstance(){
        return new NewRecipeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_recipe, container, false);
        ButterKnife.bind(this,view);
        btnSubmitRecipe.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        validate_and_createRecipe();
    }

    private void validate_and_createRecipe(){
        String customTitle = recipeTitle.getText().toString().trim();
        String stringTime = recipePrepTime.getText().toString().trim();
        String stringServing = recipeServing.getText().toString().trim();
        List<String> customIngredients = Arrays.asList(recipeIngredients.getText().toString().trim().split(","));
        List<String> customSteps = Arrays.asList(recipeSteps.getText().toString().trim().split("\\."));

        if(customTitle.isEmpty()) recipeTitle.setError("Please fill in this field");
        else if(stringTime.isEmpty()) recipeTitle.setError("Please fill in this field");
        else if(stringServing.isEmpty()) recipeTitle.setError("Please fill in this field");
        else if(customIngredients.isEmpty()) recipeTitle.setError("Please fill in this field");
        else if(customSteps.isEmpty()) recipeTitle.setError("Please fill in this field");
        else {

            /* -- RETRIEVING STEPS -- */
            List<Step> stepList = new ArrayList<>();
            for(int i = 0; i < customSteps.size(); i++){
                Step step = new Step(i+1,customSteps.get(i));
                stepList.add(step);
            }

            /* -- RETRIEVING INGREDIENTS -- */
            List<ExtendedIngredient> ingredientList = new ArrayList<>();
            for(String cIngredient: customIngredients){
                ExtendedIngredient ingredient = new ExtendedIngredient(cIngredient);
                ingredientList.add(ingredient);
            }

            /* -- INITIALIZE ANALYZED INSTRUCTIONS -- */
            AnalyzedInstruction analyzedInstruction = new AnalyzedInstruction(stepList);
            List<AnalyzedInstruction> analyzedInstructionList = new ArrayList<>();
            analyzedInstructionList.add(analyzedInstruction);

            //Typecast Strings to Int
            int customPrepTime = Integer.parseInt(stringTime);
            int customServing = Integer.parseInt(stringServing);

            Recipe recipe = new Recipe(customTitle,customPrepTime,customServing,ingredientList,analyzedInstructionList);
            uploadRecipeToFirebase(recipe);
            resetFields();
        }
    }

    private void uploadRecipeToFirebase(Recipe recipe){
        //Push Recipe
        DatabaseReference mRecipeRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RECIPE);
        String firebaseRecipeId = mRecipeRef.push().getKey();
        recipe.setFirebaseId(firebaseRecipeId);
        assert firebaseRecipeId != null;
        mRecipeRef.child(firebaseRecipeId).setValue(recipe);

        //Push Result Object used in Searching
        DatabaseReference mResultRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESULT);
        Result result = new Result(firebaseRecipeId,recipe.getTitle(),recipe.getReadyInMinutes());
        mResultRef.push().setValue(result);

        //Notify User
        Toast.makeText(getContext(),"Recipe Stored", Toast.LENGTH_SHORT).show();
    }

    private void resetFields(){
        recipeTitle.setText("");
        recipePrepTime.setText("");
        recipeServing.setText("");
        recipeIngredients.setText("");
        recipeSteps.setText("");
    }


}//complete end

