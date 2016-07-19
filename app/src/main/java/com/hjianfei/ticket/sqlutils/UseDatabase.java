package com.hjianfei.ticket.sqlutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by HJianFei on 2016-7-18.
 */
public class UseDatabase {

    Context context;
    DatabaseHelper dbhelper;
    public SQLiteDatabase sqlitedatabase;

    public UseDatabase(Context context) {
        super();
        this.context = context;
    }

    //打开数据库连接
    public void opendb(Context context) {
        dbhelper = new DatabaseHelper(context);
        sqlitedatabase = dbhelper.getWritableDatabase();
    }

    //关闭数据库连接
    public void closedb(Context context) {
        if (sqlitedatabase.isOpen()) {
            sqlitedatabase.close();
        }
    }

    //插入表数据
    public void insert(String table_name, ContentValues values) {
        opendb(context);
        sqlitedatabase.insert(table_name, null, values);
        closedb(context);
    }

    //更新数据
    public int updatatable(String table_name, ContentValues values, int ID) {
        opendb(context);
        return sqlitedatabase.update(table_name, values, " Type_ID = ? ", new String[]{String.valueOf(ID)});
    }

    //删除表数据
    public void delete(String table_name) {
        opendb(context);
        try {

            sqlitedatabase.delete(table_name, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closedb(context);
        }
    }
}
