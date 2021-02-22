/**
 * Activity Añadir contacto.
 * Permite agregar un nuevo contacto.
 *
 * @author Aaron Breglia, Aïna González
 * */
package com.example.bocadillo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity

class AnadirContacto : AppCompatActivity(), View.OnClickListener {

    /**
     * On create
     * Establece el onClickListener a los botones y muestra el layout activity_anadir_contacto.
     *
     * @param savedInstanceState Instancia.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_contacto)

        //Botones
        val btnGuardarContacto: Button = findViewById(R.id.btnGuardarContacto)

        //On Click
        btnGuardarContacto.setOnClickListener(this)
    }

    /**
     * On Click
     * Gestión al hacer clic sobre un botón.
     *
     * @param V Instancia del botón pulsado.
     * @see Pestanas
     */
    override fun onClick(V : View?)
    {
        val intent = Intent(this, Pestanas::class.java)
        startActivity(intent)
    }

}