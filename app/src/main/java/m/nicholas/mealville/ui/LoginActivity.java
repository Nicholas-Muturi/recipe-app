package m.nicholas.mealville.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import m.nicholas.mealville.R;

public class LoginActivity extends AppCompatActivity implements OnClickListener{
    @BindView(R.id.etEmailLoginField) EditText etEmailLogin;
    @BindView(R.id.etPasswordLoginField) EditText etPasswordLogin;
    @BindView(R.id.btnLogin) Button btnLogin;
    @BindView(R.id.tvCreateNewAccount) TextView tvCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
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

    private void validateThenLogin(){
        String email = etEmailLogin.getText().toString().trim();
        String password = etPasswordLogin.getText().toString().trim();

        if(email.isEmpty()) etEmailLogin.setError("Please fill in this field");
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) etEmailLogin.setError("Email doesn't have a proper format");
        else if(password.isEmpty()) etPasswordLogin.setError("Please fill in this field");
        else{
            //Do login
        }


    }

}
