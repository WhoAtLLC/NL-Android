package com.root.wishlist.interfaces;

import com.root.wishlist.pojo.EmailStatus;
import com.root.wishlist.pojo.FbResult;
import com.root.wishlist.pojo.MyWishListInterest;
import com.root.wishlist.pojo.Reasoncompany;
import com.root.wishlist.pojo.SelectnetworkBean;
import com.root.wishlist.pojo.leads.LeadsCompanyList;
import com.root.wishlist.pojo.leads.MemberLeads;
import com.root.wishlist.pojo.leads.YourConnectionAtbean;
import com.root.wishlist.pojo.leads.getintroduce.IntoMatualContacts;
import com.root.wishlist.pojo.leads.getintroduce.IntroAllCompany;
import com.root.wishlist.pojo.leads.getintroduce.RequesttomeetBean;
import com.root.wishlist.pojo.leads.getintroduce.TheirInterestBean;
import com.root.wishlist.pojo.leads.getintroduce.UserDetails;
import com.root.wishlist.pojo.members.MutualContactListBeans;
import com.root.wishlist.pojo.members.MyWishlistFragmentBean;
import com.root.wishlist.pojo.mywishlist.Company;
import com.root.wishlist.pojo.notification.NotificationIOA;
import com.root.wishlist.pojo.profile.PrivacyPolicyBean;
import com.root.wishlist.pojo.profile.PrivateProfileBean;
import com.root.wishlist.pojo.registration.ForgetpasswordBean;
import com.root.wishlist.pojo.registration.Loginbean;
import com.root.wishlist.pojo.registration.UploadContact;
import com.root.wishlist.pojo.search.SearchActivity;
import com.root.wishlist.pojo.wantto.NotificationWantTo;
import com.root.wishlist.pojo.wantto.UserProfileNotification;
import com.root.wishlist.pojo.wantto.WantToMutualContacts;
import com.root.wishlist.util.RegistrationBean;
import com.root.wishlist.util.globalValues.Constants;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface WishListApiProcess {

    @FormUrlEncoded
    @POST(Constants.REGISTRATION_STEP1)
    Call<RegistrationBean> registrationStep1(@Field("email") String email, @Field("password") String password);


    @Multipart
    @POST(Constants.REGISTRATION_STEP2)
    Call<RegistrationBean> registrationStep2(@Header("Authorization") String authorization, @Part("first_name") String firstname,
                                             @Part("last_name") String lastname, @Part("handle") String nickname, @Part MultipartBody.Part part);

    @Headers("Content-Type: application/json")
    @POST(Constants.UPLOAD_CONTACTS)
    Call<UploadContact> uploadContact(@Header("Authorization") String authorization, @Body HashMap<String, Object> stringObjectHashMap);

    @GET(Constants.COMPANY_LIST)
    Call<Company> getCompanyDetails(@Header("Authorization") String authorization, @Query("page") Integer page);

    @GET(Constants.PRIVACY_POLICY)
    Call<PrivacyPolicyBean> getPrivacyPolicybean();

    @GET(Constants.TERMS_SERVICES)
    Call<PrivacyPolicyBean> getTermAndCondition();

    @FormUrlEncoded
    @POST(Constants.LOGIN)
    Call<Loginbean> userLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constants.RESET_PASSWORD)
    Call<ForgetpasswordBean> forgetPassword(@Field("email") String email);

    @Headers("Content-Type: application/json")
    @POST(Constants.COMPANY_OF_INTERESTS)
    Call<ResponseBody> companiesOfinterest(@Header("Authorization") String authorization, @Body HashMap<String, Object> stringObjectHashMap);

    @FormUrlEncoded
    @POST(Constants.NETWORKS_PROFILE)
    Call<SelectnetworkBean> userNetwork(@Header("Authorization") String authorization, @Field("network_status") String network_status);

    @GET(Constants.NETWORKS_PROFILE)
    Call<SelectnetworkBean> selectednetwork(@Header("Authorization") String authorization);

    @GET("api/v1/profile/wishlist/")
    Call<LeadsCompanyList> getWishList(@Header("Authorization") String authorization, @Query("page") Integer page);


    @GET(Constants.MATCH_COMPANY)
    Call<LeadsCompanyList> getMembersWishlist(@Header("Authorization") String authorization);


    @GET(Constants.FEED_COMPANY)
    Call<MemberLeads> getLeadsCompanyList(@Header("Authorization") String authorization, @Query("page") Integer page);

    @GET(Constants.COMPANY_USER_CONNECTIONS)
    Call<YourConnectionAtbean> getYourConnectionAtList(@Header("Authorization") String authorization, @Path("userID") Integer userID, @Query("page") int pageNumber);

    /*Members api*/
    @GET(Constants.COMPANY_MATCH_USER)
    Call<MyWishlistFragmentBean> getMembersMyWishListFragment(@Header("Authorization") String authorization, @Path("userName") String userName);

    @GET(Constants.COMPANY_MATCH_USER_REVERSE)
    Call<MyWishlistFragmentBean> getTheirMemberFragment(@Header("Authorization") String authorization, @Path("userName") String userName);

    @GET(Constants.USER_MUTUAL_CONTACTS)
    Call<MutualContactListBeans> mutualContactMemberFragment(@Header("Authorization") String authorization, @Path("userName") String userName, @Query("page") Integer page);

    @GET(Constants.USER_MUTUAL_CONTACTS)
    Call<IntoMatualContacts> getIntroMutualContact(@Header("Authorization") String authorization, @Path("userName") String userName, @Query("page") Integer page);

    @GET(Constants.USER_DETAILS_INFORMATIONS)
    Call<TheirInterestBean> getTheirInterest(@Header("Authorization") String authorization, @Path("userName") String userName);

    @GET(Constants.USER_COMPANIES)
    Call<IntroAllCompany> getIntroCompany(@Header("Authorization") String authorization, @Path("userName") String userName, @Query("page") Integer page);

    @GET(Constants.USERPROFILE)
    Call<Reasoncompany> getReasoncompanyCall(@Header("Authorization") String authorization);

    //for email verification
    @GET(Constants.USER_STATUS)
    Call<EmailStatus> getUserStatus(@Header("Authorization") String token);

    @GET(Constants.RE_SEND_EMAIL)
    Call<EmailStatus> reSendEmail(@Header("Authorization") String token);
    ///wnatto notification

    @POST(Constants.BUSINESS_NOTIFICATIONS)
    Call<NotificationWantTo> getNotificationBusiness(@Header("Authorization") String token, @Path("userId") Integer userid);


    @GET(Constants.MUTUAL_CONTACTS_NOTIFICATIONS)
    Call<WantToMutualContacts> getNotificationMutualContacts(@Header("Authorization") String token, @Path("userID") int userid, @Query("page") Integer page);

    @GET(Constants.USER_DETAILS_INFORMATIONS)
    Call<UserProfileNotification> getUserProfile(@Header("Authorization") String token, @Path("userName") String userName);
