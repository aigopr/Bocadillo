/**
 * Fragment Ajustes.
 * Muestra la pestaña de Ajustes.
 *
 * @author Aaron Breglia, Aïna González
 * */
package com.example.bocadillo

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Fragment_Ajustes : Fragment(), View.OnClickListener{

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    /**
     * On create view
     * Establece el onClickListener a los botones y muestra el layout fragment_ajustes.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState Instancia.
     * @return Vista.
     */
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_ajustes, container, false)

        // Firebase Authentication
        auth = Firebase.auth
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        // Google sign in
        googleSignInClient = GoogleSignIn.getClient(this.requireActivity(), gso)

        // Botones
        val btnLogOut: Button = view.findViewById(R.id.btnLogOut)

        // On Click
        btnLogOut.setOnClickListener(this)

        return view
    }

    /**
     * On click
     * Gestión al hacer clic sobre un botón.
     *
     * @param v Instancia del botón pulsado.
     */
    override fun onClick(v : View?) {
        when(v?.id){
            // ------------ LOG OUT
            R.id.btnLogOut -> {
                signOut()
            }
        }
    }

    /**
     * Cerrar sesión actual.
     * */
    private fun signOut() {
        // Firebase sign out
        auth.signOut()
        // Google sign out
        googleSignInClient.signOut().addOnSuccessListener {
            // Cerrar Activity
            val activity: Activity? = activity
            activity?.finish()
        }
    }

}