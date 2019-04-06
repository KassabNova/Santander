package com.ds.dummysantander;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {

    private Button btnsucursal, btnTransferencias;
    private TextView lblSaldoTarjeta;
    private  Tarjetas tarjeta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        String numTarjeta, tipo, limiteCredito;
        int numCuenta;
        double saldo;

        lblSaldoTarjeta = (TextView)findViewById(R.id.LblSaldoTarjeta);

        Intent intent = getIntent();
        numTarjeta = intent.getStringExtra("numTarjeta");
        tipo = intent.getStringExtra("Tipo");
        limiteCredito = intent.getStringExtra("limiteCredito");
        numCuenta = intent.getIntExtra("numCuenta", 0);
        saldo = intent.getDoubleExtra("saldo", 0);

        tarjeta = new Tarjetas(numTarjeta, numCuenta, saldo, tipo, limiteCredito);

        lblSaldoTarjeta.setText(Double.toString(tarjeta.getSaldo()));

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
                startActivityForResult(intentTransferencias, 0);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
