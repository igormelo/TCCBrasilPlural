package com.igormelo.tccbrasilplural;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.igormelo.tccbrasilplural.databinding.ActivitySignupBinding;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class Sigup extends AppCompatActivity {
    ActivitySignupBinding binding;
    String login = "igorak4@hotmail.com";
    String senha = "igormelo151725";
    ProgressDialog progressDialog;


    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        ButterKnife.inject(this);

        progressDialog = new ProgressDialog(Sigup.this,
                R.style.AppTheme);

       _loginButton.setOnClickListener(v -> login());

    }

    public void login() {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando");
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        String email = _emailText.getText().toString();
                        String password = _passwordText.getText().toString();
                        _loginButton.setEnabled(true);
                        if(email.equals(login)&&password.equals(senha)){
                            onLoginSuccess();
                        } else if(email.equals("") && password.equals("")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(Sigup.this);
                            builder.setTitle("Login failed");
                            builder.setMessage("Login e/ou senha em branco, por favor preencha os campos");
                            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    builder.create();
                            builder.show();
                            progressDialog.dismiss();
                        } else {
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
    }

    public void onLoginSuccess() {
            Intent i = new Intent(Sigup.this, MainActivity.class);
            startActivity(i);
            finish();
    }

    public void onLoginFailed() {
        progressDialog.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login failed");
        builder.setMessage("Login e/ou senha errados");
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
        _loginButton.setEnabled(true);
    }
}
