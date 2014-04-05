package com.hackgood.nvolveu.app;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class lista extends ListActivity {
	
    boolean acabado;
    String nombreCreador;
    private DrawerLayout NavDrawerLayout;
    private LinearLayout NavList;
    ArrayList<voluntariado_organizacion> evento_organizaciones= new ArrayList<voluntariado_organizacion>();
    ArrayList <voluntariado_persona> evento_personas= new ArrayList<voluntariado_persona>();
    ArrayList<Object> eventos = new ArrayList<Object>();

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {  //ABG CADA ITEM DEL MENU LO ELEGIMOS CON UN SWITCH DE LA FORMA R.ID.ITEMDELMENU
            case R.id.menu_todo:
                ArrayList<Object> eventos2 = new ArrayList<Object>();
                if (evento_personas != null) {
                    for (int j = 0; j < evento_personas.size(); j++) {
                        eventos2.add(evento_personas.get(j));
                    }
                }
                if (evento_organizaciones != null) {
                    for (int j = 0; j < evento_organizaciones.size(); j++) {
                        eventos2.add(evento_organizaciones.get(j));
                    }
                }
                setListAdapter(new MiAdaptador(this, eventos2));
                break;
            case R.id.voluntariado_organizacion:
                ArrayList<Object> eventos3 = new ArrayList<Object>();
                if (evento_organizaciones != null) {
                    for (int j = 0; j < evento_organizaciones.size(); j++) {
                        eventos3.add(evento_organizaciones.get(j));
                    }
                }
                setListAdapter(new MiAdaptador(this, eventos3));
                break;
            case R.id.voluntariado_personas:
                ArrayList<Object> eventos4 = new ArrayList<Object>();
                if (evento_personas != null) {
                    for (int j = 0; j < evento_personas.size(); j++) {
                        eventos4.add(evento_personas.get(j));
                    }
                }
                setListAdapter(new MiAdaptador(this, eventos4));
                break;

            default:
                return false;

        }
        return false;

    }

        @Override

        //ABG QUE  VAMOS A HACER EN RESPUESTA A LA ELECCIOON DE CADA ITEM DEL MENU
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        base_de_datosdbHelper helper = new base_de_datosdbHelper(this);
        helper.abrir();
        nombreCreador=helper.leerLogin().getNombre();
        helper.cerrar();
        NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavList = (LinearLayout) findViewById(R.id.lista);
        NavDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        TextView nombre = (TextView)findViewById(R.id.nombre);
        nombre.setText(nombreCreador);
        Button bt_elegir_radio = (Button)findViewById(R.id.bt_elegir_radio);
        Button bt_ver_mapa = (Button)findViewById(R.id.bt_volver_maps);
        bt_elegir_radio.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                registerForContextMenu(v);
                openContextMenu(v);
            }
        });
        bt_ver_mapa.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            	ejecutar_gps();
            }
        });

        evento_organizaciones= (ArrayList<voluntariado_organizacion>) getIntent().getSerializableExtra("evento_organizaciones");

        evento_personas=(ArrayList<voluntariado_persona>) getIntent().getSerializableExtra("evento_personas");


        if(evento_personas!=null)  {
                for (int j=0; j<evento_personas.size();j++){
                    eventos.add(evento_personas.get(j));
                }
        }
        if(evento_organizaciones!=null) {
                for (int j = 0; j < evento_organizaciones.size(); j++) {
                    eventos.add(evento_organizaciones.get(j));
                }
        }
            setListAdapter(new MiAdaptador(this, eventos));
        }

    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        Intent i = new Intent(this, descripcion.class);
        Bundle extras=new Bundle();
        extras.putSerializable("eventos",eventos);
        extras.putInt("position",position);
        i.putExtras(extras);
        startActivity(i);
    } //ABG AL CLICAR EN UN EVENTO LLAMAMOS A DESCRIPCION Y LE PASAMOS LA INFORMACION DE DICHO EVENTO NECESARIA

    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.distancia, menu);
    }


    public boolean onContextItemSelected(MenuItem item) {
        base_de_datosdbHelper helper = new base_de_datosdbHelper(this);
        helper.abrir();
        switch (item.getItemId()) {
            case R.id.cinco:
                //Boton persona
                helper.guardarConfiguracion(1);
                helper.close();
                ejecutar_gps();
                return true;

            case R.id.diez:
                //Boton organizacion
                helper.guardarConfiguracion(2);
                helper.close();
                ejecutar_gps();

                return true;
            case R.id.veinte:
                //Boton organizacion
                helper.guardarConfiguracion(3);
                helper.close();
                ejecutar_gps();

                return true;
            case R.id.mas_grande:
                //Boton organizacion
                helper.guardarConfiguracion(4);
                helper.close();
                ejecutar_gps();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    
    public void ejecutar_gps() {
        Intent i = new Intent(this, GpsActivity.class);
        startActivity(i);
    }

}
