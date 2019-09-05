package com.root.wishlist.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.root.wishlist.pojo.mywishlist.Result;
import com.root.wishlist.pojo.search.SearchResult;

import java.util.ArrayList;

public class CompanyListDatabase extends SQLiteOpenHelper {

    private final String DATABASE_NAME = "Company";
    private final String COMPANY_NAME = "companyName";
    private final String COMPANY_IMAGE = "Companyprofileimage";
    private final String COMPANY_ID = "companyId";
    private final String COMPANY_ICON = "icon";

    public CompanyListDatabase(Context context) {
        super(context, "Company", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String HotSearchList = "create table HotsearchCompany(companyName Text primary key,icon Text,companyId text)";
        String counterCompanytable = "create table CounterCompanytable(companyName Text primary key,Companyprofileimage Text,companyId text)";
        String CompanyList = "create table CompanyList(companyName Text primary key,Companyprofileimage Text,companyId text)";
        db.execSQL(CompanyList);
        db.execSQL(counterCompanytable);
        db.execSQL(HotSearchList);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS CompanyList");
        db.execSQL("DROP TABLE IF EXISTS counterCompanytable");
        onCreate(db);
    }

    public boolean insertCompanyList(int companyId, String companyName, String Companyprofileimage) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("companyId", companyId);
            contentValues.put("companyName", companyName);
            contentValues.put("Companyprofileimage", Companyprofileimage);
            db.insert("CompanyList", null, contentValues);
        } catch (SQLiteConstraintException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean insertCounterCompanyTable(int companyId, String companyName, String Companyprofileimage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("companyId", companyId);
        contentValues.put("companyName", companyName);
        contentValues.put("Companyprofileimage", Companyprofileimage);
        db.insert("CounterCompanytable", null, contentValues);
        return true;
    }

    public boolean updateContact(String companyName, String Companyprofileimage, int companyId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("companyName", companyName);
        contentValues.put("Companyprofileimage", Companyprofileimage);
        db.update("CompanyList", contentValues, "companyId = ? ", new String[]{String.valueOf(companyId)});
        return true;
    }

    public Integer deleteCompanyListItem(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("CompanyList", "companyId = ? ",
                new String[]{Integer.toString(id)});
    }

    public Integer deleteCounterCompanyListItem(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("CounterCompanytable", "companyId = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<Result> getCompanyList() {
        ArrayList<Result> array_list = new ArrayList<Result>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from CompanyList order by companyName ", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(new Result(res.getString(res.getColumnIndex(COMPANY_NAME)),
                    res.getString(res.getColumnIndex(COMPANY_IMAGE))
                    , res.getInt(res.getColumnIndex(COMPANY_ID))));

            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Result> getCounterCompany() {
        ArrayList<Result> array_list = new ArrayList<Result>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from CounterCompanytable", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(new Result(res.getString(res.getColumnIndex(COMPANY_NAME)),
                    res.getString(res.getColumnIndex(COMPANY_IMAGE))
                    , res.getInt(res.getColumnIndex(COMPANY_ID))));

            res.moveToNext();
        }
        return array_list;
    }

    public Integer totalCounter() {
        int id = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from CounterCompanytable", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            id = res.getCount();
            res.moveToNext();
        }
        return id;
    }

    public void deletetable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from CompanyList");
        db.execSQL("delete from CounterCompanytable");
    }

    //for hot search
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
                    res.getString(res.getColumnIndex(COMPANY_ICON))));

            res.moveToNext();
        }
        return array_list;
    }
}
