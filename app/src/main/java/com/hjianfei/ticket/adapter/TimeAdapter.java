package com.hjianfei.ticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hjianfei.ticket.R;

import java.util.List;

/**
 * Created by HJianFei on 2016-7-16.
 */
public class TimeAdapter extends ArrayAdapter<String> {
    List<String> lists;

    public TimeAdapter(Context context, int resource, List<String> list) {
        super(context, resource, list);
        this.lists = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_time, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.time.setText(lists.get(position));

        return convertView;
    }

    private static final class ViewHolder {
        TextView time;

        public ViewHolder(View v) {
            time = (TextView) v.findViewById(R.id.time);
        }
    }
}
