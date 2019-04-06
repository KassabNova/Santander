package com.ds.dummysantander;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

class CustomCardsAdapter implements ListAdapter {
    ArrayList<Tarjetas> arrayList;
    Context context;
    public CustomCardsAdapter(Context context, ArrayList<Tarjetas> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return arrayList.size();
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
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Tarjetas tarjeta = arrayList.get(position);
        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.custom_row, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BaseActivity.class);
                    //intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.putExtra("numTarjeta", tarjeta.getNumTarjeta());
                    intent.putExtra("numCuenta", tarjeta.getNumCuenta());
                    intent.putExtra("saldo", tarjeta.getSaldo());
                    intent.putExtra("tipo", tarjeta.getTipo());
                    intent.putExtra("limiteCredito", tarjeta.getLimiteCredito());

                   context.startActivity(intent);
                }
            });
            TextView tipo=convertView.findViewById(R.id.TxtFecha);
            TextView numero = convertView.findViewById(R.id.TxtOperacion);
            TextView saldo = convertView.findViewById(R.id.TxtSaldo);

            tipo.setText(tarjeta.getTipo());
            numero.setText(Integer.toString(tarjeta.getNumCuenta()));
            saldo.setText(Double.toString(tarjeta.getSaldo()));
        }
        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}
