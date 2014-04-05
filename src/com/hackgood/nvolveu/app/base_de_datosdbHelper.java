package com.hackgood.nvolveu.app;

/**
 * Created by Adrian on 04/04/2014.
 */


        import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by adrian on 30/10/13.
 */
public class base_de_datosdbHelper  extends SQLiteOpenHelper {

    public base_de_datosdbHelper(Context context) {
        super(context,"puntuacionesDb", null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(this.getClass().toString(), "Creando base de datos");
        //Se ejecuta la sentencia SQL de creaciÃ³n de la tabla
        db.execSQL("CREATE TABLE Login (id_persona INTEGER PRIMARY KEY AUTOINCREMENT, correo VARCHAR(300), password VARCHAR(20), automatico INTEGER)");

        Log.i(this.getClass().toString(), "tabla creada");
        db.execSQL("INSERT INTO Login (id_persona,correo,password,automatico) VALUES (1,'default@default.com','123',1);");

        Log.i(this.getClass().toString(), "datos insertados");

        db.execSQL("CREATE TABLE Configuracion (RangoDistancia INTEGER)");

        Log.i(this.getClass().toString(), "tabla Configuracion creada");
        db.execSQL("INSERT INTO Configuracion (RangoDistancia) VALUES (1);");

        Log.i(this.getClass().toString(), "datos insertados");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Login");
        db.execSQL("DROP TABLE IF EXISTS Configuracion");
        onCreate(db);
    }
    public void abrir(){
        this.getWritableDatabase();
    }
    public void cerrar(){
        this.close();
    }
    public void borrarTabla(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Login");
        db.execSQL("DROP TABLE IF EXISTS Configuracion");
    }
    public void guardarLogin(int id_persona,String correo,String password,int automatico) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE login SET id_persona = "+id_persona+", correo='"+correo+"', password = '"+password+"', automatico= "+automatico+";");
    }
    public void guardarConfiguracion(int RangoDistancia) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE configuracion SET RangoDistancia = "+RangoDistancia+";");
    }
    public Login leerLogin() {
        Login loginme= new Login(0,"","",0);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id_persona,correo,password,automatico FROM " +
                "Login;", null);
        while (cursor.moveToNext()){
            loginme =  new Login(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));

        }
        cursor.close();
        return loginme;
    }
    public int leerConfiguracion() {
         int RangoDistancia =0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT RangoDistancia FROM " +
                "Configuracion; ", null);
        while (cursor.moveToNext()){
            RangoDistancia=cursor.getInt(0);

        }
        cursor.close();
        return RangoDistancia;
    }
}
