package template.r3tech.com.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import template.r3tech.com.R;
import template.r3tech.com.model.ItemModel;

/**
 * Created by Zymr Inc. on 28/2/18.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LocalDB.db";

    private final String TABLE_NAME = "FAV_ITEMS";
    private final String _ID = "_ID";
    private final String ITEM_CONTENT = "ITEM_CONTENT";
    private final String ITEM_FAV = "ITEM_FAV";

    private final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    ITEM_FAV + " INTEGER," +
                    ITEM_CONTENT + " TEXT)";

    private final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void initiateData(Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String[] _items = context.getResources().getStringArray(R.array.Joke);
            int _totalItemsInArray = _items.length;
            db.beginTransaction();
            Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + "", null);
            cursor.moveToFirst();

            int _TotalItemInDB = cursor.getInt(0);
            if (_totalItemsInArray > _TotalItemInDB) {
                for (int i = _TotalItemInDB; i < _totalItemsInArray; i++) {
                    ContentValues _vals = new ContentValues();
                    _vals.put(_ID, i);
                    _vals.put(ITEM_CONTENT, _items[i]);
                    _vals.put(ITEM_FAV, 0);
                    db.insert(TABLE_NAME, null, _vals);
                }
            }
            db.setTransactionSuccessful();
            db.close();
        } finally {
            db.endTransaction();
        }
    }

    public List<ItemModel> getContent() {

        List<ItemModel> lstItems = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        Cursor cursor = db.rawQuery(query, null);
        ItemModel itemModel = null;
        if (cursor.moveToFirst()) {
            do {
                itemModel = new ItemModel();
                itemModel.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(_ID))));
                itemModel.setItemContent(cursor.getString(cursor.getColumnIndex(ITEM_CONTENT)));
                itemModel.setFav(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(ITEM_FAV))));
                lstItems.add(itemModel);
            } while (cursor.moveToNext());
        }
        db.setTransactionSuccessful();
        db.close();
        return lstItems;
    }

    public void addFavContent(int id, boolean isFav) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_FAV, isFav);
        db.update(TABLE_NAME, values, _ID + "=" + id, null);
        db.close();
    }
}
