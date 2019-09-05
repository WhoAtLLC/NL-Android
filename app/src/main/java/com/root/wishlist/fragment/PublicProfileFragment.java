package com.root.wishlist.fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.util.globalValues.Constants;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.presentation.profile.PrivateProfileInterface;
import com.root.wishlist.presentation.profile.PrivateProfilePresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.PrivateProfileView;
import com.root.wishlist.interfaces.NetworkInterface;

import de.hdodenhof.circleimageview.CircleImageView;


public class PublicProfileFragment extends Fragment implements PrivateProfileView {

    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private android.widget.TextView Employeename;
    private android.widget.TextView profilename;
    private android.widget.TextView companyname;
    private android.widget.TextView employeecontactno;
    private android.widget.TextView employeeemail;
    private android.widget.TextView company_description;
    private android.widget.TextView biotitle;

    private android.widget.TextView whatidiscussdescription;
    private android.widget.TextView biodecsription;
    PrivateProfileInterface publicProfileInterface;
    private String suppoertEmail = "support@whoat.io";
    private String userEmail;
    WishlistProgressDialog wishlistProgressDialog;
    Typeface normalFont, boldFont;
    private Constants constants;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        wishlistProgressDialog = new WishlistProgressDialog(getActivity());
        publicProfileInterface = new PrivateProfilePresentation(activity, PublicProfileFragment.this);
        constants = new Constants(getActivity());
        wishlistProgressDialog.dialogShow();
        publicProfileInterface.getPrivateUserProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_public_profile, container, false);
        this.biodecsription = (TextView) view.findViewById(R.id.bio_decsription);
        this.company_description = (TextView) view.findViewById(R.id.company_description);
        this.biotitle = (TextView) view.findViewById(R.id.bio_title);
        this.employeeemail = (TextView) view.findViewById(R.id.employee_email);
        this.employeecontactno = (TextView) view.findViewById(R.id.employee_contact_no);
        this.companyname = (TextView) view.findViewById(R.id.company_name);
        this.profilename = (TextView) view.findViewById(R.id.profile_name);
        this.Employeename = (TextView) view.findViewById(R.id.Employee_name);
        this.whatidiscussdescription = (TextView) view.findViewById(R.id.what_i_discuss_description);
        this.profileimage = (CircleImageView) view.findViewById(R.id.profile_image);

        setFontfamily();


        return view;
    }

    private void setFontfamily() {
        normalFont = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        boldFont = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Medium.otf");

        biodecsription.setTypeface(normalFont);
        company_description.setTypeface(normalFont);
        biotitle.setTypeface(normalFont);
        companyname.setTypeface(normalFont);
        biodecsription.setTypeface(normalFont);
        company_description.setTypeface(normalFont);
        whatidiscussdescription.setTypeface(normalFont);


    }

    public PublicProfileFragment() {
        super();
    }

    @Override
    public void setPrivateProfilePicture(String privateProfilePicture) {
        wishlistProgressDialog.dismissDialog();
        Glide.with(getActivity()).load(constants.BASE_URL + privateProfilePicture).error(R.drawable.user_profile).into(profileimage);

    }

    @Override
    public void setPrivateTitle(String privateTitle) {

    }

    @Override
    public void setPrivateFirstName(String privateFirstName) {

        companyname.setText(privateFirstName);
    }

    @Override
    public void setPrivatetBio(String privatetBio) {

        biodecsription.setText(privatetBio);
    }

    @Override
    public void setPrivateEmail(String privateEmail) {
        userEmail = privateEmail;
    }

    @Override
    public void setPrivateComapny(String privateComapny) {

    }

    @Override
    public void setPrivatePhone(String privatePhone) {

    }

    @Override
    public void setPrivateShortBio(String shortBio) {

        company_description.setText(shortBio);
    }

    @Override
    public void setPrivateBusinessDiscussion(String businessDiscussion) {

        whatidiscussdescription.setText(businessDiscussion);

    }

    @Override
    public void setPrivateBusinessAdditional(String businessAdditional) {

        String str = whatidiscussdescription.getText().toString();
        whatidiscussdescription.setText("" + str + "\n\n" + businessAdditional);

    }

    @Override
    public void setPrivateHandle(String privateHandle) {

        companyname.setText(privateHandle);
    }

    @Override
    public void checknetwork(String connection) {
        wishlistProgressDialog.dismissDialog();
        //new AlertDialogBox(getContext()).networkMessage(connection);
        NetworkInterface networkInterface = (NetworkInterface) getContext();
        try {
            networkInterface.connectionMessage(connection);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
