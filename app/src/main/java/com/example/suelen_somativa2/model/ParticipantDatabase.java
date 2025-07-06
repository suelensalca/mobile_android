package com.example.suelen_somativa2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ParticipantDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "participants.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Participant";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "nome";
    private static final String COL_PHONE = "telefone";

    public ParticipantDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create table if not exists " + DB_TABLE + "( " + COL_ID + " integer primary key autoincrement, " + COL_NAME + " text, " + COL_PHONE + " text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long createParticipantInDB(Participant p){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, p.getNome());
        values.put(COL_PHONE, p.getTelefone());
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }

    public ArrayList<Participant> getParticipantFromDB(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);
        ArrayList<Participant>participants = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE));
                participants.add(new Participant(id, name, phone));
            } while (cursor.moveToNext());
        }

        database.close();
        return participants;
    }

    public long updateParticipantInDB(Participant p){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, p.getNome());
        values.put(COL_PHONE, p.getTelefone());
        String id = String.valueOf(p.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.update(DB_TABLE, values, COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }

    public int removeParticipantInDB(Participant p){
        String id = String.valueOf(p.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.delete(DB_TABLE, COL_ID + "=?", new String[]{id});
        database.close();
        return count;
    }

}
