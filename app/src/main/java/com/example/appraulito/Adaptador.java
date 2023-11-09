package com.example.appraulito;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private ArrayList<Peces> lista;
    private daoPeces dao;
    private Peces p;
    private Activity a;

    public Adaptador(Activity a, ArrayList<Peces> lista, daoPeces dao) {
        this.lista = lista;
        this.a = a;
        this.dao = dao;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }

        p = lista.get(posicion);

        TextView nombre = v.findViewById(R.id.nombre);
        TextView especie = v.findViewById(R.id.especie);
        TextView longitud = v.findViewById(R.id.longitud);
        TextView peso = v.findViewById(R.id.peso);
        TextView horaCaptura = v.findViewById(R.id.hora_captura);
        Button editar = v.findViewById(R.id.btn_editar);
        Button eliminar = v.findViewById(R.id.btn_eliminar);

        nombre.setText(p.getNombre());
        especie.setText(p.getEspecie());
        longitud.setText(p.getLongitud());
        peso.setText(p.getPeso());
        horaCaptura.setText(p.getHoraCaptura());

        editar.setTag(posicion);
        eliminar.setTag(posicion);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();

                final EditText nombre = dialog.findViewById(R.id.et_nombre);
                final EditText especie = dialog.findViewById(R.id.et_especie);
                final EditText longitud = dialog.findViewById(R.id.et_longitud);
                final EditText peso = dialog.findViewById(R.id.et_peso);
                final EditText horaCaptura = dialog.findViewById(R.id.et_hora_captura);
                Button guardar = dialog.findViewById(R.id.btn_agregar);
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);

                p = lista.get(pos);
                nombre.setText(p.getNombre());
                especie.setText(p.getEspecie());
                longitud.setText(p.getLongitud());
                peso.setText(p.getPeso());
                horaCaptura.setText(p.getHoraCaptura());

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            p = new Peces(p.getId(), nombre.getText().toString(),
                                    especie.getText().toString(),
                                    longitud.getText().toString(),
                                    peso.getText().toString(),
                                    horaCaptura.getText().toString());
                            dao.editar(p);
                            lista = dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(a, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                p = lista.get(pos);
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("¿Estás seguro de eliminar?");
                del.setCancelable(false);
                del.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(p.getId());
                        lista = dao.verTodo();
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                del.show();
            }
        });

        return v;
    }
}
