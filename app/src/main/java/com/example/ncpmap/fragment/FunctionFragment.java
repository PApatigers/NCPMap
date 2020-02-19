package com.example.ncpmap.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ncpmap.Constants;
import com.example.ncpmap.R;
import com.example.ncpmap.StepNavigator;
import com.example.ncpmap.util.ToastHelper;

public class FunctionFragment extends Fragment {

    private View mRootView;
    private ViewGroup mFunctionGlobalView,mFunctionLocalView,mFunctionGameView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null){
            mRootView = inflater.inflate(R.layout.fragment_function,null);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initAction();
    }

    void initView(){
        mFunctionGlobalView = mRootView.findViewById(R.id.function_global);
        mFunctionLocalView = mRootView.findViewById(R.id.function_local);
        mFunctionGameView = mRootView.findViewById(R.id.function_game);
    }

    void initAction(){

        mFunctionGlobalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StepNavigator.goToWebActivity(getActivity(),Constants.TECENT_NCP);
            }
        });

        mFunctionLocalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StepNavigator.goToWebActivity(getActivity(),Constants.AROUND_NCP);
            }
        });

        mFunctionGameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //todo 别踩白块游戏
                StepNavigator.goToWebActivity(getActivity(),Constants.BW_GAME);
                //ToastHelper.getInstance(getActivity()).showToast("暂不开放");
            }
        });
    }
}
