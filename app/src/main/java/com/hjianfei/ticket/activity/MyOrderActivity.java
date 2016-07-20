package com.hjianfei.ticket.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.ticket.R;
import com.hjianfei.ticket.bean.TicketOrder;
import com.xys.libzxing.zxing.encoding.EncodingUtils;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MyOrderActivity extends AppCompatActivity {
    private String start;
    private String end;
    private String trains;
    private String names;
    private String order_id;
    private TextView from, date, train_num, to, persion_name, cart_start, cart_date, cart_type, cart_money;
    private ImageView erweima;
    private TicketOrder ticketOrder;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                progressDialog.dismiss();
                ticketOrder = (TicketOrder) msg.obj;
                System.out.println(ticketOrder.toString());
                initView();

            }
            if (msg.what == 2) {
                progressDialog.dismiss();
                Toast.makeText(MyOrderActivity.this, "系统故障，请稍后再试", Toast.LENGTH_LONG).show();
            }
            if (msg.what == 3) {
                progressDialog.dismiss();
                Toast.makeText(MyOrderActivity.this, "没有订单信息", Toast.LENGTH_LONG).show();


            }
        }
    };
    private LovelyProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        start = (String) getIntent().getBundleExtra("data").get("start");
        end = (String) getIntent().getBundleExtra("data").get("end");
        trains = (String) getIntent().getBundleExtra("data").get("trains");
        names = (String) getIntent().getBundleExtra("data").get("names");
        order_id = (String) getIntent().getBundleExtra("data").get("order_id");
        setContentView(R.layout.activity_my_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("用户订单信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initDatas();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("用户订单");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        from = (TextView) findViewById(R.id.from);
        date = (TextView) findViewById(R.id.date);
        train_num = (TextView) findViewById(R.id.train_num);
        to = (TextView) findViewById(R.id.to);
        persion_name = (TextView) findViewById(R.id.persion_name);
        cart_start = (TextView) findViewById(R.id.cart_start);
        cart_date = (TextView) findViewById(R.id.cart_date);
        cart_type = (TextView) findViewById(R.id.cart_type);
        cart_money = (TextView) findViewById(R.id.cart_money);
        erweima = (ImageView) findViewById(R.id.erweima);

        from.setText(ticketOrder.getFrom_station_name());
        to.setText(ticketOrder.getTo_station_name());
        train_num.setText(ticketOrder.getTrain_no());
        date.setText(ticketOrder.getDate());
        persion_name.setText(ticketOrder.getUser_name());
        cart_start.setText(ticketOrder.getStart_time());
        cart_type.setText(ticketOrder.getCart_type());
        cart_money.setText(ticketOrder.getTicket_money());
        cart_date.setText(ticketOrder.getDate());
        String info = "出发站" + ticketOrder.getFrom_station_name() + ";" + "达到站:" + ticketOrder.getTo_station_name()
                + ";车次:" + ticketOrder.getTrain_no() + ";乘车时间:" + ticketOrder.getStart_time() + ";发车日期:" + ticketOrder.getDate() + ";席位:" + ticketOrder
                .getCart_type() + ";票价:" + ticketOrder.getTicket_money() + ";乘客姓名:" + ticketOrder.getUser_name() + ";证件号码:" + ticketOrder.getUser_id();
        Bitmap bitmap = EncodingUtils.createQRCode(info, 600, 600, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, null));
        erweima.setImageBitmap(bitmap);

    }

    private void initDatas() {
        progressDialog = new LovelyProgressDialog(MyOrderActivity.this);
        progressDialog.setTitle("请稍后...");
        progressDialog.setTopColorRes(R.color.teal);
        progressDialog.setCancelable(false);
        progressDialog.show();
        BmobQuery<TicketOrder> query = new BmobQuery<TicketOrder>();
        query.addWhereEqualTo("date", start);
//        query.addWhereEqualTo("train_no", trains);
        query.addWhereEqualTo("user_name", names);
//        query.addWhereEqualTo("date", order_id);

        query.setLimit(1);
        query.findObjects(new FindListener<TicketOrder>() {
            @Override
            public void done(List<TicketOrder> object, BmobException e) {
                if (e == null) {
                    if (object.size() > 0) {
                        Message message = Message.obtain();
                        message.obj = object.get(0);
                        message.what = 1;
                        mHandler.sendMessage(message);
                    } else {
                        Message message = Message.obtain();
                        message.what = 3;
                        mHandler.sendMessage(message);
                    }

                } else {
                    Message message = Message.obtain();
                    message.what = 2;
                    mHandler.sendMessage(message);
                }
            }
        });
    }
}
