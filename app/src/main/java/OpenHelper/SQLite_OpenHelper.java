package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite_OpenHelper extends SQLiteOpenHelper {

    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    //crear estructura de las tablas
    public void onCreate(SQLiteDatabase db) {
        String query = "create table usuarios(_ID integer primary key autoincrement,Nombre text," +
                "Distrito text,Correo text, Password text);";

        db.execSQL(query);


        String consulta = "create table maquinaria(_ID integer primary key autoincrement,Nombre text," +
                "Informacion text);";
        db.execSQL(consulta);


    }


    @Override
    //modificar estructura BD
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //METODO QUE PERMITE ABRIR LA BD
    public void abrir(){
        this.getWritableDatabase();
    }

    //METODO QUE PERMITE CERRAR LA BD
    public void cerrar(){
        this.close();
    }

    //METODO QUE PERMITE INSERTAR REGISTROS EN LA TABLA USUARIOS
    public void insertarRegistro(String nombre,String distrito,String correo, String password){
          ContentValues valores = new ContentValues();
          valores.put("Nombre",nombre);
          valores.put("Distrito",distrito);
          valores.put("Correo",correo);
          valores.put("Password",password);
          this.getWritableDatabase().insert("usuarios",null,valores);

    }

    //METODO QUE PERMITE INSERTAR REGISTROS EN LA TABLA MAQUINARIA
    public void insertarRegistrosMaquinarias(String nombre,String Informacion){
        ContentValues valores = new ContentValues();
        valores.put("Nombre",nombre);
        valores.put("Informacion",Informacion);
        this.getWritableDatabase().insert("maquinaria",null,valores);

    }

    //METODO QUE PERMITE VALIDAR SI EL USUARIO EXISTE
    public Cursor ConsultarUsuPas(String usuario,String password) throws SQLException {
        Cursor mcursor = null;
        mcursor = this.getReadableDatabase().query("usuarios",new String[]{"_ID","Nombre","Distrito","Correo",
                "Password"},"Correo like '"+usuario+"' and Password like '"+password+"'"
                ,null,null,null,null);
        return mcursor;
    }


    public Cursor obtenerInformacion(int id){
        SQLiteDatabase db = this.getWritableDatabase();
  Cursor res = db.rawQuery("select * from maquinaria where _ID = "+id,null);
  res.moveToFirst();
        return res;
    }


}
