package com.example.lanxumit.testapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lanxumit.testapplication.R;
import com.example.lanxumit.testapplication.entity.TestEntity_1;

import java.util.List;

public class TestLearnAdapter extends ArrayAdapter<TestEntity_1> {
    private int mResource;

    public TestLearnAdapter(Context context, int resource, List<TestEntity_1> objects) {
        super(context, resource, objects);
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //by position get entity
        TestEntity_1 item = getItem(position);
        View inflate;
        ViewHolder mViewHolder;
        if (convertView == null) {
            inflate = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
            mViewHolder = new ViewHolder();
            mViewHolder.itemTv = inflate.findViewById(R.id.item_basic_lv_tv);
            mViewHolder.itemIv = inflate.findViewById(R.id.item_basic_lv_img);
            inflate.setTag(mViewHolder);
        } else {
            inflate = convertView;
            mViewHolder = (ViewHolder) inflate.getTag();
        }
        mViewHolder.itemTv.setText(item.getfName());
        mViewHolder.itemIv.setImageResource(item.getfImageView());

        Intent intent = new Intent();
        getContext().sendBroadcast(intent);
        return inflate;
    }

    class ViewHolder {
        private TextView itemTv;
        private ImageView itemIv;

    }
}
