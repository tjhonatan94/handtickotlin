package com.citizensecurityhandtic.handtic

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createHere.setOnClickListener {
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }

        btnAccederLog.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "BaseDatos", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select usuario, contraseña from cuentas where usuario='${etusername.text}'", null)

            if (fila.moveToFirst()) {

                if(fila.getString(0) == etusername.text.toString() && fila.getString(1) == etepassword.text.toString()){


                    val intent = Intent(this, MapsActivity::class.java)
                    startActivity(intent)
                }

            }else{
                Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show()

            }
        }


    }
}
