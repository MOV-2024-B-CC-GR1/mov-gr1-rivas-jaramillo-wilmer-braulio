package com.example.ccgr12024b_wbrj

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableEstacionamientos = """
            CREATE TABLE estacionamientos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                ubicacion TEXT NOT NULL,
                descripcion TEXT NOT NULL,
                precio TEXT NOT NULL,
                latitud REAL NOT NULL,
                longitud REAL NOT NULL,
                imagen INTEGER NOT NULL
            )
        """.trimIndent()

        val createTableSolicitudes = """
            CREATE TABLE solicitudes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                id_estacionamiento INTEGER NOT NULL,
                fecha TEXT NOT NULL,
                hora_inicio TEXT NOT NULL,
                hora_fin TEXT NOT NULL,
                FOREIGN KEY(id_estacionamiento) REFERENCES estacionamientos(id)
            )
        """.trimIndent()

        db.execSQL(createTableEstacionamientos)
        db.execSQL(createTableSolicitudes)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS estacionamientos")
        db.execSQL("DROP TABLE IF EXISTS solicitudes")
        onCreate(db)
    }

    // Método para insertar un nuevo estacionamiento
    fun insertarEstacionamiento(
        nombre: String,
        ubicacion: String,
        descripcion: String,
        precio: Double,
        latitud: Double,
        longitud: Double,
        imagen: Int // Agregamos imagen como parámetro
    ): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("ubicacion", ubicacion)
        values.put("descripcion", descripcion)
        values.put("precio", precio)
        values.put("latitud", latitud)
        values.put("longitud", longitud)
        values.put("imagen", imagen) // Guardamos la imagen en la base de datos

        val resultado = db.insert("estacionamientos", null, values)
        db.close()
        return resultado != -1L
    }

    // Método para obtener la lista de estacionamientos
    fun obtenerEstacionamientos(): List<Estacionamiento> {
        val listaEstacionamientos = mutableListOf<Estacionamiento>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM estacionamientos", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val ubicacion = cursor.getString(2)
                val descripcion = cursor.getString(3)
                val precio = cursor.getDouble(4)
                val latitud = cursor.getDouble(5)
                val longitud = cursor.getDouble(6)
                val imagen = cursor.getInt(7) // Obtener la imagen

                listaEstacionamientos.add(
                    Estacionamiento(id, nombre, ubicacion, descripcion, precio, latitud, longitud, imagen)
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return listaEstacionamientos
    }

    fun contarSolicitudes(): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM solicitudes", null)
        var count = 0
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return count
    }

    // Método para insertar una solicitud de estacionamiento
    fun insertarSolicitud(idEstacionamiento: Int, fecha: String, horaInicio: String, horaFin: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("id_estacionamiento", idEstacionamiento)
            put("fecha", fecha)
            put("hora_inicio", horaInicio)
            put("hora_fin", horaFin)
        }

        val resultado = db.insert("solicitudes", null, values)
        db.close()
        return resultado != -1L
    }

    // Método para obtener la lista de solicitudes
    fun obtenerSolicitudes(): List<Solicitud> {
        val listaSolicitudes = mutableListOf<Solicitud>()
        val db = this.readableDatabase

        val sql = """
        SELECT s.id, s.fecha, s.hora_inicio, s.hora_fin, 
               e.nombre, e.ubicacion, e.precio, e.imagen
        FROM solicitudes s
        INNER JOIN estacionamientos e ON s.id_estacionamiento = e.id
    """.trimIndent()

        val cursor = db.rawQuery(sql, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val fecha = cursor.getString(1)
                val horaInicio = cursor.getString(2)
                val horaFin = cursor.getString(3)
                val nombreEstacionamiento = cursor.getString(4)
                val ubicacion = cursor.getString(5)
                val precio = cursor.getString(6) // 🔹 Precio ya es String en la BD
                val imagen = cursor.getInt(7) // Imagen guardada en la BD

                Log.d("DatabaseHelper", "Solicitud obtenida: $nombreEstacionamiento, $fecha, $horaInicio - $horaFin")

                listaSolicitudes.add(
                    Solicitud(
                        imagen,
                        nombreEstacionamiento,
                        ubicacion,
                        precio,
                        fecha,
                        horaInicio,
                        horaFin
                    )
                )

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return listaSolicitudes
    }

    companion object {
        private const val DATABASE_NAME = "EstacionaYa.db"
        private const val DATABASE_VERSION = 2
    }
}