package com.hjianfei.ticket.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.hjianfei.ticket.R;
import com.hjianfei.ticket.activity.TicketBuyActivity;
import com.hjianfei.ticket.bean.TicketMoney;
import com.hjianfei.ticket.bean.Trains;
import com.labo.kaji.swipeawaydialog.SwipeAwayDialogFragment;

/**
 * Created by HJianFei on 2016-7-17.
 */
public class MyDialogFragment extends SwipeAwayDialogFragment {
    private Context mContext;
    private Trains.ResultBean resultBean;
    private TicketMoney.ResultMoney resultMoney;
    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        resultBean = (Trains.ResultBean) bundle.get("resultBean");
        resultMoney = (TicketMoney.ResultMoney) bundle.get("resultMoney");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("车次: " + resultBean.getTrain_no() + " 详情信息:");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);
        builder.setItems(new String[]{
                "车席 ",
                "高级软卧  余票: " + resultBean.getGr_num() + "  票价: " + resultMoney.getGr(),
                "其他 余票: " + resultBean.getQt_num() + "  票价: " + resultMoney.getQt(),
                "软卧 余票: " + resultBean.getRw_num() + "  票价: " + resultMoney.getRw(),
                "软座 余票: " + resultBean.getRz_num() + "  票价: " + resultMoney.getRz(),
                "特等座 余票: " + resultBean.getTz_num() + "  票价: " + resultMoney.getTz(),
                "无座 余票: " + resultBean.getWz_num() + "  票价: " + resultMoney.getWz(),
                "硬卧 余票: " + resultBean.getYw_num() + "  票价: " + resultMoney.getYw(),
                "硬座 余票: " + resultBean.getYz_num() + "  票价: " + resultMoney.getYz(),
                "二等座 余票: " + resultBean.getZe_num() + "  票价: " + resultMoney.getZe(),
                "一等座 余票: " + resultBean.getZy_num() + "  票价: " + resultMoney.getZy(),
                "商务座 余票: " + resultBean.getSwz_num() + " 票价: " + resultMoney.getSwz(),
        }, null);
        builder.setPositiveButton("立即购票", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(mContext, TicketBuyActivity.class);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
        return builder.create();
    }
}
