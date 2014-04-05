package com.hackgood.nvolveu.app;


import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MiAdaptador extends BaseAdapter { //CADA ELEMNTO DE LA LISTA

    private final Activity actividad;
    private final ArrayList<Object> lista;
    private ImageView editText2;
    private TextView editText1;

    public MiAdaptador(Activity actividad, ArrayList<Object> lista) {
        super();
        this.actividad = actividad;
        this.lista = lista;
    }


    public View getView(int position, View convertView,
                        ViewGroup parent) {
    		
    	
        LayoutInflater inflater = actividad.getLayoutInflater();
        Object evento= lista.get(position);
        String nombreEvento= evento.getClass().getName();

        View view = inflater.inflate(R.layout.elemento_lista, null,
                true);
        assert view != null;
        editText2=(ImageView)view.findViewById(R.id.imageView1);	
    	editText1=(TextView)view.findViewById(R.id.textView2);
        TextView titulo =(TextView)view.findViewById(R.id.titulo);
        if (nombreEvento.equals("com.hackgood.nvolveu.app.voluntariado_organizacion")){
            voluntariado_organizacion voluntariado_org= (voluntariado_organizacion)lista.get(position);
            titulo.setText(voluntariado_org.getTitulo());
            Drawable drawable = actividad.getResources().getDrawable(R.drawable.barrazul);
            editText2.setImageDrawable(drawable);
            editText1.setText(voluntariado_org.getNombre_creador());
            
        }
        else{
            voluntariado_persona voluntariado_per= (voluntariado_persona)lista.get(position);
            titulo.setText(voluntariado_per.getTitulo());
            Drawable drawable2 = actividad.getResources().getDrawable(R.drawable.morado);
            editText2.setImageDrawable(drawable2);
            editText1.setText(voluntariado_per.getNombre_creador()); 

        }

        return view;
    }

    public int getCount() {
        return lista.size();
    }

    public Object getItem(int arg0) {
        return lista.get(arg0);
    }

    public long getItemId(int position) {
        return position;
    }
}