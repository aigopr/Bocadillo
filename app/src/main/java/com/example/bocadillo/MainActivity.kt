/**
 * Activity principal.
 * Acceso al Log in.
 *
 * @author Aaron Breglia, Aïna González
 * */
package com.example.bocadillo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers.Main
import kotlin.math.sign


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    /**
     * On create
     * Establece el onClickListener a los botones y muestra el layout activity_main.
     *
     * @param savedInstanceState Instancia.
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Firebase Authentication
        auth = Firebase.auth
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        // Google sign in
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Botones
        val btnLogIn: Button = findViewById<Button>(R.id.btnLogIn)
        val btnSignIn: Button = findViewById<Button>(R.id.btnSignIn)
        val btnGoogle: Button = findViewById<Button>(R.id.btnLogIn_Google)

        // On Click
        btnLogIn.setOnClickListener(this)
        btnSignIn.setOnClickListener(this)
        btnGoogle.setOnClickListener(this)

    }

    /**
     * On Click
     * Gestión al hacer clic sobre un botón.
     *
     * @param v Instancia del botón pulsado.
     * @see Pestanas
     * @see SignIn
     * */
    override fun onClick(v : View?)
    {
        when(v?.id)
        {
            // ------------ LOG IN
            R.id.btnLogIn -> {
                // Recoger inputs
                val inputUser: TextInputEditText = findViewById<TextInputEditText>(R.id.login_input_User)
                val inputPass: TextInputEditText = findViewById<TextInputEditText>(R.id.login_input_Pass)
                val user = inputUser.text.toString()
                val pass = inputPass.text.toString()
                if(!user.isEmpty()||!pass.isEmpty()){
                    // Log in
                    logIn(user,pass)
                } else {
                    Toast.makeText(baseContext, "Autenticación fallida.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            // ------------ SIGN IN
            R.id.btnSignIn -> {
                val intentMain = Intent(this,
                SignIn::class.java)
                this.startActivity(intentMain)
            }
            // ------------ LOG IN GOOGLE
            R.id.btnLogIn_Google -> {
                signInGoogle()
            }
        }

    }

    /**
     * Comprueba el Log In.
     *
     * @param email Email.
     * @param password Contraseña.
     * */
    private fun logIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")

        // Log in
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val intentMain = Intent(this,  Pestanas::class.java)
                    this.startActivity(intentMain)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Autenticación fallida.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    /**
     * Cargar ID cuenta de Google.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     * */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Companion.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    /**
     * Autenticación con Google.
     *
     * @param idToken ID de la cuenta de Google.
     * */
    private fun firebaseAuthWithGoogle(idToken: String) {
        // [END_EXCLUDE]
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    val intentMain = Intent(this,  Pestanas::class.java)
                    this.startActivity(intentMain)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Autenticación fallida.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    /**
     * Iniciar sesión con Google.
     * */
    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // ------------ CONSTANTES
    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

}