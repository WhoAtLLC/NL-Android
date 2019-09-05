package com.root.wishlist.presentation.profile;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.NetworkConnection;
import com.root.wishlist.model.profile.PrivateProfileModel;
import com.root.wishlist.R;
import com.root.wishlist.view.PrivateProfileView;

public class PrivateProfilePresentation implements PrivateProfileInterface, PrivateProfileModel.OnPrivateProfileListener {

    PrivateProfileView privateProfileView;
    PrivateProfileModel privateProfileModel;
    Context mContext;
    SharedDatabase sharedDatabase;


    public PrivateProfilePresentation(Context context, PrivateProfileView privateProfileFragment) {
        this.privateProfileView = privateProfileFragment;
        this.mContext = context;
        privateProfileModel = new PrivateProfileModel();
        sharedDatabase = new SharedDatabase(context);
    }

    @Override
    public void setPrivateProfilePicture(String privateProfilePicture) {
        if (privateProfileView != null) {
            privateProfileView.setPrivateProfilePicture(privateProfilePicture);
        }
    }

    @Override
    public void setPrivateTitle(String privateTitle) {
        if (privateProfileView != null) {
            privateProfileView.setPrivateTitle(privateTitle);
        }
    }

    @Override
    public void setPrivateFirstName(String privateFirstName) {
        if (privateProfileView != null) {
            privateProfileView.setPrivateFirstName(privateFirstName);
        }
    }


    @Override
    public void setPrivatetBio(String privatetBio) {
        if (privateProfileView != null) {
            privateProfileView.setPrivatetBio(privatetBio);
        }
    }

    @Override
    public void setPrivateEmail(String privateEmail) {
        if (privateProfileView != null) {
            privateProfileView.setPrivateEmail(privateEmail);
        }
    }

    @Override
    public void setPrivateComapny(String privateComapny) {
        if (privateProfileView != null) {
            privateProfileView.setPrivateComapny(privateComapny);
        }
    }

    @Override
    public void setPrivatePhone(String privatePhone) {
        if (privateProfileView != null) {
            privateProfileView.setPrivatePhone(privatePhone);
        }
    }

    @Override
    public void setPrivateShortBio(String shortBio) {
        if (privateProfileView != null) {
            privateProfileView.setPrivateShortBio(shortBio);
        }
    }

    @Override
    public void setPrivateBusinessDiscussion(String businessDiscussion) {
        if (privateProfileView != null) {
            privateProfileView.setPrivateBusinessDiscussion(businessDiscussion);
        }
    }

    @Override
    public void setPrivateBusinessAdditional(String businessAdditional) {
        if (privateProfileView != null) {
            privateProfileView.setPrivateBusinessAdditional(businessAdditional);
        }


    }

    @Override
    public void setPrivateHandle(String privateHandle) {
        if (privateProfileView != null) {
            privateProfileView.setPrivateHandle(privateHandle);
        }
    }

    @Override
    public void setNetworkMessage(String connection) {
        privateProfileView.checknetwork(connection);
    }

    @Override
    public void getPrivateUserProfile() {

        try {
            if (NetworkConnection.isNetworkAvailable(mContext)) {
                privateProfileModel.privateProfileInfo(mContext, "token " + sharedDatabase.getToken(), this);
            } else {
                privateProfileView.checknetwork(mContext.getString(R.string.networkconnection));
            }
        } catch (Exception e) {
            privateProfileView.checknetwork(mContext.getString(R.string.networkconnection));
        }

    }
}
