package com.root.wishlist.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.root.wishlist.util.globalValues.Constants;
import com.root.wishlist.pojo.leads.MemberResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedDatabase {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Context context;
    private String KEY_VERSION = "version";
    private String KEY_TARGETSERVER = "targetServer";
    private String KEY_TARGETSERVERPOSITION = "targetServerPosition";
    private String KEY_PREVIOUSTARGETSERVER = "previousTargetServer";

    public SharedDatabase(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.SHAREDDATABASE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void userEmail(String email) {
        editor.putString("email", email);
        editor.commit();
    }

    public String getEmail() {
        String userEmail = sharedPreferences.getString("email", "");
        return userEmail;
    }

    public void userToken(String token) {
        editor.putString("token", token);
        editor.commit();
    }

    public String getToken() {
        String token = sharedPreferences.getString("token", "");
        return token;
    }

    public void userId(String userId) {
        editor.putString("userId", userId);
        editor.commit();
    }

    public void nextPage(int pageNumber) {
        editor.putInt("pageNumber", pageNumber);
        editor.commit();
    }

    public Integer getPageNumber() {
        Integer pageNumber = sharedPreferences.getInt("pageNumber", 1);
        return pageNumber;
    }

    public void addStep(String step) {
        editor.putString("step", step);
        editor.commit();
    }

    public String getStep() {
        String step = sharedPreferences.getString("step", null);
        if (step != null) {
            return step;
        } else {
            return "";
        }
    }

    //for mybusiness
    public void setMyBusinessMessage(CharSequence charsequence) {
        editor.putString("discuss", String.valueOf(charsequence));
        editor.commit();
    }

    public String getMyBusinessMessage() {
        String step = sharedPreferences.getString("discuss", null);
        return step;
    }

    public void myBusinessAdditional(CharSequence charsequence) {
        editor.putString("additional", String.valueOf(charsequence));
        editor.commit();
    }

    public String myBusinessAdditional() {
        String step = sharedPreferences.getString("additional", null);
        return step;
    }

    //reason

    public void setReasonHelp(CharSequence charsequence) {
        editor.putString("additional", String.valueOf(charsequence));
        editor.commit();
    }

    public String getReasonHelp() {
        String step = sharedPreferences.getString("additional", null);
        if (step != null)
            return step;
        else
            return "";
    }


    //for coachmark
    public void myBusiness(boolean coach_value) {
        editor.putBoolean("business_value", coach_value);
        editor.commit();
    }

    public boolean getMybusinessCoach() {
        boolean coach_value = sharedPreferences.getBoolean("business_value", false);
        return coach_value;
    }

    public void setCompanyCoach(boolean coach_value) {
        editor.putBoolean("companylist", coach_value);
        editor.commit();
    }

    public boolean getCompanylistCoach() {
        boolean coach_value = sharedPreferences.getBoolean("companylist", false);
        return coach_value;
    }

    public void totalCompanylist(boolean coach_value) {
        editor.putBoolean("totalCompanylist", coach_value);
        editor.commit();
    }

    public boolean gettotalCompanylist() {
        boolean coach_value = sharedPreferences.getBoolean("totalCompanylist", false);
        return coach_value;
    }

    public void setYourConnectionAt(boolean yourconnectionat) {
        editor.putBoolean("yourconnectionat", yourconnectionat);
        editor.commit();
    }

    public boolean getYourConnectionAt() {
        boolean coach_value = sharedPreferences.getBoolean("yourconnectionat", false);
        return coach_value;
    }

    public void setNotification_page(boolean coach_value) {
        editor.putBoolean("coach_value", coach_value);
        editor.commit();
    }

    public boolean getnotification() {
        boolean coach_value = sharedPreferences.getBoolean("coach_value", false);
        return coach_value;
    }

    public void setMyintro(boolean myinterorequest) {
        editor.putBoolean("myinterorequest", myinterorequest);
        editor.commit();
    }

    public boolean getMyintro() {
        boolean myinterorequest = sharedPreferences.getBoolean("myinterorequest", false);
        return myinterorequest;
    }

    public void setLoginwith(String loginwith) {
        editor.putString("loginwith", loginwith);
        editor.commit();
    }

    public String getLoginwith() {
        String loginWith = sharedPreferences.getString("loginwith", "");
        return loginWith;
    }

    public void setLeadsCompany(boolean myinterorequest) {
        editor.putBoolean("leadscompany", myinterorequest);
        editor.commit();
    }

    public boolean getLeadsCompany() {
        boolean myinterorequest = sharedPreferences.getBoolean("leadscompany", false);
        return myinterorequest;
    }


    public void addFlag(boolean isflag) {
        editor.putBoolean("isflag", isflag);
        editor.commit();
    }

    public boolean getIsFlag() {
        boolean isflag = sharedPreferences.getBoolean("isflag", false);
        return isflag;
    }

    //for network
    public void setNetwork(String network) {
        editor.putString("network", network);
        editor.commit();
    }

    public String getNetwork() {
        String isflag = sharedPreferences.getString("network", "open");
        return isflag;
    }

    public void saveLeadsCompany(List<MemberResult> callLog) {
        Gson gson = new Gson();
        String json = gson.toJson(callLog);
        editor.putString("leadsCompany", json);
        editor.commit();
    }

    public List<MemberResult> loadLeadsCompany() {
        List<MemberResult> callLog = new ArrayList<MemberResult>();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("leadsCompany", "");
        if (json.isEmpty()) {
            callLog = new ArrayList<MemberResult>();
        } else {
            Type type = new TypeToken<List<MemberResult>>() {
            }.getType();
            callLog = gson.fromJson(json, type);
        }
        return callLog;
    }

    public void setNextpage(int nextPage) {
        editor.putInt("nextPage", nextPage);
        editor.commit();
    }

    public int getNextPage() {
        int nextPage = sharedPreferences.getInt("nextPage", 1);
        return nextPage;
    }

    public String getVersion() {
        return sharedPreferences.getString(this.KEY_VERSION, "");
    }

    public void setVersion(String version) {
        editor.putString(this.KEY_VERSION, version);
        editor.commit();
    }

    public String getTargetServer() {
        return sharedPreferences.getString(this.KEY_TARGETSERVER, "https://wishlist.whoat.net/");
    }

    public void setTargetServer(String targetServer) {
        editor.putString(this.KEY_TARGETSERVER, targetServer);
        editor.commit();
    }

    public int getTargetServerPosition() {
        return sharedPreferences.getInt(this.KEY_TARGETSERVERPOSITION,1);
    }

    public void setTargetServerPosition(int targetServerPosition) {
        editor.putInt(this.KEY_TARGETSERVERPOSITION, targetServerPosition);
        editor.commit();
    }

    public String getPreviousTargetServer() {
        return sharedPreferences.getString(this.KEY_PREVIOUSTARGETSERVER, "");
    }

    public void setPreviousTargetServer(String previousTargetServer) {
        editor.putString(this.KEY_PREVIOUSTARGETSERVER, previousTargetServer);
        editor.commit();
    }
}
