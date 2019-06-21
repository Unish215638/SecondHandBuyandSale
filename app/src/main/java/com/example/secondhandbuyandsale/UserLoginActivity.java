package com.example.secondhandbuyandsale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import API.API;
import Model.AuthToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.Url;

public class UserLoginActivity extends AppCompatActivity {
EditText et_username,et_password;
Button btn_login,btn_sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
//        getSupportActionBar().setTitle("Login Form");

        et_username=findViewById(R.id.etUsername);
        et_password=findViewById(R.id.etPassword);

        btn_login=findViewById(R.id.btnLogin);
        btn_sign_up=findViewById(R.id.btnSignUp);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUSer();
            }
        });


    }

    private void checkUSer(){
        API api= Url.getInstance().create(API.class);
        String username=et_username.getText().toString().trim();
        String password=et_password.getText().toString().trim();

        Call<AuthToken> userCall=api.checkUser(username,password);

       userCall.enqueue(new Callback<AuthToken>() {
           @Override
           public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
               if(response.isSuccessful()){
                   AuthToken authToken= response.body();
                   Toast.makeText(UserLoginActivity.this,authToken.getToken(), Toast.LENGTH_SHORT).show();
//                   return;
               }
//               else {
//                   Toast.makeText(UserLoginActivity.this, response.code(), Toast.LENGTH_SHORT).show();
//               }

           }

           @Override
           public void onFailure(Call<AuthToken> call, Throwable t) {
               Toast.makeText(UserLoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

           }
       });
    }

}
