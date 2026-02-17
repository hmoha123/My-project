package com.example.androidlabs;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TodoAdapter extends BaseAdapter {

    private final Context context;
    private final List<TodoItem> list;

    public TodoAdapter(Context context, List<TodoItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rowView = inflater.inflate(R.layout.row_todo, parent, false);
        }

        TextView tv = rowView.findViewById(R.id.textViewRow);
        TodoItem item = list.get(position);

        tv.setText(item.getText());

        if (item.isUrgent()) {
            rowView.setBackgroundColor(Color.RED);
            tv.setTextColor(Color.WHITE);
        } else {
            rowView.setBackgroundColor(Color.TRANSPARENT);
            tv.setTextColor(Color.BLACK);
        }

        return rowView;
    }
}
