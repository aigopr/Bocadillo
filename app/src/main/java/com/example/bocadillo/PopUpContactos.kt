/**
 * Activity Pop Up Contactos.
 * Menú de gestión de contactos.
 *
 * @author Aaron Breglia, Aïna González
 * */
package com.example.bocadillo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity


class PopUpContactos : AppCompatActivity(), View.OnClickListener{

    /**
     * On create
     * Establece el onClickListener a los botones y muestra el layout activity_pop_up_contactos.
     *
     * @param savedInstanceState Instancia.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up_contactos)
        // Botones
        val btn_nuevoChat: Button = findViewById(com.example.bocadillo.R.id.btn_nuevoChat)
        val btn_editar: Button = findViewById(com.example.bocadillo.R.id.btn_editar)
        val btn_block: Button = findViewById(com.example.bocadillo.R.id.btn_block)

        // On Click
        btn_nuevoChat.setOnClickListener(this)
        btn_editar.setOnClickListener(this)
        btn_block.setOnClickListener(this)
    }

    /**
     * On Click
     * Gestión al hacer clic sobre un botón.
     *
     * @param v Instancia del botón pulsado.
     * @see Chat
     * */
    override fun onClick(v : View?){
        when(v?.id){
            com.example.bocadillo.R.id.btn_nuevoChat -> {
                val intent = Intent(this, Chat::class.java)
                startActivity(intent)
            }
            com.example.bocadillo.R.id.btn_editar -> {

            }
            com.example.bocadillo.R.id.btn_block -> {

            }
        }
    }
}