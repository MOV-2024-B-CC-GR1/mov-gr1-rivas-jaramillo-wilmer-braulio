package com.example.ccgr12024b_wbrj

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "aviones.db"
        private const val DATABASE_VERSION = 2 // 🔹 Cambia la versión de la base de datos

        const val TABLE_AVIONES = "avion"
        const val COLUMN_ID = "id"
        const val COLUMN_MODELO = "modelo"
        const val COLUMN_FABRICANTE = "fabricante"
        const val COLUMN_LATITUD = "latitud"  // 🔹 Nueva columna
        const val COLUMN_LONGITUD = "longitud"  // 🔹 Nueva columna
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_AVIONES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_MODELO TEXT,
                $COLUMN_FABRICANTE TEXT,
                $COLUMN_LATITUD REAL,  -- 🔹 Tipo REAL para almacenar Double
                $COLUMN_LONGITUD REAL   -- 🔹 Tipo REAL para almacenar Double
            )
        """.trimIndent()

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) { // 🔹 Verifica si la versión anterior es menor a la nueva
            db.execSQL("ALTER TABLE $TABLE_AVIONES ADD COLUMN $COLUMN_LATITUD REAL DEFAULT 0.0")
            db.execSQL("ALTER TABLE $TABLE_AVIONES ADD COLUMN $COLUMN_LONGITUD REAL DEFAULT 0.0")
        }
    }


    fun insertarAvion(modelo: String, fabricante: String, latitud: Double, longitud: Double): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("modelo", modelo)
        values.put("fabricante", fabricante)
        values.put("latitud", latitud)
        values.put("longitud", longitud)

        val resultado = db.insert("avion", null, values)
        return resultado != -1L
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
                val latitud = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LATITUD))
                val longitud = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LONGITUD))
                listaAviones.add(Avion(id, modelo, fabricante, latitud, longitud))
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