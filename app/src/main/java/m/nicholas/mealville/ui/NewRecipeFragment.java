package m.nicholas.mealville.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
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
    private static final int REQUEST_IMAGE_CAPTURE = 111;
    @BindView(R.id.etRecipeTitle) EditText recipeTitle;
    @BindView(R.id.etRecipePrepTime) EditText recipePrepTime;
    @BindView(R.id.etRecipeServing) EditText recipeServing;
    @BindView(R.id.etRecipeIngredients) EditText recipeIngredients;
    @BindView(R.id.etRecipeSteps) EditText recipeSteps;
    @BindView(R.id.btnAddImage) Button btnCaptureImage;
    @BindView(R.id.viewCapturedImage) ImageView recipeCustomImage;
    @BindView(R.id.btnSubmitRecipe) Button btnSubmitRecipe;
    private String imageEncoded = "";
    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();
        btnCaptureImage.setOnClickListener(this);
        btnSubmitRecipe.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == btnCaptureImage){
            openCamera();
        }
        if(view == btnSubmitRecipe){
            validate_and_createRecipe();
        }
    }

    private void openCamera(){
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(ContextCompat.checkSelfPermission(getView().getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            //request permission First
            String[] permissions = {Manifest.permission.CAMERA};
            requestPermissions(permissions,REQUEST_IMAGE_CAPTURE);
        }
        if(pictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivityForResult(pictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            recipeCustomImage.setImageBitmap(imageBitmap);
            encodeImage(imageBitmap);
        }
    }

    private void encodeImage(Bitmap imageBitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        imageEncoded = Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT);
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
            int customPrepTime = Integer.parseInt(stringTime);
            int customServing = Integer.parseInt(stringServing);
            String author;

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

            if (mAuth.getCurrentUser().getDisplayName() != null)
                author = "By " + mAuth.getCurrentUser().getDisplayName();
            else author = "By Anonymous";

            Recipe recipe = new Recipe(customTitle,customPrepTime,customServing,ingredientList,analyzedInstructionList,author);
            if(!imageEncoded.isEmpty()){
              recipe.setImage(imageEncoded);
            }
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
        if(!imageEncoded.isEmpty()){
            result.setImage(imageEncoded);
        }
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
        recipeCustomImage.setImageResource(R.color.backgroundGrey);
    }


}//complete end

