/**
 * Activity Pestañas.
 * Contenedor de los fragments.
 *
 * @author Aaron Breglia, Aïna González
 * */
package com.example.bocadillo

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.bocadillo.ui.main.SectionsPagerAdapter

class Pestanas : AppCompatActivity(), View.OnClickListener{

    /**
     * On create
     * Establece el onClickListener a los botones y muestra el layout activity_pestanas.
     *
     * @param savedInstanceState Instancia.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pestanas)

        // Gestión fragments
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // Botones
        val btnPestanas: FloatingActionButton = findViewById(R.id.btnPestanas)

        // On Click
        btnPestanas.setOnClickListener(this)

    }

    /**
     * On Click
     * Gestión al hacer clic sobre un botón.
     *
     * @param v Instancia del botón pulsado.
     * @see AnadirContacto
     * */
    override fun onClick(v : View?)
    {
        when(v?.id)
        {
            // ------------ AÑADIR CONTACTO
            R.id.btnPestanas -> {
                val viewPager: ViewPager = findViewById(R.id.view_pager)

                if(viewPager.currentItem == 0){
                    val intent = Intent(this, AnadirContacto::class.java)
                    startActivity(intent)
                } else if (viewPager.currentItem == 1){
                    val intent = Intent(this, Chat::class.java)
                    startActivity(intent)
                }

            }
        }
    }
}

