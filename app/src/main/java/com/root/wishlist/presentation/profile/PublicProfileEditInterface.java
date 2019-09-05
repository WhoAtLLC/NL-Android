package com.root.wishlist.presentation.profile;


import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface PublicProfileEditInterface
{
    void editProfile(MultipartBody.Part imageFileBody, HashMap<String, RequestBody> map);


}
