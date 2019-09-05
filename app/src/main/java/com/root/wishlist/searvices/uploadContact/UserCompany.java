package com.root.wishlist.searvices.uploadContact;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

public class UserCompany {
    Context context;

    public UserCompany(Context context) {
        this.context = context;
    }

    public String getCompanyName(int contactID) {

        String companyname = "";
        String orgWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
        String[] orgWhereParams = new String[]{String.valueOf(contactID),
                ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE};
        Cursor orgCur = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                null, orgWhere, orgWhereParams, null);
        if (orgCur.moveToFirst()) {
            companyname = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DATA));
        }
        orgCur.close();
        return companyname;
    }
}
