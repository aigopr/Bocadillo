/**
 * Fragment Chats.
 * Muestra la pestaña de Chats.
 *
 * @author Aaron Breglia, Aïna González
 * */
package com.example.bocadillo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class Fragment_Chats : Fragment(), View.OnClickListener {

    /**
     * On create view
     * Establece el onClickListener a los botones y muestra el layout fragment_chats.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState Instancia.
     * @return Vista.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chats, container, false)

        // Botones
        val contacto_num1 = view.findViewById<CardView>(R.id.contacto_num1)
        val contacto_num2 = view.findViewById<CardView>(R.id.contacto_num2)
        val contacto_num3 = view.findViewById<CardView>(R.id.contacto_num3)

        // On Click
        contacto_num1.setOnClickListener(this)
        contacto_num2.setOnClickListener(this)
        contacto_num3.setOnClickListener(this)

        return view
    }

    /**
     * On click
     * Gestión al hacer clic sobre un botón.
     *
     * @param v Instancia del botón pulsado.
     * @see Chat
     */
    override fun onClick(v : View?)
    {
        val intent = Intent(activity, Chat::class.java)
        startActivity(intent)
    }
}