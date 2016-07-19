package com.hjianfei.ticket.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.hjianfei.ticket.R;
import com.hjianfei.ticket.adapter.Fragment_PagerAdapter;
import com.hjianfei.ticket.fragment.OrderFragment;
import com.hjianfei.ticket.fragment.PersionFragment;
import com.hjianfei.ticket.fragment.TicketFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private Fragment_PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ToolBar的设定
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //fragment的实例化
        fragments = new ArrayList<Fragment>();
        TicketFragment ticketFragment = TicketFragment.newInstance("车票预定");
        OrderFragment orderFragment = OrderFragment.newInstance("订单管理");
        PersionFragment persionFragment = PersionFragment.newInstance("我的12306");
        fragments.add(ticketFragment);
        fragments.add(orderFragment);
        fragments.add(persionFragment);
        pagerAdapter = new Fragment_PagerAdapter(getSupportFragmentManager(), this, fragments);
        //设置fragment的设配器
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(pagerAdapter.getTabView(i));
            }
        }

        tabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }

    }

}
