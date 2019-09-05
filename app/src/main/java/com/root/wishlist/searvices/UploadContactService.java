package com.root.wishlist.searvices;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;

import com.google.gson.Gson;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.interfaces.UpdateMessage;
import com.root.wishlist.interfaces.WishListApiProcess;
import com.root.wishlist.pojo.UploadContactBean;
import com.root.wishlist.pojo.registration.ContactMessage;
import com.root.wishlist.pojo.registration.UploadContact;
import com.root.wishlist.searvices.uploadContact.UserCompany;
import com.root.wishlist.searvices.uploadContact.UserEmail;
import com.root.wishlist.searvices.uploadContact.UserJobTitle;
import com.root.wishlist.searvices.uploadContact.UserName;
import com.root.wishlist.util.RetrofitSingletonClass;
import com.root.wishlist.util.globalValues.RetrofitClass;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;

//import org.codehaus.jackson.map.ObjectMapper;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadContactService {
    SharedDatabase sharedDatabase;
    int totalContact = 0;
    Context context;
    List email;
    int contact_id;
    ArrayList<UploadContactBean> userBeen = new ArrayList<>();
    HashMap<String, Object> uploadContact = new HashMap<>();
    WishlistProgressDialog wishlistProgressDialog;
    String oldName = "";
    private String name, companyName, jobTitle, firstname, lastName, deviceName, udid;
    private String whoseCalling;

    public UploadContactService(Context context, String whoseCalling) {
        this.context = context;
        this.whoseCalling = whoseCalling;
        sharedDatabase = new SharedDatabase(context);
        wishlistProgressDialog = new WishlistProgressDialog(context);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                wishlistProgressDialog.dialogShow();
                getcontact();
                // emailreading();
                deviceName = getDevicename();
                udid = getDeviceUDID();
                uploadContact.put("contacts", userBeen);
                uploadContact.put("deviceName", deviceName);
                uploadContact.put("udid", udid);
               /* ObjectMapper mapper = new ObjectMapper();                                         //JACKSON MAPPER LIBRARY
                try {
                    String json = mapper.writeValueAsString(uploadContact);
                    Log.d("json", json);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                String token = "token " + sharedDatabase.getToken();
                contactUpload(uploadContact, token);
            }
        });
    }

    public void getcontact() {
        List<HashMap<String, String>> phonedetails = null;
        String nameofuser = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        int UserId = 0;
        HashMap<String, String> typeHome = null;
        HashMap<String, String> typeMobile = null;
        HashMap<String, String> typeWork = null;
        ContentResolver contentResolver = context.getContentResolver();
        Cursor phones = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, nameofuser);
        while (phones.moveToNext()) {
            if (userBeen.size() > 10)
                break;
            else {
                name = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String name = phones
                        .getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //  String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if (oldName.equals(name)) {

                } else {
                    oldName = name;
                    typeHome = null;
                    typeMobile = null;
                    typeWork = null;

                }
                UserId = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
                email = new UserEmail(context).getEmail(UserId);
                if (Integer.parseInt(phones.getString(phones.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone._ID + " = ?",
                            new String[]{String.valueOf(UserId)}, null);
                    while (pCur.moveToNext()) {
                        //for change
                        if (userBeen.size() >= 10)
                            break;
                        else {
                            Log.d("userBeen", String.valueOf(userBeen.size()));

                            String num = "";
                            Phonenumber.PhoneNumber swissNumberProto = null;
                            String number1 = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            contact_id = pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
                            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                            try {
                                swissNumberProto = phoneUtil.parse(number1, "IN");
                                num = swissNumberProto.toString();
                                num = num.substring(num.lastIndexOf("Number") + 2);
                                num = num.substring(num.lastIndexOf(":") + 1);
                                // String vall = phoneUtil.format(swissNumberProto, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
                                System.out.println(num);
                            } catch (NumberParseException e) {
                                System.err.println("NumberParseException was thrown: " + e.toString());
                            }
                            int type = pCur.getInt(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            switch (type) {
                                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                    typeHome = new HashMap<>();
                                    typeHome.put("label", "HOME");
                                    typeHome.put("primary", "0");
                                    typeHome.put("value", num);
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                    typeMobile = new HashMap<>();
                                    typeMobile.put("label", "MOBILE");
                                    typeMobile.put("primary", "0");
                                    typeMobile.put("value", num);
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                    typeWork = new HashMap<>();
                                    typeWork.put("label", "WORK");
                                    typeWork.put("primary", "0");
                                    typeWork.put("value", num);
                                    break;

                            }

                        }
                        phonedetails = new ArrayList<>();
                        if (typeHome != null) {
                            phonedetails.add(typeHome);
                        }
                        if (typeMobile != null) {
                            phonedetails.add(typeMobile);
                        }
                        /*if (typeWork != null) {
                            phonedetails.add(typeWork);
                        }*/


                    }
                    pCur.close();

                }
                companyName = new UserCompany(context).getCompanyName(UserId);
                jobTitle = new UserJobTitle(context).getJobTitle(UserId);

                firstname = new UserName(context).getFirstName(UserId);
                lastName = new UserName(context).getLastName(UserId);
                totalContact++;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                System.out.println(dateFormat.format(date));
                userBeen.add(new UploadContactBean(companyName, dateFormat.format(date), email, firstname, name, contact_id, name, lastName, "", dateFormat.format(date), "", phonedetails));
            }
        }
        phones.close();
    }

    public String getDevicename() {
        String devicename = android.os.Build.MODEL;
        return devicename;
    }

    public String getDeviceUDID() {
        String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }

    public void contactUpload(HashMap<String, Object> uploadContact, String token) {
        RetrofitSingletonClass retrofitSingletonClass = RetrofitSingletonClass.getInstance(context);
        RetrofitClass retrofitClass = RetrofitSingletonClass.getRetrofitClassSingleInstance();

        WishListApiProcess raterofitInterface = retrofitClass.getClient().create(WishListApiProcess.class);
        Call<UploadContact> userCall = raterofitInterface.uploadContact(token, uploadContact);
        try {
            userCall.enqueue(new Callback<UploadContact>() {
                @Override
                public void onResponse(Call<UploadContact> call, Response<UploadContact> response) {
                    String message = null;
                    wishlistProgressDialog.dismissDialog();
                    int errorcode = response.code();
                    if (errorcode == 201) {
                        ContactMessage contact = response.body().getContact();
                        message = contact.getResult();
                        Log.d("message", message);
                        if (whoseCalling.equals("syncContacts"))
                            sharedDatabase.addStep("companies");
                    } else {
                        message = "We've encountered a technical error.our team is working on it. please try again later";
                    }
                    UpdateMessage updateMessage = (UpdateMessage) context;
                    updateMessage.loginStatus(message);
                }

                @Override
                public void onFailure(Call<UploadContact> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
