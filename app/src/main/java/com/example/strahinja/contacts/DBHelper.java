package com.example.strahinja.contacts;

/**
 * Created by Strahinja on 4/11/2018.
 */
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Contacts1.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FIRSTNAME";
    public static final String COL_3 = "LASTNAME";
    public static final String COL_4 = "PHONE";
    public static final String COL_5 = "FAVORITE";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table IF NOT EXISTS " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT, LASTNAME TEXT, PHONE INTEGER, FAVORITE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String fname, String lname, String phone, int favorite)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, fname);
        contentValues.put(COL_3, lname);
        contentValues.put(COL_4, phone);
        contentValues.put(COL_5, favorite);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getContact(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+ " WHERE 'ID = ?'", new String[] { id });
        return res;
    }

    public boolean updateData(String id, String fname, String lname, String phone, int favorite)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2, fname);
        contentValues.put(COL_3, lname);
        contentValues.put(COL_4, phone);
        contentValues.put(COL_5, favorite);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("delete from "+TABLE_NAME);
        onUpgrade(db,1,2);
    }

    public Integer deleteReset()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "1", null);
    }

}
