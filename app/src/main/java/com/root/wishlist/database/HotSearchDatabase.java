package com.root.wishlist.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.root.wishlist.pojo.search.SearchResult;

import java.util.ArrayList;

public class HotSearchDatabase extends SQLiteOpenHelper {

    private final String DATABASE_NAME = "HotSearch";
    private final String COMPANY_NAME = "companyName";
    private final String COMPANY_IMAGE = "icon";
    private final String COMPANY_ID = "companyId";

    public HotSearchDatabase(Context context) {
        super(context, "Company", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String counterCompanytable = "create table CounterHotSearch(companyName Text primary key,Companyprofileimage Text,companyId text)";
        String CompanyList = "create table HotsearchCompany(companyName Text primary key,icon Text,companyId text)";
        db.execSQL(CompanyList);
        db.execSQL(counterCompanytable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS CounterHotSearch");
        db.execSQL("DROP TABLE IF EXISTS HotsearchCompany");
        onCreate(db);
    }

    public boolean insertHotSearch(int companyId, String companyName, String Companyprofileimage) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("companyId", companyId);
            contentValues.put("companyName", companyName);
            contentValues.put("icon", Companyprofileimage);
            db.insert("HotsearchCompany", null, contentValues);
        } catch (SQLiteConstraintException e) {
            e.printStackTrace();
        }

        return true;
    }

    public ArrayList<SearchResult> getHotSearch() {
        ArrayList<SearchResult> array_list = new ArrayList<SearchResult>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from HotsearchCompany order by companyName ASC LIMIT 10", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(new SearchResult(res.getInt(res.getColumnIndex(COMPANY_ID)), res.getString(res.getColumnIndex(COMPANY_NAME)),
                    res.getString(res.getColumnIndex(COMPANY_IMAGE))));

            res.moveToNext();
        }
        return array_list;
    }

}
