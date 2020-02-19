package com.example.ncpmap.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.ncpmap.R;
import com.example.ncpmap.adapter.SimpleAdapter;
import com.example.ncpmap.bean.NcpDailyCase;
import com.example.ncpmap.util.ToastHelper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NcpDailyCaseFragment extends Fragment {

    private View mRootView;

    private ProgressDialog mWaitDialog;
    private TextView mDiagnosedView,mDiagnosedIncrView,mSuspectView,mSuspectIncrView,
            mDeathView,mDeathIncrView,mCuredView,mCuredIncrView,
            mLocalDiagnoseView,mLocalSuspecview,mLocalDeathView,mLocalCuredView;
    private ListView mProvincesData;

    private NcpDailyCase dailyCase;

    public static final String NCP_DATA_API = "https://tianqiapi.com/api?version=epidemic&appid=53995812&appsecret=lkxt57oe";
    public static final String LOCAL_NCP_DATA_URL = "http://papatiger.xyz/guangdeCase.php";
    public static final String APP_ID = "53995812";
    public static final String APP_SECRET = "lkxt57oe";
    public static final String VERSION = "epidemic";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null){
            mRootView = inflater.inflate(R.layout.fragment_ncp_dailycase,null);
        }

        initView();
        getData();
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    void initView(){
        mDiagnosedView = mRootView.findViewById(R.id.sick_man_num);
        mDiagnosedIncrView = mRootView.findViewById(R.id.add_sick_man);
        mSuspectView = mRootView.findViewById(R.id.seem_man_num);
        mSuspectIncrView = mRootView.findViewById(R.id.add_seem_man);
        mDeathView = mRootView.findViewById(R.id.dead_man_num);
        mDeathIncrView = mRootView.findViewById(R.id.add_dead_man);
        mCuredView = mRootView.findViewById(R.id.cure_man_num);
        mCuredIncrView = mRootView.findViewById(R.id.add_cure_man);
        mProvincesData = mRootView.findViewById(R.id.provinces_data_list);
        mLocalDiagnoseView = mRootView.findViewById(R.id.local_sick_num);
        mLocalSuspecview = mRootView.findViewById(R.id.local_seem_num);
        mLocalDeathView = mRootView.findViewById(R.id.local_dead_num);
        mLocalCuredView = mRootView.findViewById(R.id.local_cure_num);

        mWaitDialog = new ProgressDialog(getContext());
        mWaitDialog.setTitle("正在请求数据");
        mWaitDialog.show();
    }

    void getData(){

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url(NCP_DATA_API)

                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mWaitDialog.dismiss();
                ToastHelper.getInstance(getActivity()).showToast("数据获取失败");
                Log.e("connect_error",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()){
                    //解析数据
                    String data = response.body().string();
                    analyseData(data);
                }else{
                    ToastHelper.getInstance(getActivity()).showToast("数据获取失败");
                    Log.e("response_error","response");
                }
                mWaitDialog.dismiss();
            }
        });

        getLocalData();
    }

    void getLocalData(){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url(LOCAL_NCP_DATA_URL)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String body = response.body().string();
                Log.v("guangde",body);

                JSONObject object = null;

                try{
                    object = (JSONObject) JSONObject.parse(body);
                }catch (JSONException e){
                    Log.v("guangde",e.getMessage());
                }

                final JSONObject finalObject = object;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLocalDiagnoseView.setText(""+ finalObject.getString("mDiagnosed"));
                        mLocalSuspecview.setText(""+ finalObject.getString("mSuspect"));
                        mLocalDeathView.setText(""+ finalObject.getString("mDeath"));
                        mLocalCuredView.setText(""+ finalObject.getString("mCured"));
                    }
                });
            }
        });
    }

    void analyseData(String response){
        Log.v("ncpData",response);
        JSONObject object = (JSONObject)JSONObject.parse(response);
        int errcode = (int)object.get("errcode");

        if (errcode != 0){
            ToastHelper.getInstance(getContext()).showToast("数据获取失败");
            Log.e("code_error","code "+errcode);
            return;
        }

        final JSONObject data = object.getJSONObject("data");

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    dailyCase = NcpDailyCase.makeDailyCase(data);
                    mDiagnosedView.setText(dailyCase.getmDiagnosed()+"");
                    mDiagnosedIncrView.setText(dailyCase.getmDiagnosedIncr()+"");
                    mSuspectView.setText(dailyCase.getmSuspect()+"");
                    mSuspectIncrView.setText(""+dailyCase.getmSuspectIncr());
                    mDeathView.setText(""+dailyCase.getmDeath());
                    mDeathIncrView.setText(""+dailyCase.getmDeathIncr());
                    mCuredView.setText(""+dailyCase.getmCured());
                    mCuredIncrView.setText(""+dailyCase.getmCuredIncr());

                    SimpleAdapter adapter = new SimpleAdapter(getActivity(),dailyCase.getmProvince());
                    mProvincesData.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                    Log.e("json_error",e.getMessage());
                }
            }
        });
    }
}
