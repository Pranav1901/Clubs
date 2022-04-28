package android.example.cells;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "ClubData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table clubdetails(name TEXT primary key,domain TEXT,dob TEXT,dep TEXT,fcord TEXT,pname TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists clubdetails ");
    }

    public boolean insertclubdata(String name,String domain, String dob,String dep,String fcord,String Pname){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("domain",domain);
        contentValues.put("dob",dob);
        contentValues.put("dep",dep);
        contentValues.put("fcord",fcord);
        contentValues.put("Pname",Pname);

        long result = DB.insert("Clubdetails",null,contentValues);
        if(result==-1)return false;
        else return true;
    }
    public boolean updateclubdata(String name,String domain, String dob,String dep,String fcord,String Pname){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("domain",domain);
        contentValues.put("dob",dob);
        contentValues.put("dep",dep);
        contentValues.put("fcord",fcord);
        contentValues.put("Pname",Pname);
        Cursor cursor = DB.rawQuery("Select * from Clubdetails where name = ?",new String[]{name});
        if(cursor.getCount()>0){
            long result = DB.update("Clubdetails",contentValues,"name=?",new String[]{name});
            if(result==-1)return false;
            else return true;
        }
        else return false;

    }
    public boolean deleteclubdata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Clubdetails where name = ?",new String[]{name});
        if(cursor.getCount()>0){
            long result = DB.delete("Clubdetails","name=?",new String[]{name});
            if(result==-1)return false;
            else return true;
        }
        else return false;
    }
    public Cursor getSpecificData(String name){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Clubdetails where name =?",new String[]{name});
     //   Cursor cursor = DB.query("Clubdetails",new String[]{"name","domain","dob","dep","fcord","Pname"},"name=?",new String[]{name},null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor getclubdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Clubdetails ",null);
        return cursor;
    }
}
