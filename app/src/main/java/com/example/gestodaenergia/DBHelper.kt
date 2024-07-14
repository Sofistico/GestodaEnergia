package com.example.gestodaenergia

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_SESSOES_TABLE)
        db.execSQL(CREATE_REGISTROS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTROS)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSOES)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "energia.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_SESSOES: String = "sessoes"
        const val TABLE_REGISTROS: String = "registros"

        const val COLUMN_ID: String = "_id"
        const val COLUMN_NAME: String = "name"

        const val COLUMN_SESSAO_ID: String = "sessao_id"
        const val COLUMN_TIPO: String = "tipo"
        const val COLUMN_QUANTIDADE: String = "quantidade"

        private const val CREATE_SESSOES_TABLE = "CREATE TABLE " + TABLE_SESSOES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL);"

        private const val CREATE_REGISTROS_TABLE = "CREATE TABLE " + TABLE_REGISTROS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SESSAO_ID + " INTEGER, " +
                COLUMN_TIPO + " TEXT NOT NULL, " +
                COLUMN_QUANTIDADE + " REAL NOT NULL, " +
                "FOREIGN KEY(" + COLUMN_SESSAO_ID + ") REFERENCES " + TABLE_SESSOES + "(" + COLUMN_ID + "));"
    }
}
