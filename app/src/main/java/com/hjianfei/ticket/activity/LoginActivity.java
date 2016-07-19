package com.hjianfei.ticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hjianfei.ticket.R;
import com.hjianfei.ticket.bean.MyUser;
import com.hjianfei.ticket.utils.SharedPreferencesUtils;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText user_name, user_password;
    private CheckBox ck_paw, ck_auto;
    private Button btn_login, btn_reg;
    private LovelyProgressDialog progressDialog;
    private String login_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_name = getIntent().getStringExtra("user_name");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("用户登录");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initView();

    }

    private void initView() {
        user_name = (EditText) findViewById(R.id.user_name);
        user_password = (EditText) findViewById(R.id.user_password);
        ck_auto = (CheckBox) findViewById(R.id.ck_auto);
        ck_paw = (CheckBox) findViewById(R.id.ck_paw);
        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
        user_name.setText(login_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (user_name.getText().toString().isEmpty() || user_password.getText().toString().isEmpty()) {
                    Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog = new LovelyProgressDialog(LoginActivity.this);
                progressDialog.setMessage("登陆中...");
                progressDialog.setTopColorRes(R.color.teal);
                progressDialog.setCancelable(false);
                progressDialog.show();
                MyUser myUser = new MyUser();
                myUser.setUsername(user_name.getText().toString());
                myUser.setPassword(user_password.getText().toString());
                myUser.login(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e == null) {
                            SharedPreferencesUtils.setParam(LoginActivity.this, "username", user_name.getText().toString());
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            System.out.println(e.toString());
                            Toast.makeText(LoginActivity.this, "系统故障,稍后再试", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.btn_reg:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}
