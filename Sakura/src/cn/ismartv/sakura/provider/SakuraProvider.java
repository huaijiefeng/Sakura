package cn.ismartv.sakura.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by fenghb on 14-7-11.
 */
public class SakuraProvider extends ContentProvider {

    //UriMatcher
    private static UriMatcher matcher;
    private DatabaseHelper dbHelper;
    public static final String AUTHORITY = "cn.ismartv.sakura";

    private static final int CACHE = 1;
    private static final int CACHE_ID = 2;


    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, NodeCache.TABLE_NAME, CACHE);
        matcher.addURI(AUTHORITY, NodeCache.TABLE_NAME + "/#", CACHE_ID);
    }


    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return (dbHelper == null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        qb.setTables(NodeCache.TABLE_NAME);
        qb.setProjectionMap(NodeCache.projectionMap);
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
        long rowId = db.insert(NodeCache.TABLE_NAME, "NULL", v);
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
}
