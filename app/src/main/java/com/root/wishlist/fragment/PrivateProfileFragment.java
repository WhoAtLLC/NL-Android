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
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.Constants;
import com.root.wishlist.presentation.profile.PrivateProfileInterface;
import com.root.wishlist.presentation.profile.PrivateProfilePresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.PrivateProfileView;

import de.hdodenhof.circleimageview.CircleImageView;


public class PrivateProfileFragment extends Fragment implements PrivateProfileView {


    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private android.widget.TextView Employeename;
    private android.widget.TextView profilename;
    private android.widget.TextView companyname;
    private android.widget.TextView employeecontactno;
    private android.widget.TextView employeeemail;
    private android.widget.TextView biotitle;
    private android.widget.TextView biodecsription;
    PrivateProfileInterface publicProfileInterface;
    private String firstName;
    SharedDatabase sharedDatabase;
    private String suppoertEmail = "support@whoat.io";
    Typeface normalFont, boldFont;
    private Constants constants;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        constants = new Constants(getActivity());
        publicProfileInterface = new PrivateProfilePresentation(activity, PrivateProfileFragment.this);
        publicProfileInterface.getPrivateUserProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_private_profile, container, false);
        this.biodecsription = (TextView) view.findViewById(R.id.bio_decsription);
        this.biotitle = (TextView) view.findViewById(R.id.bio_title);
        this.employeeemail = (TextView) view.findViewById(R.id.employee_email);
        this.employeecontactno = (TextView) view.findViewById(R.id.employee_contact_no);
        this.companyname = (TextView) view.findViewById(R.id.company_name);
        this.profilename = (TextView) view.findViewById(R.id.profile_name);
        this.Employeename = (TextView) view.findViewById(R.id.Employee_name);
        this.profileimage = (CircleImageView) view.findViewById(R.id.profile_image);
        sharedDatabase = new SharedDatabase(getActivity());


        return view;
    }

    private void setFontfamily() {
        normalFont = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        boldFont = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Medium.otf");

        biodecsription.setTypeface(normalFont);
        profilename.setTypeface(normalFont);
        biotitle.setTypeface(normalFont);
        companyname.setTypeface(normalFont);
        Employeename.setTypeface(normalFont);
        employeeemail.setTypeface(normalFont);
        employeecontactno.setTypeface(normalFont);


    }

    public PrivateProfileFragment() {
        super();
    }


    @Override
    public void setPrivateProfilePicture(String privateProfilePicture) {
        Glide.with(getActivity()).load(constants.BASE_URL + privateProfilePicture).error(R.drawable.user_profile).into(profileimage);
    }

    @Override
    public void setPrivateTitle(String privateTitle) {

        profilename.setText(privateTitle);
    }

    @Override
    public void setPrivateFirstName(String privateFirstName) {
        Employeename.setText(privateFirstName);
    }


    @Override
    public void setPrivatetBio(String privatetBio) {
        biodecsription.setText(privatetBio);
    }

    @Override
    public void setPrivateEmail(String privateEmail) {
        employeeemail.setText(privateEmail);
    }

    @Override
    public void setPrivateComapny(String privateComapny) {
        companyname.setText(privateComapny);
    }

    @Override
    public void setPrivatePhone(String privatePhone) {
        employeecontactno.setText(privatePhone);
    }

    @Override
    public void setPrivateShortBio(String shortBio) {

    }

    @Override
    public void setPrivateBusinessDiscussion(String businessDiscussion) {

    }

    @Override
    public void setPrivateBusinessAdditional(String businessAdditional) {

    }

    @Override
    public void setPrivateHandle(String privateHandle) {

    }

    @Override
    public void checknetwork(String connection) {
        //new AlertDialogBox(getContext()).networkMessage(connection);
    }

}
