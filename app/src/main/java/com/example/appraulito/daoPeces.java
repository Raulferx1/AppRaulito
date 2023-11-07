package com.example.appraulito;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class daoPeces {
    SQLiteDatabase bd;
    ArrayList<Peces> lista = new ArrayList<Peces>();
    Peces p;
    Context ct;
    String nombreBD = "DBPeces";
    String tabla = "create table if not exists peces(id INTEGER primary key AUTOINCREMENT, nombre text, especie text, longitud text, peso text, hora_captura text)";

    public daoPeces(Context c) {
        this.ct = c;
        bd = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        bd.execSQL(tabla);
    }

    public boolean insertar(Peces p) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", p.getNombre());
        contenedor.put("especie", p.getEspecie());
        contenedor.put("longitud", p.getLongitud());
        contenedor.put("peso", p.getPeso());
        contenedor.put("hora_captura", obtenerHoraActual());
        return (bd.insert("peces", null, contenedor)) > 0;
    }

    public boolean eliminar(int id) {
        return (bd.delete("peces", "id=?", new String[]{String.valueOf(id)})) > 0;
    }

    public boolean editar(Peces p) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", p.getNombre());
        contenedor.put("especie", p.getEspecie());
        contenedor.put("longitud", p.getLongitud());
        contenedor.put("peso", p.getPeso());
        contenedor.put("hora_captura", obtenerHoraActual());
        return (bd.update("peces", contenedor, "id=?", new String[]{String.valueOf(p.getId())}) > 0);
    }

    public ArrayList<Peces> verTodo() {
        lista.clear();
        Cursor cursor = bd.rawQuery("select * from peces", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                lista.add(new Peces(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public Peces verUno(int posicion) {
        Cursor cursor = bd.rawQuery("select * from peces", null);
        cursor.moveToPosition(posicion);
        p = new Peces(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4),
                cursor.getString(5));
        return p;
    }

    private String obtenerHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateAndTime = sdf.format(new Date());
        return currentDateAndTime;
    }
}
