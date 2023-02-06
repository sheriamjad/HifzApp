package com.example.salahtracker;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHandler   extends SQLiteOpenHelper {

        private Context context;

        private static final String DATABASE_NAME = "HifzDb";
        private static final String TABLE_NAME = "Student";

        private static final String COLUMN_SID = "Sid";
        private static final String COLUMN_STNAME= "StName";

        private static final String COLUMN_DATE = "date";
        private static final String COLUMN_SABAK = "Sabak";
        private static final String COLUMN_SABKI = "Sabki";
        private static final String COLUMN_MANZIL = "Manzil";


        public DBHandler( Context context) {
            super(context, DATABASE_NAME, null, 1);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_SID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_STNAME + " TEXT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_SABAK + " TEXT,"
                    + COLUMN_SABKI + " TEXT,"
                    + COLUMN_MANZIL + " TEXT"
                    + ")";
            db.execSQL(sql);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(sql);
            onCreate(db);
        }


        public  boolean insertStudent(Student student) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_STNAME, student.stName);
            values.put(COLUMN_DATE, student.date);
            values.put(COLUMN_SABAK, student.sabak);
            values.put(COLUMN_SABKI, student.sabki);
            values.put(COLUMN_MANZIL,student.manzil );
            long result= db.insert(TABLE_NAME, null, values);
            db.close();
            if(result == -1){
                //Toast.makeText(this.context,  namaz.date, Toast.LENGTH_SHORT).show();

                return false;}
            else
                return true;
        }
        public Cursor ReadAllData() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res=null;
            if(db!=null){
                res = db.rawQuery("select * from "+TABLE_NAME,null);
            }
            return res;
        }
        public  boolean updateData(Student student) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_STNAME, student.stName);
//            values.put(COLUMN_DATE, namaz.date);
            values.put(COLUMN_SABAK, student.sabak);
            values.put(COLUMN_SABKI, student.sabki);
            values.put(COLUMN_MANZIL,student.manzil);

            long result = db.update(TABLE_NAME, values, COLUMN_STNAME + " =?", new String[]{student.stName});
            db.close();

            if (result == -1) {

                return false;
            } else {
                return true;
            }
        }
        public  boolean deleteData(String rollNo) {
            SQLiteDatabase db = this.getWritableDatabase();
            long result =db.delete(TABLE_NAME, COLUMN_STNAME + " = ?", new String[] {rollNo});
            db.close();
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
    }

