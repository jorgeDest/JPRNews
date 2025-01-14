package com.fic.jprnews1_0;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {


private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "jpr.db";
    private static final String TABLE_USUARIO = "t_Usuarios";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE"+TABLE_USUARIO+"(" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "CorreoElectronico TEXT NOT NULL," +
                "Contrasenia TEXT NOT NULL)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
