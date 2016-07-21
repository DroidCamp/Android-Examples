package appcamp.hemang.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hemang on 12/07/16.
 */
public class MyDatabase {

    //STEP 1
    //Create static constants

    public static final String DB_NAME = "tasks.db";
    public static final int DB_VERSION = 2;
    public static final String DB_TABLE= "Info";
    public static final String C_TASK = "Task";
    public static final String C_DESCRIPTION = "Description";
    public static final String Q_CREATE = "CREATE TABLE " + DB_TABLE + " (" + C_TASK + " TEXT, " + C_DESCRIPTION
            + " TEXT)";
    Context context;
    SQLiteDatabase sqlLiteDatabase;
    //STEP 2
    //Create DBHelper private class extending SqliteOpenHelper


    public MyDatabase(Context c) {
        //Context are basically privileges
        //To create this class and operate only on places where the this activity has access privileges
        context = c;
    }

    public MyDatabase open() {

        //CREATES A DATABaSE UNDER THE CONTEXT OF SAVE ACTIVITY
        DBHelper dbHelper = new DBHelper(context);
        sqlLiteDatabase = dbHelper.getWritableDatabase();

        return this;
    }

    public void write(String task, String description) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(C_TASK, task);
        contentValues.put(C_DESCRIPTION, description);

        sqlLiteDatabase.insert(DB_TABLE, null, contentValues);


    }

    public void close() {
        sqlLiteDatabase.close();
    }

    public String read() {
        String result ="";

        //Add only the columns to be read
        String[] columns = { C_TASK, C_DESCRIPTION};


        //Create query String
      //  String READ_QUERY = "SELECT * FROM " + DB_TABLE;
        //OR USE A SMARTER METHOD

        Cursor cursor =  sqlLiteDatabase.query(
                DB_TABLE,
                columns,
                null, null,null,null,null);

        //Cursor cant fetch by column name but by column index
        //So fetch the index attained by column in the cursor object

        int taskIndex = cursor.getColumnIndex(C_TASK);
        int descriptionIndex = cursor.getColumnIndex(C_DESCRIPTION);

        //special for loop of cursor

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            result = result
                    + cursor.getString(taskIndex)
                    + " \t"
                    + cursor.getString(descriptionIndex)
                    + "\n\n" ;
        }

        return result;
    }

    private class DBHelper extends SQLiteOpenHelper{


        //DATABASE CREATED BY THIS CONSTRUCTOR AND SENT TO onCreate

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            //Execute query to create table

            db.execSQL(Q_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
