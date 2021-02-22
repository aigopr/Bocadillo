/**
 * Activity Chat.
 * Muestra el Chat con un usuario.
 *
 * @author Aaron Breglia, Aïna González
 * */
package com.example.bocadillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Chat : AppCompatActivity(), View.OnClickListener {

    /**
     * On create
     * Establece el onClickListener a los botones y muestra el layout activity_chat.
     *
     * @param savedInstanceState Instancia.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }

    /**
     * On click
     * Gestión al hacer clic sobre un botón.
     *
     * @param V Instancia del botón pulsado.
     */
    override fun onClick(V : View?)
    {
        val intent = Intent(this, Pestanas::class.java)
        startActivity(intent)
    }
}