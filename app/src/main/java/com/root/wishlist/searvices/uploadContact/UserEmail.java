package com.root.wishlist.searvices.uploadContact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class UserEmail {
    Context context;

    public UserEmail(Context context) {
        this.context = context;

    }

    public List<String> getEmail(int contactId) {
        List<String> emailStr = new ArrayList<String>();
        ContentResolver cr = context.getContentResolver();
        Cursor emailCur = cr.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{String.valueOf(contactId)}, null);
        while (emailCur.moveToNext()) {
            String email = emailCur.getString(
                    emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            String emailType = emailCur.getString(
                    emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));

            emailStr.add(email);
            System.out.println("Email " + email + " Email Type : " + emailType);
        }
        emailCur.close();
        return emailStr;

    }


}
