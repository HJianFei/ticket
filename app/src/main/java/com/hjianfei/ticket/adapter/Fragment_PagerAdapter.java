package com.hjianfei.ticket.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hjianfei.ticket.R;

import java.util.List;

/**
 * Created by HJianFei on 2016-7-13.
 */
public class Fragment_PagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private String tabTitles[] = new String[]{"车票预定", "订单管理", "我的12306"};
    private Context context;

    public Fragment_PagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragmentList = fragments;
    }

    public View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.textView);
        tv.setText(tabTitles[position]);
//        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        //img.setImageResource(imageResId[position]);
        return v;
    }


    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
