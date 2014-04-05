package com.hackgood.nvolveu.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registroPersona extends Activity{
	
	JSONArray jsonArray;
    ProgressDialog progress_dialog;
    
    EditText texview,texview2,texview3;
    String name,mail,password;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarsepersona);
        
        texview=(EditText)findViewById(R.id.editText1);
        texview2=(EditText)findViewById(R.id.editText4);
        texview3=(EditText)findViewById(R.id.editText5);
        

    }
    
    private class TareaEventosPersonas extends AsyncTask<String, String, Integer> {
        InputStream is = null;
        String json = "";

        @Override
        protected Integer doInBackground(String... urls) {
            ///Dialog
            //Conexion http
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://nvolveu.hol.es/php/Registro.php?nombre="+name+"&correo="+mail+"&password="+password);
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
            Toast.makeText(getApplicationContext(), "Registrado con exito!!", Toast.LENGTH_LONG).show();
            returnActivity();

        }
    }
    
    public void onClickSend(View v) {
    	 progress_dialog = new ProgressDialog(registroPersona.this);
         progress_dialog.setMessage("Loading, please wait for a few seconds..");
         progress_dialog.setCancelable(false);
         progress_dialog.show();
         name=texview.getText().toString();
         mail=texview2.getText().toString();
         password=texview3.getText().toString();
         
         new TareaEventosPersonas().execute();
	}

    public void returnActivity(){
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }

}

