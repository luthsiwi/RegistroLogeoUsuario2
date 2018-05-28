package com.example.nicollanos.registrologeousuario;

import android.database.Cursor;

public class user {

    static int id;
    static String nombre;
    static String distrito;
    static String email;
    static String password;
static Cursor cursor;

public void fillbycursor(Cursor asd){
    this.cursor=asd;
    id=cursor.getInt(0);
    nombre=cursor.getString(1);
    distrito=cursor.getString(2);
    email=cursor.getString(3);
    password=cursor.getString(4);
}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
