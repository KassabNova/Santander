package com.ds.dummysantander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransferenciasActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias);

        final String numTarjeta, tipo, limiteCredito, usuario;
        final int numCuenta;
        final double saldo;

        Intent intent = getIntent();
        numTarjeta = intent.getStringExtra("numTarjeta");
        tipo = intent.getStringExtra("Tipo");
        limiteCredito = intent.getStringExtra("limiteCredito");
        numCuenta = intent.getIntExtra("numCuenta", 0);
        saldo = intent.getDoubleExtra("saldo", 0);
        usuario = intent.getStringExtra("usuario");

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String opcion = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                switch(expandableListTitle.get(groupPosition)){
                    case "Transferencias":
                         Intent intent = new Intent(TransferenciasActivity.this, TransferenciasDetailActivity.class);
                         intent.putExtra("CASO", opcion);
                         intent.putExtra("numTarjeta", numTarjeta);
                         intent.putExtra("Tipo", tipo);
                         intent.putExtra("limiteCredito", limiteCredito);
                         intent.putExtra("numCuenta", numCuenta);
                         intent.putExtra("saldo", saldo);
                         intent.putExtra("usuario", usuario);
                         startActivityForResult(intent, 0);
                        break;
                    case "Compras":
                        Intent intentCompras = new Intent(TransferenciasActivity.this, ComprasDetailActivity.class);
                        intentCompras.putExtra("CASO", opcion);
                        intentCompras.putExtra("numTarjeta", numTarjeta);
                        intentCompras.putExtra("Tipo", tipo);
                        intentCompras.putExtra("limiteCredito", limiteCredito);
                        intentCompras.putExtra("numCuenta", numCuenta);
                        intentCompras.putExtra("saldo", saldo);
                        intentCompras.putExtra("usuario", usuario);
                        startActivityForResult(intentCompras, 0);
                        break;
                    case "Realizar pago":
                        Intent intentPago = new Intent(TransferenciasActivity.this, PagosDetailActivity.class);
                        intentPago.putExtra("CASO", opcion);
                        intentPago.putExtra("numTarjeta", numTarjeta);
                        intentPago.putExtra("Tipo", tipo);
                        intentPago.putExtra("limiteCredito", limiteCredito);
                        intentPago.putExtra("numCuenta", numCuenta);
                        intentPago.putExtra("saldo", saldo);
                        intentPago.putExtra("usuario", usuario);
                        startActivityForResult(intentPago, 0);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
