package com.example.ncpmap.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.FunctionHolder> {


    private List<Map<String,String>> mFunction;

    @Override
    public FunctionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FunctionHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class FunctionHolder extends RecyclerView.ViewHolder{


        public FunctionHolder(View itemView) {
            super(itemView);
        }
    }
}
