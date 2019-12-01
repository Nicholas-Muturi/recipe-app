package m.nicholas.mealville.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;

public class LoginActivity extends AppCompatActivity implements OnClickListener{
    @BindView(R.id.etEmailLoginField) EditText etEmailLogin;
    @BindView(R.id.etPasswordLoginField) EditText etPasswordLogin;
    @BindView(R.id.btnLogin) Button btnLogin;
    @BindView(R.id.loginProgressBar) ProgressBar progressBar;
    @BindView(R.id.tvCreateNewAccount) TextView tvCreateAccount;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
        createAuthListener();
    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin){
            validateThenLogin();
        }
        if(view == tvCreateAccount){
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
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

    private void validateThenLogin(){
        String email = etEmailLogin.getText().toString().trim();
        String password = etPasswordLogin.getText().toString().trim();

        if(email.isEmpty()) etEmailLogin.setError("Please fill in this field");
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) etEmailLogin.setError("Email doesn't have a proper format");
        else if(password.isEmpty()) etPasswordLogin.setError("Please fill in this field");
        else{
            showProgress_hideButton();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,task->{
               if(task.isSuccessful()){
                   clearFields();
                   Intent intent = new Intent(this,MainActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                   hideProgress_showButton();
                   startActivity(intent);
                   finish();
               } else {
                   Toast.makeText(this,"Login failed, wrong email or password",Toast.LENGTH_SHORT).show();
                   hideProgress_showButton();
               }
            });
        }
    }

    private void createAuthListener(){
        mAuthStateListener = firebaseAuth -> {
            final FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user != null){
                Intent intent = new Intent(this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        };
    }

    private void clearFields(){
        etEmailLogin.setText("");
        etPasswordLogin.setText("");
    }

    private void showProgress_hideButton(){
        progressBar.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);
    }

    private void hideProgress_showButton(){
        progressBar.setVisibility(View.GONE);
        btnLogin.setVisibility(View.VISIBLE);
    }

}
