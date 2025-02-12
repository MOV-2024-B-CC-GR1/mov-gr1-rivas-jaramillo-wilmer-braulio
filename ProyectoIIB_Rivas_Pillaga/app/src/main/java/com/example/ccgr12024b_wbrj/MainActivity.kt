package com.example.ccgr12024b_wbrj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnVerEstacionamientos = findViewById<Button>(R.id.btnVerEstacionamientos)
        val btnVerSolicitudes = findViewById<Button>(R.id.btnVerSolicitudes)

        btnVerEstacionamientos.setOnClickListener {
            startActivity(Intent(this, EstacionamientosActivity::class.java))
        }

        btnVerSolicitudes.setOnClickListener {
            startActivity(Intent(this, SolicitudesActivity::class.java))
        }
    }
}
