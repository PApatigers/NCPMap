package com.example.ncpmap.bean;



import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NcpDailyCase {

    //确诊人数
    private int mDiagnosed = 0;

    //疑似人数
    private int mSuspect = 0;

    //死亡人数
    private int mDeath = 0;

    //治愈人数
    private int mCured = 0;

    //新增确诊人数
    private int mDiagnosedIncr = 0;

    //新增疑似人数
    private int mSuspectIncr = 0;

    //新增死亡人数
    private int mDeathIncr = 0;

    //新增治愈人数
    private int mCuredIncr = 0;

    //各省市信息
    private List<String> mProvincesData = new ArrayList<>();
    private List<Map<String ,String>> mProvince = new ArrayList<>();

    public List<Map<String, String>> getmProvince() {
        return mProvince;
    }

    public void setmProvince(List<Map<String, String>> mProvince) {
        this.mProvince = mProvince;
    }

    //时间
    private String date = null;

    public int getmDiagnosed() {
        return mDiagnosed;
    }

    public void setmDiagnosed(int mDiagnosed) {
        this.mDiagnosed = mDiagnosed;
    }

    public int getmSuspect() {
        return mSuspect;
    }

    public void setmSuspect(int mSuspect) {
        this.mSuspect = mSuspect;
    }

    public int getmDeath() {
        return mDeath;
    }

    public void setmDeath(int mDeath) {
        this.mDeath = mDeath;
    }

    public int getmCured() {
        return mCured;
    }

    public void setmCured(int mCured) {
        this.mCured = mCured;
    }

    public int getmDiagnosedIncr() {
        return mDiagnosedIncr;
    }

    public void setmDiagnosedIncr(int mDiagnosedIncr) {
        this.mDiagnosedIncr = mDiagnosedIncr;
    }

    public int getmSuspectIncr() {
        return mSuspectIncr;
    }

    public void setmSuspectIncr(int mSuspectIncr) {
        this.mSuspectIncr = mSuspectIncr;
    }

    public int getmDeathIncr() {
        return mDeathIncr;
    }

    public void setmDeathIncr(int mDeathIncr) {
        this.mDeathIncr = mDeathIncr;
    }

    public int getmCuredIncr() {
        return mCuredIncr;
    }

    public void setmCuredIncr(int mCuredIncr) {
        this.mCuredIncr = mCuredIncr;
    }

    public List<String> getmProvincesData() {
        return mProvincesData;
    }

    public void setmProvincesData(List<String> mProvincesData) {
        this.mProvincesData = mProvincesData;
    }

    public static NcpDailyCase makeDailyCase(JSONObject data) throws JSONException {

        NcpDailyCase dailyCase = new NcpDailyCase();
        dailyCase.setmDiagnosed(data.getIntValue("diagnosed"));
        dailyCase.setmDiagnosedIncr(data.getIntValue("diagnosedIncr"));
        dailyCase.setmSuspect(data.getIntValue("suspect"));
        dailyCase.setmSuspectIncr(data.getIntValue("suspectIncr"));
        dailyCase.setmDeath((data.getIntValue("death")));
        dailyCase.setmDeathIncr(data.getIntValue("deathIncr"));
        dailyCase.setmCured((data.getIntValue("cured")));
        dailyCase.setmCuredIncr(data.getIntValue("curedIncr"));

        JSONArray list = data.getJSONArray("list");
        for(int i=0;i<list.size();i++){
            dailyCase.getmProvincesData().add(list.getString(i));
        }

        splitData(dailyCase);
        return dailyCase;
    }

    //拆分各省市具体信息
    static  void splitData(NcpDailyCase dailyCase){
        for(int i=0;i<dailyCase.mProvincesData.size();i++){

            String data = dailyCase.mProvincesData.get(i);
            String[] tmp = data.split(" ");

            if (tmp.length < 0){
                continue;
            }

            String mProvinceName = tmp[0];
            String mProvinceCase = data.substring(mProvinceName.length(),data.length());

            Log.v("province_map",mProvinceName + "  " + mProvinceCase);
            Map<String,String> mProData = new HashMap<>();
            mProData.put("province",mProvinceName);
            mProData.put("data",mProvinceCase);
            dailyCase.mProvince.add(mProData);
        }
    }
}
