package com.hackgood.nvolveu.app;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Adrian on 05/04/2014.
 */
public class descripcion extends Activity {
    ArrayList<voluntariado_organizacion> evento_organizaciones= new ArrayList<voluntariado_organizacion>();
    ArrayList <voluntariado_persona> evento_personas= new ArrayList<voluntariado_persona>();
    ArrayList<Object> eventos = new ArrayList<Object>();
    int position;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descripcion);
        TextView titulo= (TextView)findViewById(R.id.titulo);
        TextView informacion= (TextView)findViewById(R.id.informacion);
        TextView fecha= (TextView)findViewById(R.id.fecha);
        TextView cantidad_max= (TextView)findViewById(R.id.cantidad_max);
        ImageView imagen=(ImageView)findViewById(R.id.barrita);	
        eventos= (ArrayList<Object>) getIntent().getSerializableExtra("eventos");
        position=getIntent().getIntExtra("position",1);
        Object evento= eventos.get(position);
        String nombreEvento= evento.getClass().getName();

        if (nombreEvento.equals("com.hackgood.nvolveu.app.voluntariado_organizacion")){
            voluntariado_organizacion voluntariado_org= (voluntariado_organizacion)eventos.get(position);
            titulo.setText(voluntariado_org.getTitulo());
            informacion.setText(voluntariado_org.getInformacion());
            fecha.setText(voluntariado_org.getFecha());
            cantidad_max.setText(Integer.toString(voluntariado_org.getCantidadMax()));
            Drawable drawable = getResources().getDrawable(R.drawable.barrazul);
            imagen.setImageDrawable(drawable);

        }
        else{
            voluntariado_persona voluntariado_per= (voluntariado_persona)eventos.get(position);
            titulo.setText(voluntariado_per.getTitulo());
            informacion.setText(voluntariado_per.getInformacion());
            fecha.setText(voluntariado_per.getFecha());
            cantidad_max.setText(Integer.toString(voluntariado_per.getCantidadMax()));
            Drawable drawable = getResources().getDrawable(R.drawable.morado);
            imagen.setImageDrawable(drawable);

        }

    }

    }
