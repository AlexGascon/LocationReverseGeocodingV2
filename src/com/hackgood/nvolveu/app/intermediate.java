package com.hackgood.nvolveu.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Adrian on 05/04/2014.
 */
public class intermediate extends Activity {
    JSONArray jsonArray;
    ProgressDialog progress_dialog;
    ArrayList<voluntariado_organizacion> evento_organizaciones= new ArrayList<voluntariado_organizacion>();
    ArrayList <voluntariado_persona> evento_personas= new ArrayList<voluntariado_persona>();
    ArrayList<Object> eventos = new ArrayList<Object>();
    Double latitud;
    Double longitud;
    Double latitudMax;
    Double latitudMin;
    Double longitudMax;
    Double longitudMin;
    int parametroDistancia;
    Double valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermediate); 
        
        base_de_datosdbHelper helper = new base_de_datosdbHelper(this);
        helper.abrir();
        parametroDistancia = helper.leerConfiguracion();
        helper.close();
        
        switch(parametroDistancia){
	        case 1: valor = 0.04;
	        case 2: valor = 0.1;
	        case 3: valor = 0.15;
	        case 4: valor = 5.00;
	        default: valor = 0.04;
        }
        
        latitud = getIntent().getDoubleExtra("latitud",0);
        longitud = getIntent().getDoubleExtra("longitud",0);
        latitudMax = latitud + 0.04;
        latitudMin = latitud - 0.04;
        longitudMax = longitud + 0.04;
        longitudMin = longitud - 0.04;

        progress_dialog = new ProgressDialog(intermediate.this);
        progress_dialog.setMessage("Loading, please wait for a few seconds..");
        progress_dialog.setCancelable(false);
        progress_dialog.show();
        new TareaEventosOrganizacionCercanos().execute();
    }
    private class TareaEventosOrganizacionCercanos extends AsyncTask<String, String, Integer> {
        InputStream is = null;
        String json = "";

        @Override
        protected Integer doInBackground(String... urls) {
            ///Dialog
            //Conexion http
            HttpClient client = new DefaultHttpClient();
            HttpPost post=new HttpPost("http://nvolveu.hol.es/php/CercanosOrganizacion.php?longMin="+longitudMin+"&longMax="+longitudMax+"&latMin="+latitudMin+"&latMax="+latitudMax);
            HttpResponse response = null;
            try {
                response = client.execute(post);
                HttpEntity httpEntity = response.getEntity();
                is = httpEntity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }

            try {

                jsonArray = new JSONArray(json);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            try{
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = jsonArray.getJSONObject(i);
                        evento_organizaciones.add(new voluntariado_organizacion(jsonObject.getString("fecha"),jsonObject.getString("nombre"),jsonObject.getString("informacion"),jsonObject.getDouble("latitud"),jsonObject.getDouble("longitud"),jsonObject.getString("titulo"),jsonObject.getInt("recordatorio"),jsonObject.getInt("cantidad_max"),jsonObject.getInt("cantidad"),jsonObject.getString("nombre_categoria"),jsonObject.getString("nombre_enfermedad")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }} catch (Exception e){
                Log.e("error",e.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getApplicationContext(), R.string.no_conexion,
                                Toast.LENGTH_LONG).show();


                        // TODO Auto-generated method stub
                    }
                });

            }
            return null;
        }
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            new TareaEventosPersonasCercanos().execute();

        }


    }


    private class TareaEventosPersonasCercanos extends AsyncTask<String, String, Integer> {
        InputStream is = null;
        String json = "";

        @Override
        protected Integer doInBackground(String... urls) {
            ///Dialog
            //Conexion http
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://nvolveu.hol.es/php/CercanosPersona.php?longMin="+longitudMin+"&longMax="+longitudMax+"&latMin="+latitudMin+"&latMax="+latitudMax);
            HttpResponse response = null;
            try {
                response = client.execute(post);
                HttpEntity httpEntity = response.getEntity();
                is = httpEntity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }

            try {

                jsonArray = new JSONArray(json);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = jsonArray.getJSONObject(i);
                        evento_personas.add(new voluntariado_persona(jsonObject.getString("fecha"), jsonObject.getString("nombre"), jsonObject.getString("informacion"), jsonObject.getDouble("latitud"), jsonObject.getDouble("longitud"), jsonObject.getString("titulo"), jsonObject.getInt("recordatorio"), jsonObject.getInt("cantidad_max"), jsonObject.getInt("cantidad")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            } catch (Exception e) {
                Log.e("error", e.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getApplicationContext(), R.string.no_conexion,
                                Toast.LENGTH_LONG).show();


                        // TODO Auto-generated method stub
                    }
                });

            }
            return null;
        }

        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            progress_dialog.cancel();

            ejecutar_lista();

        }
    }
    public void ejecutar_lista() {
        Intent i = new Intent(this, lista.class);
        Bundle extras=new Bundle();
        extras.putSerializable("evento_organizaciones",evento_organizaciones);
        i.putExtras(extras);
        Bundle extras2=new Bundle();
        extras2.putSerializable("evento_personas",evento_personas);
        i.putExtras(extras2);

        startActivity(i);
    }
}
