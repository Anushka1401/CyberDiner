package com.example.cyberdiner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CartDB extends SQLiteOpenHelper {
    private static int DB_VERSION=1;
    private static String DATABASE_NAME="CartDB";
    private static String TABLE_NAME="cartTable";
    public static String PRICE="itemPrice";
    public static String ITEM_TITLE="itemTitle";
    public static String ITEM_IMAGE="itemImage";
    public static String QUANTITY="itemQty";
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + PRICE + " TEXT," + ITEM_TITLE+ " TEXT,"
            + ITEM_IMAGE + " TEXT," + QUANTITY+" TEXT)";

    public CartDB(Context context) {super(context,DATABASE_NAME, null,DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        for(int x=1; x<4; x++){
            cv.put(PRICE, x);
            cv.put(QUANTITY, "0");

            db.insert(TABLE_NAME, null,cv);
        }
    }

    public void insertIntoTheDatabase(String item_title, int item_image, String item_price, String item_qty){
        SQLiteDatabase db;
        db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ITEM_TITLE, item_title);
        cv.put(ITEM_IMAGE,item_image);
        cv.put(PRICE,item_price);
        cv.put(QUANTITY,item_qty);
        db.insert(TABLE_NAME,null,cv);
        //Log.d("FavDB Status", item_title + ", favstatus - "+fav_status+" - . " + cv);

    }
    public Cursor read_all_data(String itemTitle) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + ITEM_TITLE+"="+itemTitle+"";
        return db.rawQuery(sql,null,null);
    }
    public void remove_fav(String itemTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE "+ITEM_TITLE+"='"+itemTitle+"'";
        db.execSQL(sql);
        //Log.d("remove", itemTitle.toString());

    }
    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+QUANTITY+" >='1'";
        return db.rawQuery(sql,null,null);
    }

}
