package com.hjianfei.ticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.ticket.R;
import com.hjianfei.ticket.adapter.TimeAdapter;
import com.hjianfei.ticket.bean.MyUser;
import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText reg_user_name, reg_user_password, reg_user_re_password, reg_real_name, reg_number, reg_phone, reg_email;
    private CheckBox reg_ck;
    private TextView reg_cart;
    private Button reg_btn;
    private LovelyProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("用户注册");
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
        reg_user_name = (EditText) findViewById(R.id.reg_user_name);
        reg_user_password = (EditText) findViewById(R.id.reg_user_password);
        reg_user_re_password = (EditText) findViewById(R.id.reg_user_re_password);
        reg_real_name = (EditText) findViewById(R.id.reg_real_name);
        reg_number = (EditText) findViewById(R.id.reg_number);
        reg_phone = (EditText) findViewById(R.id.reg_phone);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_ck = (CheckBox) findViewById(R.id.reg_ck);
        reg_cart = (TextView) findViewById(R.id.reg_cart);
        reg_btn = (Button) findViewById(R.id.reg_btn);
        reg_cart.setOnClickListener(this);
        reg_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_cart:
                ArrayAdapter<String> adapter = new TimeAdapter(this, R.layout.item_time, loadDatas(R.array.id_cart_type));
                new LovelyChoiceDialog(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTopColorRes(R.color.primary)
                        .setTitle("证件类型")
                        .setItems(adapter, new LovelyChoiceDialog.OnItemSelectedListener<String>() {
                            @Override
                            public void onItemSelected(int position, String item) {
                                reg_cart.setText(item);
                            }
                        }).show();
                break;
            case R.id.reg_btn:
                if (reg_user_name.getText().toString().isEmpty()
                        || reg_user_password.getText().toString().isEmpty()
                        || reg_user_re_password.getText().toString().isEmpty()
                        || reg_real_name.getText().toString().isEmpty()
                        || reg_number.getText().toString().isEmpty()
                        || reg_phone.getText().toString().isEmpty()
                        || reg_email.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "必填项不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!reg_user_password.getText().toString().equals(reg_user_re_password.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!reg_ck.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "确认勾选服务条款", Toast.LENGTH_SHORT).show();
                    return;

                }
                progressDialog = new LovelyProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("注册中...");
                progressDialog.setTopColorRes(R.color.teal);
                progressDialog.setCancelable(false);
                progressDialog.show();
                MyUser myUser = new MyUser();
                myUser.setUsername(reg_user_name.getText().toString());
                myUser.setPassword(reg_user_password.getText().toString());
                myUser.setReal_name(reg_real_name.getText().toString());
                myUser.setUser_id_type(reg_cart.getText().toString());
                myUser.setUser_id(reg_number.getText().toString());
                myUser.setMobilePhoneNumber(reg_phone.getText().toString());
                myUser.setEmail(reg_email.getText().toString());
                myUser.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e == null) {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("user_name", reg_user_name.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "系统故障,稍后再试", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }

    private List<String> loadDatas(int resource_id) {
        List<String> result = new ArrayList<>();
        String[] raw = getResources().getStringArray(resource_id);
        for (int i = 0; i < raw.length; i++) {
            result.add(raw[i]);
        }
        return result;
    }
}
