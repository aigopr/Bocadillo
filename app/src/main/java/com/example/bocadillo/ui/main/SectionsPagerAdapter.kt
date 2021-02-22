package com.example.bocadillo.ui.main

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bocadillo.Fragment_Ajustes
import com.example.bocadillo.Fragment_Chats
import com.example.bocadillo.Fragment_Contactos
import com.example.bocadillo.R


private val TAB_TITLES = arrayOf(
        R.string.contactos,
        R.string.chats,
    R.string.ajustes
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Fragment_Contactos()
            }
            1 -> {
                Fragment_Chats()
            }
            2 -> {
                Fragment_Ajustes()
            }
            else -> getItem(position)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}