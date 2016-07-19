package com.hjianfei.ticket.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hjianfei.ticket.R;
import com.hjianfei.ticket.activity.LoginActivity;
import com.hjianfei.ticket.activity.PersionActivity;
import com.hjianfei.ticket.activity.RegisterActivity;
import com.hjianfei.ticket.bean.MyUser;

import cn.bmob.v3.BmobUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersionFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout tv_login, tv_reg, tv_psersion, tv_phone_set, tv_save;
    private Context mContext;
    private CardView persion, first;

    public static PersionFragment newInstance(String tag) {
        Bundle args = new Bundle();
        args.putString("PersionFragment", tag);
        PersionFragment fragment = new PersionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_persion, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initView(View view) {
        tv_login = (RelativeLayout) view.findViewById(R.id.tv_login);
        tv_reg = (RelativeLayout) view.findViewById(R.id.tv_reg);
        tv_psersion = (RelativeLayout) view.findViewById(R.id.tv_persion);
        tv_phone_set = (RelativeLayout) view.findViewById(R.id.tv_phone_set);
        tv_save = (RelativeLayout) view.findViewById(R.id.tv_save);
        persion = (CardView) view.findViewById(R.id.persion);
        first = (CardView) view.findViewById(R.id.first);
        tv_login.setOnClickListener(this);
        tv_reg.setOnClickListener(this);
        persion.setOnClickListener(this);
        first.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        System.out.println(userInfo);
        if (userInfo != null) {
            System.out.println(userInfo);
            System.out.println(userInfo.getReal_name());
            persion.setVisibility(View.VISIBLE);
            first.setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_reg:
                Intent intent2 = new Intent(mContext, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.persion:
                Intent intent3 = new Intent(mContext, PersionActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
