package com.root.wishlist.searvices.uploadContact;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * Created by dal on 10/10/16.
 */
public class UserJobTitle {

    Context context;

    public UserJobTitle(Context context) {

        this.context = context;
    }

    public String getJobTitle(int contactID) {

        String jobtitle = "";
        String orgWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
        String[] orgWhereParams = new String[]{String.valueOf(contactID),
                ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE};
        Cursor orgCur = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                null, orgWhere, orgWhereParams, null);
        if (orgCur.moveToFirst()) {
            jobtitle = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE));

        }
        orgCur.close();
        return jobtitle;
    }


}
