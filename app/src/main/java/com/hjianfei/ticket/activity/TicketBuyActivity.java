package com.hjianfei.ticket.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.ticket.R;
import com.hjianfei.ticket.bean.MyUser;
import com.hjianfei.ticket.bean.TicketMoney;
import com.hjianfei.ticket.bean.TicketOrder;
import com.hjianfei.ticket.bean.Trains;
import com.hjianfei.ticket.utils.SharedPreferencesUtils;
import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class TicketBuyActivity extends AppCompatActivity implements View.OnClickListener {

    private Trains.ResultBean resultBean;
    private TicketMoney.ResultMoney resultMoney;
    private TextView date, from, to, train_num, start_time, lishi, end_time, one, two, yz, wz, one_yuan, two_yuan, yz_yuan, wz_yuan, user_order_name;
    private LinearLayout add;
    private Button btn_order;
    private RadioButton one_ticket, two_ticket, yz_ticket, wz_ticket;
    private MyUser userInfo;
    private LovelyProgressDialog progressDialog;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private String username;
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
                Toast.makeText(TicketBuyActivity.this, "系统故障,稍后再试", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private String username1;
    private String user_id_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_buy);
        Bundle bundle = getIntent().getBundleExtra("data");
        resultBean = (Trains.ResultBean) bundle.get("resultBean");
        resultMoney = (TicketMoney.ResultMoney) bundle.get("resultMoney");
        userInfo = BmobUser.getCurrentUser(MyUser.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("确认订单");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initData();
    }

    private void initData() {
        progressDialog = new LovelyProgressDialog(TicketBuyActivity.this);
        progressDialog.setMessage("正在查询,请稍后。。。");
        progressDialog.setTopColorRes(R.color.teal);
        progressDialog.setCancelable(true);
        progressDialog.show();
        username = (String) SharedPreferencesUtils.getParam(TicketBuyActivity.this, "username", "");
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
        date = (TextView) findViewById(R.id.date);
        from = (TextView) findViewById(R.id.from);
        to = (TextView) findViewById(R.id.to);
        train_num = (TextView) findViewById(R.id.train_num);
        start_time = (TextView) findViewById(R.id.start_time);
        lishi = (TextView) findViewById(R.id.lishi);
        end_time = (TextView) findViewById(R.id.end_time);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        yz = (TextView) findViewById(R.id.yz);
        wz = (TextView) findViewById(R.id.wz);
        one_yuan = (TextView) findViewById(R.id.one_yuan);
        two_yuan = (TextView) findViewById(R.id.two_yuan);
        yz_yuan = (TextView) findViewById(R.id.yz_yuan);
        wz_yuan = (TextView) findViewById(R.id.wz_yuan);
        user_order_name = (TextView) findViewById(R.id.user_order_name);
        add = (LinearLayout) findViewById(R.id.add);
        btn_order = (Button) findViewById(R.id.btn_order);
        one_ticket = (RadioButton) findViewById(R.id.one_ticket);
        two_ticket = (RadioButton) findViewById(R.id.two_ticket);
        yz_ticket = (RadioButton) findViewById(R.id.yz_ticket);
        wz_ticket = (RadioButton) findViewById(R.id.wz_ticket);
        add.setOnClickListener(this);
        btn_order.setOnClickListener(this);
        one_ticket.setOnClickListener(this);
        two_ticket.setOnClickListener(this);
        yz_ticket.setOnClickListener(this);
        wz_ticket.setOnClickListener(this);
        date.setText(formatter.format(new Date()));
        from.setText(resultBean.getFrom_station_name());
        to.setText(resultBean.getTo_station_name());
        train_num.setText(resultBean.getTrain_no());
        start_time.setText(resultBean.getStart_time());
        lishi.setText(resultBean.getLishi());
        end_time.setText(resultBean.getArrive_time());
        one.setText(resultBean.getZy_num());
        two.setText(resultBean.getZe_num());
        yz.setText(resultBean.getYz_num());
        wz.setText(resultBean.getWz_num());
        one_yuan.setText("￥" + resultMoney.getZy());
        two_yuan.setText("￥" + resultMoney.getZe());
        yz_yuan.setText("￥" + resultMoney.getYz());
        wz_yuan.setText("￥" + resultMoney.getWz());
    }

    public void getData() {
        TicketOrder order = new TicketOrder();
        if (one_ticket.isChecked()) {
            order.setCart_type("一等座");
            order.setTicket_money(resultMoney.getZy());
        }
        if (two_ticket.isChecked()) {
            order.setCart_type("二等座");
            order.setTicket_money(resultMoney.getZe());
        }
        if (yz_ticket.isChecked()) {
            order.setCart_type("硬座");
            order.setTicket_money(resultMoney.getYz());
        }
        if (wz_ticket.isChecked()) {
            order.setCart_type("无座");
            order.setTicket_money(resultMoney.getWz());
        }
        order.setFrom_station_name(resultBean.getFrom_station_name());
        order.setTo_station_name(resultBean.getTo_station_name());
        order.setTrain_no(resultBean.getTrain_no());
        order.setStart_time(resultBean.getStart_time());
        order.setLishi(resultBean.getLishi());
        order.setArrive_time(resultBean.getArrive_time());

        order.setUser_name(username1);
        order.setUser_id(user_id_cart);

        order.setDate(formatter.format(new Date()));
        order.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    progressDialog.dismiss();
                    Toast.makeText(TicketBuyActivity.this, "订票成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TicketBuyActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(TicketBuyActivity.this, "订票失败", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                username1 = userInfo.getReal_name();
                user_id_cart = userInfo.getUser_id();
                String[] items = {username1 + ":" + user_id_cart};
                new LovelyChoiceDialog(this, R.style.CheckBoxTintTheme)
                        .setTopColorRes(R.color.ticket_text)
                        .setTitle("选择乘客")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItemsMultiChoice(items, new LovelyChoiceDialog.OnItemsSelectedListener<String>() {
                            @Override
                            public void onItemsSelected(List<Integer> positions, List<String> items) {
                                user_order_name.setText(TextUtils.join("\n", items));
                                user_order_name.setVisibility(View.VISIBLE);
                            }
                        })
                        .setConfirmButtonText("确定")
                        .show();
                break;
            case R.id.btn_order:
                MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
                if (userInfo == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(TicketBuyActivity.this);
                    builder.setTitle("用户没登陆");
                    builder.setMessage("请先登录");
                    builder.setPositiveButton("返回登陆", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(TicketBuyActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.create().show();
                }
                if (!one_ticket.isChecked() && !two_ticket.isChecked() && !yz_ticket.isChecked() && !wz_ticket.isChecked()) {
                    Toast.makeText(TicketBuyActivity.this, "请选择车席类型", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (user_order_name.getVisibility() == View.GONE) {
                    Toast.makeText(TicketBuyActivity.this, "请选择乘客", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog = new LovelyProgressDialog(TicketBuyActivity.this);
                progressDialog.setTitle("订单生成中...");
                progressDialog.setTopColorRes(R.color.teal);
                progressDialog.setCancelable(true);
                progressDialog.show();
                BmobQuery<TicketOrder> query = new BmobQuery<TicketOrder>();
                query.addWhereEqualTo("user_name", username1);
                query.addWhereEqualTo("date", formatter.format(new Date()));
                query.setLimit(10);
                query.findObjects(new FindListener<TicketOrder>() {
                    @Override
                    public void done(List<TicketOrder> object, BmobException e) {
                        if (e == null) {
                            if (object.size() > 0) {
                                progressDialog.dismiss();
                                Toast.makeText(TicketBuyActivity.this, "你今天有未出行的订单，订票失败", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                getData();
                            }

                        } else {
                            progressDialog.dismiss();
                            System.out.println(e.toString());
                        }
                    }

                });
                break;
            case R.id.one_ticket:
                one_ticket.setChecked(true);
                two_ticket.setChecked(false);
                yz_ticket.setChecked(false);
                wz_ticket.setChecked(false);

                break;
            case R.id.two_ticket:
                one_ticket.setChecked(false);
                two_ticket.setChecked(true);
                yz_ticket.setChecked(false);
                wz_ticket.setChecked(false);

                break;
            case R.id.yz_ticket:
                one_ticket.setChecked(false);
                two_ticket.setChecked(false);
                yz_ticket.setChecked(true);
                wz_ticket.setChecked(false);

                break;
            case R.id.wz_ticket:
                one_ticket.setChecked(false);
                two_ticket.setChecked(false);
                yz_ticket.setChecked(false);
                wz_ticket.setChecked(true);
                break;
        }
    }
}
