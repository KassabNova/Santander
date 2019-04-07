package com.ds.dummysantander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BaseActivity extends AppCompatActivity {

    private Button btnsucursal, btnTransferencias;
    private TextView lblSaldoTarjeta;
    //private  Tarjetas tarjeta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        final String numTarjeta, tipo, limiteCredito, usuario;
        final int numCuenta;
        final double saldo;

        lblSaldoTarjeta = (TextView)findViewById(R.id.LblSaldoTarjeta);

        final Intent intent = getIntent();
        numTarjeta = intent.getStringExtra("numTarjeta");
        tipo = intent.getStringExtra("tipo");
        limiteCredito = intent.getStringExtra("limiteCredito");
        numCuenta = intent.getIntExtra("numCuenta", 0);
        saldo = intent.getDoubleExtra("saldo", 0);
        usuario = intent.getStringExtra("usuario");

        //tarjeta = new Tarjetas(numTarjeta, numCuenta, saldo, tipo, limiteCredito);

        NumberFormat formatter = new DecimalFormat("#,###.##");
        String saldoCurrency = formatter.format(saldo);
        String saldoString = "$"+saldoCurrency;

        lblSaldoTarjeta.setText(saldoString);

        btnsucursal =(Button)findViewById(R.id.BtnSucursales);
        btnsucursal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, SucursalActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        btnTransferencias = (Button)findViewById(R.id.BtnTransferencias);
        btnTransferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTransferencias = new Intent(BaseActivity.this, TransferenciasActivity.class);
                intentTransferencias.putExtra("numTarjeta", numTarjeta);
                intentTransferencias.putExtra("Tipo", tipo);
                intentTransferencias.putExtra("limiteCredito", limiteCredito);
                intentTransferencias.putExtra("numCuenta", numCuenta);
                intentTransferencias.putExtra("saldo", saldo);
                intentTransferencias.putExtra("usuario", usuario);
                startActivityForResult(intentTransferencias, 0);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
