package com.igormelo.tccbrasilplural;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.igormelo.tccbrasilplural.R.id.email;

public class Sigup extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    String login = "igor";
    String senha = "igor12345";
    ProgressDialog progressDialog;


    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        progressDialog = new ProgressDialog(Sigup.this,
                R.style.AppTheme);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando");
        progressDialog.show();
        // TODO: Implement your own authentication logic here.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        //onLoginFailed();
                        String email = _emailText.getText().toString();
                        String password = _passwordText.getText().toString();
                        _loginButton.setEnabled(true);
                        if(email.equals(login)&&password.equals(senha)){
                            //progressDialog.dismiss();
                            onLoginSuccess();
                        } else {
                            //progressDialog.dismiss();
                            onLoginFailed();
                        }
                    }
                }, 1000);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        //finish();
        //moveTaskToBack(true);
    }

    public void onLoginSuccess() {
            Intent i = new Intent(Sigup.this, MainActivity.class);
            startActivity(i);
            finish();
    }

    public void onLoginFailed() {
        //finish();
        //AlertDialog alertDialog = new AlertDialog();
        progressDialog.dismiss();
        _loginButton.setEnabled(true);
    }

}
