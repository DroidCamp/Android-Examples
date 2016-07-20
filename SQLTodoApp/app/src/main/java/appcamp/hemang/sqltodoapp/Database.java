package appcamp.hemang.sqltodoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database{

    String DB_NAME =  "task.db";
    int DB_V = 2;
    String DBTable = "tasks";
    String CT = "cTask";
    String CD = "cDesc";
    String Q = "CREATE TABLE " + DBTable + " (" + CT + " TEXT , " + CD + " TEXT)";

    SQLiteDatabase db;
    private final Context ctx;

    public class DBhelper extends SQLiteOpenHelper{

        public DBhelper(Context context) {
            super(context, DB_NAME, null, DB_V);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Q);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public Database(Context context){
        ctx = context;
    }

    public void open(){
        DBhelper dBhelper = new DBhelper(ctx);
        db = dBhelper.getWritableDatabase();


    }

    public void write(String task , String desc){
        ContentValues cv = new ContentValues();
        cv.put(CT, task);
        cv.put(CD, desc);
        db.insert(DBTable, null, cv);
    }

    public void close(){
        db.close();
    }

    public String read(){
        String res = "";

        String columns[] = {CT, CD};

        Cursor cursor = db.query(DBTable, columns, null, null, null, null, null);

        int iT = cursor.getColumnIndex(CT);
        int iD = cursor.getColumnIndex(CD);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            res = res + cursor.getString(iT) + "\t" + cursor.getString(iD) + "\n";
        }
        return res;
    }
}