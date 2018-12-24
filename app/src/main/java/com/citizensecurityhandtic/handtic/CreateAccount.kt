
package com.citizensecurityhandtic.handtic

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.citizensecurityhandtic.handtic.AdminSQLiteOpenHelper
import com.citizensecurityhandtic.handtic.R
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        btnRegistrar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this,"BaseDatos", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("correo", etemail.getText().toString())
            registro.put("nombre", etname.getText().toString())
            registro.put("usuario", etuser.getText().toString())
            registro.put("contraseña", etpassword_createaccount.getText().toString())
            registro.put("confirmar_contraseña", etconfirmPassword.getText().toString())
            bd.insert("cuentas", null, registro)
            bd.close()
            Toast.makeText(this, "Usuario Agregado",Toast.LENGTH_SHORT).show()
        }
    }

}

