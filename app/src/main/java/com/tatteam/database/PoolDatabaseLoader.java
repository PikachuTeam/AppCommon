package com.tatteam.database;

import android.content.Context;

import tatteam.com.app_common.sqlite.v2.DatabaseLoaderV2;

/**
 * Created by ThanhNH-Mac on 6/24/16.
 */
public class PoolDatabaseLoader {

    private static final String DATABASE_NAME_INDO_DRIVING = "indo_driving.db";
    private static final String DATABASE_NAME_ITALY_DRIVING = "italy_driving.db";

    private static PoolDatabaseLoader instance;

    private DatabaseLoaderV2 indoDatabaseLoader;
    private DatabaseLoaderV2 italyDatabaseLoader;

    private PoolDatabaseLoader() {
    }

    public static PoolDatabaseLoader getInstance() {
        if (instance == null) {
            instance = new PoolDatabaseLoader();
        }
        return instance;
    }

    public void initIfNeeded(Context context) {
        indoDatabaseLoader = new DatabaseLoaderV2(context, DATABASE_NAME_INDO_DRIVING);
        indoDatabaseLoader.initIfNeeded();

        italyDatabaseLoader = new DatabaseLoaderV2(context, DATABASE_NAME_ITALY_DRIVING);
        italyDatabaseLoader.initIfNeeded();
    }

    public DatabaseLoaderV2 getIndoDatabaseLoader() {
        return indoDatabaseLoader;
    }

    public DatabaseLoaderV2 getItalyDatabaseLoader() {
        return italyDatabaseLoader;
    }
}
