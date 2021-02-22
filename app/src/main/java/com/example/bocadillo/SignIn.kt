/**
 * Activity Sign In.
 * Acceso al Sign in.
 *
 * @author Aaron Breglia, Aïna González
 * */
package com.example.bocadillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignIn : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    /**a
     *
     * On create
     * Establece el onClickListener a los botones y muestra el layout activity_sign_in.
     *
     * @param savedInstanceState Instancia.
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

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
        val btnSignIn: Button = findViewById<Button>(R.id.btnsignin_new)
        val btnSignInGoogle: Button = findViewById<Button>(R.id.btnsignin_google)

        // On Click
        btnSignIn.setOnClickListener(this)
        btnSignInGoogle.setOnClickListener(this)
    }

    /**
     * On Click
     * Gestión al hacer clic sobre un botón.
     *
     * @param v Instancia del botón pulsado.
     * */
    override fun onClick(v : View?)
    {
        when(v?.id)
        {
            // ------------ REGISTRO
            R.id.btnsignin_new -> {
                // Recoger inputs
                val inputMail: TextInputEditText = findViewById<TextInputEditText>(R.id.signin_input_Mail)
                val inputUser: TextInputEditText = findViewById<TextInputEditText>(R.id.signin_input_User)
                val inputPass: TextInputEditText = findViewById<TextInputEditText>(R.id.signin_input_Pass)
                val inputPass2: TextInputEditText = findViewById<TextInputEditText>(R.id.signin_input_Pass2)
                val mail = inputMail.text.toString()
                val user = inputUser.text.toString()
                val pass = inputPass.text.toString()
                val pass2 = inputPass2.text.toString()
                if(!mail.isEmpty()
                    || !user.isEmpty()
                    || !pass.isEmpty()
                    || !pass2.isEmpty()) {
                    // Sign In
                    if (testPass(pass, pass2)) {
                        signIn(mail, pass)
                    }else{
                        Toast.makeText(baseContext, "Las contraseñas no coinciden.",
                            Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(baseContext, "No se ha podido registrar.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            // ------------ GOOGLE
            R.id.btnsignin_google -> {
                signIn()
            }
        }

    }

    /**
     * Comprueba que las contraseñas coincidan.
     *
     * @param pass Contraseña 1.
     * @param pass2 Contraseña 2.
     */
    private fun testPass(pass: String, pass2: String): Boolean {
        return pass == pass2
    }

    /**
     * Registra un usuario nuevo.
     *
     * @param email Correo electrónico.
     * @param password Contraseña.
     * */
    private fun signIn(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intentMain = Intent(this,  Pestanas::class.java)
                    this.startActivity(intentMain)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "No se ha podido registrar.",
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
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // ------------ CONSTANTES
    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }
}