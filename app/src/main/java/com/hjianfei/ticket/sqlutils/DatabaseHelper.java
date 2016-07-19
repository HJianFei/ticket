package com.hjianfei.ticket.sqlutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HJianFei on 2016-7-18.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;  //数据库版本号

    private static final String DATABASE_NAME = "ticket";  //数据库名称

    private static final String TOTE_COMMON = "rote_common";//常用路线

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqldept = "create table TOTE_COMMON(ROTE_ID String PRIMARY KEY ,TOTE text)";
        db.execSQL(sqldept);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
