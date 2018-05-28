package com.example.nicollanos.registrologeousuario;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class Registro extends AppCompatActivity {

    Button btnGrabarUsu;
    EditText txtNomUsu,txtDisUsu,txtCorUsu,txtPassUsu;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BD1",null,1);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnGrabarUsu = (Button) findViewById(R.id.btnRegistrarUsu);
        txtNomUsu = (EditText) findViewById(R.id.txtNomUsu);
        txtDisUsu = (EditText) findViewById(R.id.txtDisUsuario);
        txtCorUsu = (EditText) findViewById(R.id.txtCorUsu);
        txtPassUsu = (EditText) findViewById(R.id.txtContraseña);

        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                helper.insertarRegistro(String.valueOf(txtNomUsu.getText()),
                        String.valueOf(txtDisUsu.getText()),
                        String.valueOf(txtCorUsu.getText()),
                        String.valueOf(txtPassUsu.getText()));
                helper.cerrar();

                Toast.makeText(getApplicationContext(),"Registro Almacenado con Exito", Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });
    }
}
