package com.root.wishlist.presentation.profile;

import android.content.Context;

import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.model.profile.PrivacyPolicyModel;
import com.root.wishlist.view.PrivacyPolicyView;


public class PrivacyPolicyPresentation implements PrivacyPolicyInterface, PrivacyPolicyModel.OnPrivacyPolicyOnListener {


    PrivacyPolicyView privacyPolicyView;
    PrivacyPolicyModel privacyPolicyModel;
    Context mContext;
    SharedDatabase sharedDatabase;


    public PrivacyPolicyPresentation(Context context, PrivacyPolicyView privacyPolicyView) {
        this.privacyPolicyView = privacyPolicyView;
        this.mContext = context;
        privacyPolicyModel = new PrivacyPolicyModel();
        sharedDatabase = new SharedDatabase(context);

    }


    @Override
    public void setId(int id) {
        if (privacyPolicyView != null) {
            privacyPolicyView.setId(id);
        }

    }

    @Override
    public void setSlug(String slug) {
        if (privacyPolicyView != null) {
            privacyPolicyView.setSlug(slug);
        }
    }

    @Override
    public void setDateCreated(String dateCreated) {
        if (privacyPolicyView != null) {
            privacyPolicyView.setDateCreated(dateCreated);
        }
    }

    @Override
    public void setDateChanged(String dateChanged) {
        if (privacyPolicyView != null) {
            privacyPolicyView.setDateChanged(dateChanged);
        }
    }

    @Override
    public void setGraphId(Object graphId) {
        if (privacyPolicyView != null) {
            privacyPolicyView.setGraphId(graphId);
        }
    }

    @Override
    public void setLabel(String label) {
        if (privacyPolicyView != null) {
            privacyPolicyView.setLabel(label);
        }
    }

    @Override
    public void setContent(String content) {
        if (privacyPolicyView != null) {
            privacyPolicyView.setContent(content);
        }
    }

    @Override
    public void getPrivacyPolicy() {

        privacyPolicyModel.privacyPolicyModel(mContext,this);
    }

    @Override
    public void getTerm() {
        privacyPolicyModel.termandConditionModel(mContext,this);
    }
}
