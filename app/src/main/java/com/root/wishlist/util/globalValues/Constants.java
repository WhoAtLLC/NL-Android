package com.root.wishlist.util.globalValues;


import android.content.Context;
import android.widget.Toast;

import com.root.wishlist.database.SharedDatabase;

public class Constants {

    private SharedDatabase sharedDatabase;
    private Context context;

    public Constants(Context mContext) {
        context = mContext;
        sharedDatabase = new SharedDatabase(context);

        String string = sharedDatabase.getTargetServer();
        String string1 = sharedDatabase.getTargetServer();
       // Toast.makeText(mContext, string, Toast.LENGTH_SHORT).show();
        if(!sharedDatabase.getTargetServer().equals(""))
            BASE_URL=sharedDatabase.getTargetServer();
       /* else
        {
            if(sharedDatabase.getTargetServer().equals(""))
            {
                BASE_URL="http://wishlist.whoat.net/";
                sharedDatabase.setTargetServer(BASE_URL);
            }

            else
                BASE_URL=sharedDatabase.getTargetServer();
        }*/
    }


    //public static final String BASE_URL = "http://wishlist.operislabs.com:80/";
    public String BASE_URL;
    // public static final String BASE_URL = "https://niceleads.serveo.net/";
     //public static final String BASE_URL = "https://wishlist.operislabs.com/";
   //  public static final String BASE_URL = "https://niceleads-staging.herokuapp.com/";
    // public static final String BASE_URL = "https://niceleads-staging-pr-1.herokuapp.com/";

    public static final String REGISTRATION_STEP1 = "api/v1/register/step/1/";
    public static final String REGISTRATION_STEP2 = "api/v1/register/step/2/";
    public static final String UPLOAD_CONTACTS = "api/v1/contact/";
    public static final String COMPANY_LIST = "api/v1/company/";
    public static final String COMPANY_OF_INTERESTS = "api/v1/profile/interests/";
    public static final String NETWORKS_PROFILE = "api/v1/profile/network/";
    public static final String PRIVACY_POLICY = "api/v1/privacy/";
    public static final String TERMS_SERVICES = "api/v1/terms/";
    public static final String LOGIN = "api/v1/login/";
    public static final String RESET_PASSWORD = "api/v1/password/reset/";
    public static final String USER_STATUS = "api/v1/register/status/";
    public static final String RE_SEND_EMAIL = "api/v1/register/resend/";
    public static final String MATCH_COMPANY = "api/v1/company/match/";
    public static final String FEED_COMPANY = "api/v1/company/feed/";
    public static final String COMPANY_USER_CONNECTIONS = "api/v1/company/{userID}/connections/";
    public static final String COMPANY_MATCH_USER = "api/v1/company/match/{userName}/";
    public static final String COMPANY_MATCH_USER_REVERSE = "api/v1/company/match/{userName}/reverse/";
    public static final String USER_MUTUAL_CONTACTS = "api/v1/contact/{userName}/mutual/";
    public static final String USER_DETAILS_INFORMATIONS = "api/v1/user/{userName}";
    public static final String USERPROFILE = "api/v1/profile/";
    public static final String USER_COMPANIES = "api/v1/user/{userName}/companies";
    public static final String CREATE_NOTIFICATIONS = "api/v1/notification/create/";
    public static final String BUSINESS_NOTIFICATIONS = "api/v1/notification/read/{userId}/";
    public static final String MUTUAL_CONTACTS_NOTIFICATIONS = "api/v1/notification/mutual/{userID}/";
    public static final String NOTIFICATIONS_DETAILS = "api/v1/notification/";
    public static final String NOTIFICATIONS_ACCEPT = "api/v1/notification/accept/{userID}/";
    public static final String NOTIFICATIONS_DELETE = "api/v1/notification/delete/{userID}/";
    public static final String NOTIFICATIONS_ARCHIVE = "api/v1/notification/archive/{userID}/";
    public static final String WL_FACEBOOK = "api/v1/social/facebook/";
    //for database
    public static final String SHAREDDATABASE = "wishlist";


}
