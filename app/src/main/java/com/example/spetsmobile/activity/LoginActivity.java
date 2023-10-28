package com.example.spetsmobile.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.spetsmobile.R;
import com.example.spetsmobile.itf.AuthInterface;
import com.example.spetsmobile.model.response.ApiReponse;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.restapi.AuthAPI;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements AuthInterface {

    private EditText edtUsername;
    private EditText edtPassword;

    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;

    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        textInputUsername = findViewById(R.id.textInputUsername);
        textInputPassword = findViewById(R.id.textInputPassword);

        tvError = findViewById(R.id.tvError);

        // login
        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                // kiểm tra dữ liệu
                if (validator(username, password)) {
                    AuthAPI authAPI = new AuthAPI(LoginActivity.this);
                    authAPI.login(username, password);
                }
            }
        });

        // forgot password
        TextView btnForgotPass = findViewById(R.id.btnForgotPass);
        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        // register
        TextView btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSuccess(String type, ApiReponse apiReponse) {
        // thiết lập token
        AuthResponse authResponse = (AuthResponse) apiReponse.getPayload();
        ConstantUtil.setAccessToken(authResponse.getAccessToken());
        ConstantUtil.setRefreshToken(authResponse.getRefreshToken());
        ConstantUtil.setAuth(authResponse);

        // đăng nhập thành công chuyển sang MainActivity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(String type, String error) {
        // hiển thị thông báo lỗi
        tvError.setVisibility(View.VISIBLE);
        tvError.setTextColor(Color.RED);
        tvError.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
    }

    private boolean validator(String username, String password) {
        int countError = 0;

        // kiểm tra tên đăng nhập
        if (username.length() == 0) {
            countError++;
            textInputUsername.setError("Vui lòng nhập tên đăng nhập!");
        } else {
            textInputUsername.setError(null);
        }

        // kiểm tra mật khẩu
        if (password.length() == 0) {
            countError++;
            textInputPassword.setError("Vui lòng nhập mật khẩu!");
        } else {
            textInputPassword.setError(null);
        }

        return countError == 0;
    }

}