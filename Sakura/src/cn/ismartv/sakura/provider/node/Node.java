package cn.ismartv.sakura.provider.node;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.HashMap;

/**
 * Created by fenghb on 14-7-11.
 */
public class Node {
    public static final String AUTHORITY = "cn.ismartv.sakura";


    public static class Cache extends ContentProvider {
        //projection map
        public static HashMap<String, String> projectionMap;
        //UriMatcher
        private static UriMatcher matcher;

        private static final String TABLE_NAME = "node_cache";
        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/" + TABLE_NAME);

        private DBHelper dbHelper;

        //columns
        public static final String ID = BaseColumns._ID;
        public static final String CDN_ID = "cdn_id";
        public static final String NODE = "node";
        public static final String FLAG = "flag";
        public static final String IP = "ip";
        public static final String URL = "url";
        public static final String ROUTE_TRACE = "route_trace";
        public static final String SPEED = "speed";
        public static final String UPDATE_TIME = "update_time";

        //
        private static final int CACHE = 1;
        private static final int CACHE_ID = 2;


        static {
            matcher = new UriMatcher(UriMatcher.NO_MATCH);
            matcher.addURI(AUTHORITY, TABLE_NAME, CACHE);
            matcher.addURI(AUTHORITY, TABLE_NAME + "/#", CACHE_ID);
        }

        //projection map
        static {
            projectionMap = new HashMap<String, String>();
            projectionMap.put(ID, ID);
            projectionMap.put(CDN_ID, CDN_ID);
            projectionMap.put(NODE, NODE);
            projectionMap.put(FLAG, FLAG);
            projectionMap.put(IP, IP);
            projectionMap.put(URL, URL);
            projectionMap.put(ROUTE_TRACE, ROUTE_TRACE);
            projectionMap.put(SPEED, SPEED);
            projectionMap.put(UPDATE_TIME, UPDATE_TIME);
        }

        @Override
        public boolean onCreate() {
            dbHelper = new DBHelper(getContext());
            return (dbHelper == null) ? false : true;
        }

        @Override
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            qb.setTables(TABLE_NAME);
            qb.setProjectionMap(projectionMap);
            Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        }

        @Override
        public String getType(Uri uri) {
            return null;
        }

        @Override
        public Uri insert(Uri uri, ContentValues contentValues) {
            switch (matcher.match(uri)) {
                case CACHE:
                    break;
                default:
                    throw new IllegalArgumentException("Unknow uri: " + uri);

            }

            // make a copy of the values
            ContentValues v;
            if (contentValues != null) {
                v = new ContentValues(contentValues);
            } else {
                v = new ContentValues();
            }

            // store the data
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            long rowId = db.insert(TABLE_NAME, "NULL", v);
            if (rowId > 0) {
                Uri catUri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(uri, null);
                return catUri;
            }
            throw new RuntimeException("Failed to insert row into " + uri);
        }


        @Override
        public int delete(Uri uri, String s, String[] strings) {
            return 0;
        }

        @Override
        public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
            return 0;
        }


        //create table sql
        public static final String CREATE_TABLE_SQL = "CREATE TABLE "
                + TABLE_NAME + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CDN_ID + " INTEGER,"
                + NODE + " VARCHAR(255),"
                + FLAG + " VARCHAR(255),"
                + IP + " VARCHAR(255),"
                + URL + " VARCHAR(255),"
                + ROUTE_TRACE + " VARCHAR(255),"
                + SPEED + " VARCHAR(255),"
                + UPDATE_TIME + " VARCHAR(255))";
    }
}
