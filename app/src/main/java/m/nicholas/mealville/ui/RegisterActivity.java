package m.nicholas.mealville.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";
    @BindView(R.id.etRegisterUsername) EditText etUsername;
    @BindView(R.id.etRegisterEmail) EditText etEmail;
    @BindView(R.id.etRegisterPassword) EditText etPassword;
    @BindView(R.id.etRegisterConfirmPassword) EditText etConfirmPassword;
    @BindView(R.id.btnRegister) Button btnRegister;
    @BindView(R.id.tvBackToLogin) TextView tvBackToLogin;
    @BindView(R.id.registerProgressBar) ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(this);
        tvBackToLogin.setOnClickListener(this);
        createAuthListener();
    }

    @Override
    public void onClick(View view) {
        if(view == btnRegister){
            validateThenRegister();
        }
        if(view == tvBackToLogin){
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthStateListener != null)
            mAuth.removeAuthStateListener(mAuthStateListener);
    }

    private void validateThenRegister(){
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPass = etConfirmPassword.getText().toString().trim();

        if(username.isEmpty()) etUsername.setError("Please fill in this field");
        else if(email.isEmpty()) etEmail.setError("Please fill in this field");
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) etEmail.setError("Email doesn't have a proper format");
        else if(password.isEmpty()) etPassword.setError("Please fill in this field");
        else if(confirmPass.isEmpty()) etConfirmPassword.setError("Please fill in this field");
        else if(!password.equals(confirmPass)) etConfirmPassword.setError("Passwords do not match");
        else{
            showProgress_hideButton();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,task -> {
               if(task.isSuccessful()){
                   Toast.makeText(this,"Registation Successful",Toast.LENGTH_SHORT).show();
                   setFirebaseDisplayName(task.getResult().getUser(),username);
               }else{
                   Toast.makeText(this,"Registation Failed",Toast.LENGTH_SHORT).show();
                   hideProgress_showButton();
               }
            });
        }
    }

    private void setFirebaseDisplayName(FirebaseUser user,String name){
        UserProfileChangeRequest setDisplayName = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
        user.updateProfile(setDisplayName).addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               Log.i(TAG, "setFirebaseDisplayName: Username set");
               hideProgress_showButton();
           }
        });
    }

    private void createAuthListener(){
        mAuthStateListener = firebaseAuth -> {
          final FirebaseUser user = firebaseAuth.getCurrentUser();
          if(user != null){
              Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
              intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(intent);
              finish();
          }
        };
    }

    private void clearFields(){
        etUsername.setText("");
        etEmail.setText("");
        etPassword.setText("");
        etConfirmPassword.setText("");
    }

    private void showProgress_hideButton(){
        progressBar.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.GONE);
    }

    private void hideProgress_showButton(){
        progressBar.setVisibility(View.GONE);
        btnRegister.setVisibility(View.VISIBLE);
    }

}
