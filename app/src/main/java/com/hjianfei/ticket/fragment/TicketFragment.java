package com.hjianfei.ticket.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hjianfei.ticket.R;
import com.hjianfei.ticket.activity.TicketListActivity;
import com.hjianfei.ticket.adapter.TimeAdapter;
import com.hjianfei.ticket.cityutils.CityActivity;
import com.hjianfei.ticket.constant.Constants;
import com.hjianfei.ticket.utils.SharedPreferencesUtils;
import com.hjianfei.ticket.utils.TrainNetUitl;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Context mContext;
    private TextView state_start, state_end, start_date, start_time, cart_type, all_cart, gdc_cart, z_cart, t_cart, k_cart;
    private Button btn_search;
    private Handler mHalder;
    private String start;
    private String end;
    private String date;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private LovelyProgressDialog progressDialog;
//    private ListView lv_common;

    public static TicketFragment newInstance(String tag) {
        Bundle args = new Bundle();
        args.putString("TicketFragment", tag);
        TicketFragment fragment = new TicketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
//        initData();
        initView(view);
        initListener();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initView(View view) {
        state_start = (TextView) view.findViewById(R.id.state_start);
        state_end = (TextView) view.findViewById(R.id.state_end);
        start_date = (TextView) view.findViewById(R.id.start_date);
        start_time = (TextView) view.findViewById(R.id.start_time);
        cart_type = (TextView) view.findViewById(R.id.cart_type);
        all_cart = (TextView) view.findViewById(R.id.all_cart);
        gdc_cart = (TextView) view.findViewById(R.id.gdc_cart);
        z_cart = (TextView) view.findViewById(R.id.z_cart);
        t_cart = (TextView) view.findViewById(R.id.t_cart);
        k_cart = (TextView) view.findViewById(R.id.k_cart);
        btn_search = (Button) view.findViewById(R.id.btn_search);
//        lv_common = (ListView) view.findViewById(R.id.re_common);
        start_date.setText(formatter.format(new Date()));

    }

    private void initListener() {
        state_start.setOnClickListener(this);
        state_end.setOnClickListener(this);
        start_date.setOnClickListener(this);
        start_time.setOnClickListener(this);
        cart_type.setOnClickListener(this);
        all_cart.setOnClickListener(this);
        gdc_cart.setOnClickListener(this);
        z_cart.setOnClickListener(this);
        t_cart.setOnClickListener(this);
        k_cart.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        mHalder = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressDialog.dismiss();
                if (msg.obj != null && msg.what == Constants.STATE_TYPE_FRG) {

                    Intent intent3 = new Intent(mContext, TicketListActivity.class);
                    intent3.putExtra("start_state", start);
                    intent3.putExtra("end_state", end);
                    intent3.putExtra("date", date);
                    intent3.putExtra("jsonString", (String) msg.obj);
                    startActivity(intent3);

                }
                if (msg.what == 2) {
                    Toast.makeText(mContext, "请求出错", Toast.LENGTH_SHORT).show();
                }
                if (msg.what == 1) {
                    progressDialog.dismiss();
                    Toast.makeText(mContext, "数据出错啦", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.state_start:
                Intent intent = new Intent(mContext, CityActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.state_end:
                Intent intent2 = new Intent(mContext, CityActivity.class);
                startActivityForResult(intent2, 2);
                break;
            case R.id.start_date:
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(this.getActivity().getFragmentManager(), "出发日期");
                break;
            case R.id.start_time:

                ArrayAdapter<String> adapter = new TimeAdapter(mContext, R.layout.item_time, loadDatas(R.array.time));
                new LovelyChoiceDialog(mContext)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTopColorRes(R.color.primary)
                        .setTitle("出发时间")
                        .setItems(adapter, new LovelyChoiceDialog.OnItemSelectedListener<String>() {
                            @Override
                            public void onItemSelected(int position, String item) {
                                start_time.setText(item);
                            }
                        }).show();
                break;
            case R.id.cart_type:
                final ArrayAdapter<String> cart_types = new TimeAdapter(mContext, R.layout.item_time, loadDatas(R.array.cart_type));
                new LovelyChoiceDialog(mContext)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTopColorRes(R.color.primary)
                        .setTitle("车席")
                        .setItems(cart_types, new LovelyChoiceDialog.OnItemSelectedListener<String>() {
                            @Override
                            public void onItemSelected(int position, String item) {
                                cart_type.setText(item);
                            }
                        }).show();
                break;
            case R.id.btn_search:
                progressDialog = new LovelyProgressDialog(mContext);
                progressDialog.setMessage("正在查询,请稍后。。。");
                progressDialog.setTopColorRes(R.color.teal);
                progressDialog.setCancelable(true);
                progressDialog.show();
                start = state_start.getText().toString();
                end = state_end.getText().toString();
                date = start_date.getText().toString();
                SharedPreferencesUtils.setParam(mContext, "a", start + "-" + end);
                TrainNetUitl netUitl = new TrainNetUitl(start, end, date, mHalder, Constants.STATE_TYPE_FRG);
                netUitl.getDatas();
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Constants.RSQC) {
            state_start.setText(data.getStringExtra("cityName"));
        } else if (requestCode == 2 && resultCode == Constants.RSQC) {
            state_end.setText(data.getStringExtra("cityName"));

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
        start_date.setText(date);
    }
}
