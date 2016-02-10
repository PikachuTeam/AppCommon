package com.tatteam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import tatteam.com.app_common.sqlite.BaseDataSource;

/**
 * Created by ThanhNH-Mac on 2/10/16.
 */
public class DataSource extends BaseDataSource {

    public static int countExams() {
        //open connection
        SQLiteDatabase sqLite = openConnection();

        Cursor cursor = sqLite.rawQuery("select count(1) from Exams", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);

        //close cursor
        cursor.close();

        //close connection
        closeConnection();

        return count;
    }

}
