package com.example.ncpmap.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ncpmap.R;

import java.util.List;
import java.util.Map;

public class SimpleAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String,String>> mData;

    public SimpleAdapter(Context context, List<Map<String,String>> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ncp_provinces,null);
            holder = new Holder();
            holder.mProvince = convertView.findViewById(R.id.provinces);
            holder.mData = convertView.findViewById(R.id.provinces_data);
            holder.mPositionIcon = convertView.findViewById(R.id.position_icon);
            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }

        holder.mProvince.setText(mData.get(position).get("province"));
        holder.mData.setText(mData.get(position).get("data"));

        switch (position+1){
            case 1:
                holder.mPositionIcon.setImageDrawable(context.getDrawable(R.drawable.ic_num_one));
                break;
            case 2:
                holder.mPositionIcon.setImageDrawable(context.getDrawable(R.drawable.ic_num_two));
                break;
            case 3:
                holder.mPositionIcon.setImageDrawable(context.getDrawable(R.drawable.ic_num_three));
                break;
            default:
                holder.mPositionIcon.setVisibility(View.INVISIBLE);
                break;
        }
        return convertView;
    }

    class Holder{
        TextView mProvince;
        TextView mData;
        ImageView mPositionIcon;
    }
}


