package com.example.nicollanos.registrologeousuario;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    TextView tvRegistrar;
    Button btnIngresar;
    user user;
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BD1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        helper.insertarRegistro("JuanPerico","Juanito","juan@gmail.com","juan123");
        helper.insertarRegistro("pascal","asd","luth@a.com","1234");

        tvRegistrar = (TextView) findViewById(R.id.tvRegistro);

        tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Registro.class);
                startActivity(i);
            }
        });

        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtUsu = (EditText) findViewById(R.id.txtUsuario);
                EditText txtPass = (EditText) findViewById(R.id.txtContraseña);

           try {

               Cursor cursor = helper.ConsultarUsuPas(txtUsu.getText().toString(), txtPass.getText().toString());
               if(cursor.getCount()>0){
            cursor.moveToFirst();
            //TOMA DATOS DE USUARIO Y LOS ALMACENA PARA POSTERIORES USOS
                user= new user();
                user.fillbycursor(cursor);
                   Intent i = new Intent(getApplicationContext(),nav.class);
                   View view = getCurrentFocus();
                   if (view != null) {
                       InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                       imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                   }
                   startActivity(i);
               }
               else{
                   Toast.makeText(getApplicationContext(),"Usuario y/o Pass Incorrectos",Toast.LENGTH_LONG).show();
               }
               txtUsu.setText("");
               txtPass.setText("");
               txtUsu.findFocus();


           } catch (SQLException e){
               e.printStackTrace();
           }


            }
        });


    }
}
