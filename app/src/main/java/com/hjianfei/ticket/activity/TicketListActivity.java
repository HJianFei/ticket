package com.hjianfei.ticket.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hjianfei.ticket.R;
import com.hjianfei.ticket.adapter.CommonAdapter;
import com.hjianfei.ticket.adapter.ViewHolder;
import com.hjianfei.ticket.bean.TicketMoney;
import com.hjianfei.ticket.bean.Trains;
import com.hjianfei.ticket.constant.Constants;
import com.hjianfei.ticket.fragment.MyDialogFragment;
import com.hjianfei.ticket.utils.TrainMoneyNetUtil;
import com.hjianfei.ticket.utils.TrainNetUitl;
import com.yalantis.phoenix.PullToRefreshView;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TicketListActivity extends AppCompatActivity {

    private ListView lv_tickets;
    private TextView time_lv;
    private CommonAdapter<Trains.ResultBean> mAdapter;
    private String date;
    private String end_state;
    private String start_state;
    private String jsonString;
    private Gson gson;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private List<Trains.ResultBean> trainLists;
    private PullToRefreshView mPullToRefreshView;
    private Handler mHandler = new Handler() {

        private TicketMoney.ResultMoney resultMoney;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.obj != null && msg.what == Constants.STATE_TYPE_MONEY) {
                Bundle bundle = (Bundle) msg.obj;
                String jsonString = (String) bundle.get("trainsJson");
                Trains.ResultBean resultBean = (Trains.ResultBean) bundle.get("resultBean");
                resultMoney = gson.fromJson(jsonString, TicketMoney.class).getResult();
                MyDialogFragment dialogFragment = new MyDialogFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("resultMoney", resultMoney);
                bundle1.putSerializable("resultBean", resultBean);
                dialogFragment.setArguments(bundle1);
                progressDialog.dismiss();
                dialogFragment.show(getFragmentManager(), "dialog");
            }
            if (msg.obj != null && msg.what == Constants.STATE_TYPE_NET) {
                mPullToRefreshView.setRefreshing(false);
                gson = new Gson();
                Trains trains = gson.fromJson(jsonString, Trains.class);
                trainLists = trains.getResult();
                mAdapter.notifyDataSetChanged();

            }
            if (msg.what == 1) {
                progressDialog.dismiss();
                Toast.makeText(TicketListActivity.this, "数据据请求失败", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private LovelyProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
        setContentView(R.layout.activity_ticket_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(start_state + "->" + end_state);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    private void initView() {
        time_lv = (TextView) findViewById(R.id.time_lv);
        time_lv.setText(formatter.format(new Date()));
        lv_tickets = (ListView) findViewById(R.id.lv_ticket);
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mAdapter = new CommonAdapter<Trains.ResultBean>(this, trainLists, R.layout.item_ticket) {
            @Override
            public void convert(ViewHolder holper, Trains.ResultBean item) {
                holper.setText(R.id.train_no, item.getTrain_no());
                holper.setText(R.id.start_station_name, item.getStart_station_name());
                holper.setText(R.id.end_station_name, item.getEnd_station_name());
                holper.setText(R.id.start_time, item.getStart_time());
                holper.setText(R.id.lishi, item.getLishi());
                holper.setText(R.id.end_time, item.getArrive_time());
                holper.setText(R.id.zy_num, item.getZy_num());
                holper.setText(R.id.ze_num, item.getZe_num());
                holper.setText(R.id.yz_num, item.getYz_num());
                holper.setText(R.id.wz_num, item.getWz_num());

            }
        };
        lv_tickets.setAdapter(mAdapter);
        lv_tickets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                progressDialog = new LovelyProgressDialog(TicketListActivity.this);
                progressDialog.setMessage("正在查询,请稍后。。。");
                progressDialog.setTopColorRes(R.color.teal);
                progressDialog.setCancelable(true);
                progressDialog.show();
                Trains.ResultBean resultBean = trainLists.get(position);
                TrainMoneyNetUtil moneyNetUtil = new TrainMoneyNetUtil(mHandler, resultBean, Constants.STATE_TYPE_MONEY);
                moneyNetUtil.getTrainMoney();
            }
        });
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initDatasFromNet(Constants.STATE_TYPE_NET);
            }
        });
    }

    private void initDatasFromNet(int type) {
        TrainNetUitl netUitl = new TrainNetUitl(start_state, end_state, date, mHandler, type);
        netUitl.getDatas();

    }

    private void initDatas() {
        start_state = getIntent().getStringExtra("start_state");
        end_state = getIntent().getStringExtra("end_state");
        date = getIntent().getStringExtra("date");
        jsonString = getIntent().getStringExtra("jsonString");
        gson = new Gson();
        Trains trains = gson.fromJson(jsonString, Trains.class);
        trainLists = trains.getResult();
    }
}
