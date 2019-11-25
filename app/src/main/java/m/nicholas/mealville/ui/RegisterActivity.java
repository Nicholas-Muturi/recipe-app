package m.nicholas.mealville.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.etRegisterUsername) EditText etUsername;
    @BindView(R.id.etRegisterEmail) EditText etEmail;
    @BindView(R.id.etRegisterPassword) EditText etPassword;
    @BindView(R.id.etRegisterConfirmPassword) EditText etConfirmPassword;
    @BindView(R.id.btnRegister) Button btnRegister;
    @BindView(R.id.tvBackToLogin) TextView tvBackToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        btnRegister.setOnClickListener(this);
        tvBackToLogin.setOnClickListener(this);
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
        }
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

        }
    }

    private void setFirebaseDisplayName(String name){

    }

}
