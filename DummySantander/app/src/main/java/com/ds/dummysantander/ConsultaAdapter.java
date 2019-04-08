package com.ds.dummysantander;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class ConsultaAdapter implements ListAdapter {

    private TextView txtOperacion, txtSaldo, txtFecha;

    private final Context context;
    private final ArrayList<Consulta> consultas;

    public ConsultaAdapter(Context context, ArrayList<Consulta> consultas) {
        this.context = context;
        this.consultas = consultas;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return consultas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public View getView(int position, View view, ViewGroup parent) {
        final Consulta consulta = consultas.get(position);
        if(view==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.list_consulta, null);

            TextView txtFecha = (TextView) view.findViewById(R.id.TxtFecha);;
            TextView txtMonto = (TextView) view.findViewById(R.id.TxtSaldo);
            TextView txtDetalle = (TextView) view.findViewById(R.id.TxtOperacion);

            txtFecha.setText(consulta.getFecha());
            txtMonto.setText(Double.toString(consulta.getMonto()));
            //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            txtDetalle.setText(consulta.getDetalle());

        }


        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return consultas.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
