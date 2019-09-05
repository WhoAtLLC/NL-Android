package com.root.wishlist.searvices.uploadContact;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * Created by dal on 10/10/16.
 */
public class UserName {

    Context context;

    public UserName(Context context) {
        this.context = context;

    }

    public String getFirstName(int contactId) {
        String firstname = null;
        Cursor firstlast = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{String.valueOf(contactId)}, null);
        while (firstlast.moveToNext()) {

            firstname = firstlast.getString(firstlast.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
            if (firstname == null) {
                firstname = "unknown";
            }

        }
        firstlast.close();
        return firstname;
    }

    public String getLastName(int contactId) {
        String lastname = null;
        Cursor firstlast = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{String.valueOf(contactId)}, null);
        while (firstlast.moveToNext()) {

            lastname = firstlast.getString(firstlast.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
            if (lastname == null) {
                lastname = "unknown";
            }

        }
        firstlast.close();
        return lastname;
    }

    private String getName(int contactId) {

        String name = "";
        final String[] projection = new String[]{ContactsContract.Contacts.DISPLAY_NAME};

        final Cursor contact = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projection, ContactsContract.Contacts._ID + "=?", new String[]{String.valueOf(contactId)}, null);

        if (contact.moveToFirst()) {
            name = contact.getString(contact.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            contact.close();
        }
        contact.close();
        return name;
    }


    private String getAddress(int contactId) {
        String postalData = "";
        String addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
        String[] addrWhereParams = new String[]{String.valueOf(contactId), ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE};

        Cursor addrCur = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, addrWhere, addrWhereParams, null);

        if (addrCur.moveToFirst()) {
            postalData = addrCur.getString(addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
        }
        addrCur.close();
        return postalData;
    }

}