//======================myaccount=====================//

    @GET(Constants.USERPROFILE)
    Call<PrivateProfileBean> getPrivateProfileFragment(@Header("Authorization") String authorization);

    @Multipart
    @POST(Constants.USERPROFILE)
    Call<PrivateProfileBean> getPrivateProfileEdit(@Header("Authorization") String authorization, @Part MultipartBody.Part file, @PartMap() Map<String, RequestBody> partMap);


    //=====================getNotification=======================================//////
    @GET(Constants.NOTIFICATIONS_DETAILS)
    Call<NotificationIOA> getNotificationIOA(@Header("Authorization") String authorizaton);
    //operation

    @Headers("Content-Type: application/json")
    @POST(Constants.NOTIFICATIONS_ACCEPT)
    Call<NotificationWantTo> getAccepted(@Header("Authorization") String authorizaton, @Path("userID") Integer userId, @Body HashMap<String, Object> stringObjectHashMap);

    @Headers("Content-Type: application/json")
    @POST(Constants.NOTIFICATIONS_DELETE)
    Call<NotificationWantTo> getdeleted(@Header("Authorization") String authorizaton, @Path("userID") Integer userId, @Body HashMap<String, Object> stringObjectHashMap);

    @Headers("Content-Type: application/json")
    @POST(Constants.NOTIFICATIONS_ARCHIVE)
    Call<NotificationWantTo> getArchive(@Header("Authorization") String authorizaton, @Path("userID") Integer userId, @Body HashMap<String, Object> stringObjectHashMap);


    @GET(Constants.USER_DETAILS_INFORMATIONS)
    Call<UserDetails> getUserDetails(@Header("Authorization") String authorization, @Path("userName") String userName);

    @Headers("Content-Type: application/json")
    @POST(Constants.CREATE_NOTIFICATIONS)
    Call<RequesttomeetBean> sendReason(@Header("Authorization") String token, @Body HashMap<String, Object> stringObjectHashMap);

    //for Search
    @GET(Constants.FEED_COMPANY)
    Call<SearchActivity> SEARCH_ACTIVITY_CALL(@Header("Authorization") String authorizaton, @Query("search") CharSequence search);
//company of interest

    @GET(Constants.COMPANY_OF_INTERESTS)
    Call<MyWishListInterest> MY_WISH_LIST_INTEREST_CALL(@Header("Authorization") String authorization);

    @Headers("Content-Type: application/json")
    @PUT(Constants.COMPANY_OF_INTERESTS)
    Call<ResponseBody> RESPONSE_BODY_CALL(@Header("Authorization") String authorization, @Body HashMap<String, Object> stringObjectHashMap);
    //for fb

    @Headers("Content-Type: application/json")
    @POST(Constants.WL_FACEBOOK)
    Call<FbResult> FB_RESULT_CALL(@Body HashMap<String, String> stringHashMap);

}

