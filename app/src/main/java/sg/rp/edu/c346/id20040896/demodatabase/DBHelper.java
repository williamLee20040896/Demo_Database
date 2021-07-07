package sg.rp.edu.c346.id20040896.demodatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "task.db";
    private static final String Table_Task = "task";
    private static final String COlumn_ID = "_id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTabelSql ="CREATE TABLE " + Table_Task + "(" +
                COlumn_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DATE + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT  )";
        db.execSQL(createTabelSql);
        Log.i("info", "created tables" + createTabelSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Task);
        onCreate(db);

    }
    public void insertTask(String description, String date){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_DATE, date);
        db.insert(Table_Task, null, values);
        db.close();
    }
    public ArrayList<String> getTaskContent(){
        ArrayList<String> task = new ArrayList<String>();
        String selectQuery  = "SELECT " + COLUMN_DESCRIPTION + " FROM " + Table_Task;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{task.add(cursor.getString(0));


            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return task;

    }

}
