package com.tatteam.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tatteam.database.PoolDatabaseLoader;

/**
 * Created by ThanhNH-Mac on 6/24/16.
 */
public class BaseItalyDataSource {

    private static SQLiteDatabase openConnection() {
        return PoolDatabaseLoader.getInstance().getItalyDatabaseLoader().openConnection();
    }

    private static void closeConnection() {
        PoolDatabaseLoader.getInstance().getItalyDatabaseLoader().closeConnection();
    }


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
