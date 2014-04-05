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
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText editText1,editText2;
	String mail,password;
	String valido;
	
	JSONArray jsonArray;
    ProgressDialog progress_dialog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        
       Button button1 = (Button)findViewById(R.id.button1);
       button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                registerForContextMenu(v);
                openContextMenu(v);
            }
        });
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
	           	 progress_dialog = new ProgressDialog(MainActivity.this);
	             progress_dialog.setMessage("Loading, please wait for a few seconds..");
	             progress_dialog.setCancelable(false);
	             progress_dialog.show();
	             mail=editText1.getText().toString();
	             password=editText2.getText().toString();
	             
	             new TareaLogearte().execute();
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.persorg, menu);
    }
        
    
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.opcion1:
            //Boton persona

                 Intent i = new Intent(this, registroPersona.class);
            	 startActivity(i);

                 return true;

            case R.id.opcion2:
            //Boton organizacion
                 Intent i1 = new Intent(this, registroOrganizacion.class);
            	 startActivity(i1);

            	 return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private class TareaLogearte extends AsyncTask<String, String, Integer> {
        InputStream is = null;
        String json = "";

        @Override
        protected Integer doInBackground(String... urls) {
            ///Dialog
            //Conexion http
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://nvolveu.hol.es/php/Login.php?correo="+mail+"&password="+password);
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
                
                valido=json;
                
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
            validar();

        }
    }

    public void validar(){
    	if(valido.equals("1\n")){
    		Toast.makeText(getApplicationContext(), "Accediendo", Toast.LENGTH_SHORT).show();
    		base_de_datosdbHelper helper = new base_de_datosdbHelper(this);
            helper.abrir();
            helper.guardarLogin(0,mail,password,1);
            helper.close();
            ejecutar_gps();
    	}
    	else
    		Toast.makeText(getApplicationContext(), "Error de autentificacion", Toast.LENGTH_LONG).show();

    		
    }
    
    public void ejecutar_gps() {
        Intent i = new Intent(this, GpsActivity.class);
        startActivity(i);
    }
    
	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}
	
	@Override
    public void onBackPressed()
    {
         super.onBackPressed();
         finish();
    }
}
