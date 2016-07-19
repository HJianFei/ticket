package com.hjianfei.ticket.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hjianfei.ticket.R;
import com.hjianfei.ticket.activity.LoginActivity;
import com.hjianfei.ticket.activity.MyOrderActivity;
import com.hjianfei.ticket.bean.MyUser;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.bmob.v3.BmobUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private TextView start_, end_;
    private EditText train, c_name, c_order_num;
    private Button search_btn;
    private DatePickerDialog dpds;
    private Calendar now;
    private Context mContext;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static OrderFragment newInstance(String tag) {
        Bundle args = new Bundle();
        args.putString("OrderFragment", tag);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        start_ = (TextView) view.findViewById(R.id.start_dates);
        end_ = (TextView) view.findViewById(R.id.end_dates);
        train = (EditText) view.findViewById(R.id.train);
        c_name = (EditText) view.findViewById(R.id.c_name);
        c_order_num = (EditText) view.findViewById(R.id.c_order_num);
        search_btn = (Button) view.findViewById(R.id.search_btn);
        start_.setText(formatter.format(new Date()));
        end_.setText(formatter.format(new Date()));
        start_.setOnClickListener(this);
        end_.setOnClickListener(this);
        search_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_dates:
                now = Calendar.getInstance();
                dpds = DatePickerDialog.newInstance(
                        this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpds.show(this.getActivity().getFragmentManager(), "起始日期");
                break;
            case R.id.end_dates:
                now = Calendar.getInstance();
                dpds = DatePickerDialog.newInstance(
                        this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpds.show(this.getActivity().getFragmentManager(), "结束日期");
                break;
            case R.id.search_btn:
                System.out.println("进来了");
                MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
                if (userInfo == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("用户没登陆");
                    builder.setMessage("请先登录");
                    builder.setPositiveButton("返回登陆", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.create().show();
                    return;
                }
                String start = start_.getText().toString();
                String end = end_.getText().toString();
                String trains = train.getText().toString();
                String names = c_name.getText().toString();
                String order_id = c_order_num.getText().toString();
                Intent intent = new Intent(mContext, MyOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("start", start);
                bundle.putString("end", end);
                bundle.putString("trains", trains);
                bundle.putString("names", names);
                bundle.putString("order_id", order_id);
                intent.putExtra("data", bundle);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "";
        if (dayOfMonth < 10 && (monthOfYear + 1) < 10) {
            date = year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth;
        } else if ((monthOfYear + 1) < 10 && dayOfMonth >= 10) {
            date = year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth;
        } else if ((monthOfYear + 1) >= 10 && dayOfMonth < 10) {
            date = year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth;
        }
        if ("起始日期".equals(dpds.getTag())) {
            start_.setText(date);
        } else {
            end_.setText(date);
        }
    }
}
