package com.hjianfei.ticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.ticket.R;
import com.hjianfei.ticket.bean.MyUser;
import com.hjianfei.ticket.utils.SharedPreferencesUtils;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class PersionActivity extends AppCompatActivity {

    private TextView p_name, p_real, p_cart, p_number, p_phone, p_email;
    private Button btn_out;
    private MyUser userInfo = new MyUser();
    private LovelyProgressDialog progressDialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                progressDialog.dismiss();
                userInfo = (MyUser) msg.obj;
                initView();
            }
            if (msg.what == 2) {
                progressDialog.dismiss();
                Toast.makeText(PersionActivity.this, "系统故障,稍后再试", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

    }

    private void initData() {
        username = (String) SharedPreferencesUtils.getParam(PersionActivity.this, "username", "");
        progressDialog = new LovelyProgressDialog(PersionActivity.this);
        progressDialog.setMessage("正在查询,请稍后。。。");
        progressDialog.setTopColorRes(R.color.teal);
        progressDialog.setCancelable(true);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<MyUser> query = new BmobQuery<MyUser>();
                query.addWhereEqualTo("username", username);
                query.findObjects(new FindListener<MyUser>() {
                    @Override
                    public void done(List<MyUser> object, BmobException e) {
                        if (e == null) {
                            Message message = Message.obtain();
                            message.obj = object.get(0);
                            message.what = 1;
                            mHandler.sendMessage(message);
                        } else {
                            Message message = Message.obtain();
                            message.what = 2;
                            mHandler.sendMessage(message);

                        }
                    }
                });
            }
        }).start();

    }

    private void initView() {
        setContentView(R.layout.activity_persion);
        p_name = (TextView) findViewById(R.id.p_name);
        p_real = (TextView) findViewById(R.id.p_real);
        p_cart = (TextView) findViewById(R.id.p_cart);
        p_number = (TextView) findViewById(R.id.p_number);
        p_phone = (TextView) findViewById(R.id.p_phone);
        p_email = (TextView) findViewById(R.id.p_email);
        btn_out = (Button) findViewById(R.id.btn_out);
        p_name.setText("用户名：" + userInfo.getUsername());
        p_real.setText("真实姓名：" + userInfo.getReal_name());
        p_cart.setText(userInfo.getUser_id_type());
        p_number.setText(userInfo.getUser_id());
        p_phone.setText("手机号码：" + userInfo.getMobilePhoneNumber());
        p_email.setText("用户邮箱：" + userInfo.getEmail());
        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();   //清除缓存用户对象
                Intent intent = new Intent(PersionActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
