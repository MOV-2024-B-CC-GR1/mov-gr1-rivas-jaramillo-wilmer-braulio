package com.example.ccgr12024b_wbrj

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "AvionesDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_AVIONES = "aviones"

        private const val COLUMN_ID = "id"
        private const val COLUMN_MODELO = "modelo"
        private const val COLUMN_FABRICANTE = "fabricante"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_AVIONES (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_MODELO TEXT, " +
                "$COLUMN_FABRICANTE TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_AVIONES")
        onCreate(db)
    }

    fun insertarAvion(modelo: String, fabricante: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MODELO, modelo)
            put(COLUMN_FABRICANTE, fabricante)
        }
        val result = db.insert(TABLE_AVIONES, null, values)
        db.close()
        return result != -1L
    }

    fun obtenerAviones(): List<Avion> {
        val listaAviones = mutableListOf<Avion>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_AVIONES", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val modelo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODELO))
                val fabricante = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FABRICANTE))
                listaAviones.add(Avion(id, modelo, fabricante))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return listaAviones
    }

    fun eliminarAvion(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_AVIONES, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }
}