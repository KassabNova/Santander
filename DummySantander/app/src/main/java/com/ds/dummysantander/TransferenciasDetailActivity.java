package com.ds.dummysantander;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class TransferenciasDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias_detail);

        final String numTarjeta, tipo, limiteCredito, usuario;
        final String opcion;
        final int numCuenta;
        final double saldo;
        final boolean pagosTerceros;
        TextView lblCuentaOrigen, lblSaldoTransferencia, lblTituloTransferencia, lblCredito;
        final EditText txtNumeroTarjeta, txtCantidadTransferencia, txtMotivoTransferencia;
        Button btnTransferencia;


        lblTituloTransferencia = findViewById(R.id.lblTituloTransferencias);
        lblCuentaOrigen = (TextView)findViewById(R.id.lblCuentaOrigen);
        lblSaldoTransferencia = (TextView)findViewById(R.id.lblSaldoTransferencias);
        lblCredito = findViewById(R.id.lblCredito);
        txtNumeroTarjeta = (EditText) findViewById(R.id.txtNumeroTarjeta);
        txtCantidadTransferencia = (EditText) findViewById(R.id.txtCantidadTransferencia);
        txtMotivoTransferencia = findViewById(R.id.txtMotivoTransferencia);
        btnTransferencia = (Button) findViewById(R.id.btnTransferencia);

        final Intent intent = getIntent();
        opcion = intent.getStringExtra("CASO");
        numTarjeta = intent.getStringExtra("numTarjeta");
        tipo = intent.getStringExtra("Tipo");
        limiteCredito = intent.getStringExtra("limiteCredito");
        numCuenta = intent.getIntExtra("numCuenta", 0);
        saldo = intent.getDoubleExtra("saldo", 0);
        usuario = intent.getStringExtra("usuario");

        lblTituloTransferencia.setText(opcion);
        NumberFormat formatter = new DecimalFormat("#,###.##");
        String saldoCurrency = formatter.format(saldo);
        String saldoString = "$"+saldoCurrency;

        lblCuentaOrigen.setText("Cuenta *"+numTarjeta);
        lblSaldoTransferencia.setText("$"+saldoCurrency);

        if(tipo.equals("Credito")){
            NumberFormat formatterCredito = new DecimalFormat("#,###.##");
            String credito = formatterCredito.format(Double.parseDouble(limiteCredito));
            String creditoString = "Credito: $"+credito;
            lblCredito.setText(creditoString);
        }
        else{
            lblCredito.setVisibility(View.GONE);
        }

        if(opcion.equals("Otros bancos")){
            pagosTerceros = true;
        }
        else{
            pagosTerceros = false;
        }

        btnTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builderAlert;
                final AlertDialog Alert;
                String numerotarjeta = txtNumeroTarjeta.getText().toString();
                String cantidadTransferencia = txtCantidadTransferencia.getText().toString();
                String motivoTransferencia = txtMotivoTransferencia.getText().toString();
                double saldoDisponible = 0;

                if(tipo.equals("Credito")){
                     saldoDisponible = Integer.parseInt(limiteCredito)+saldo;
                }
                else{
                    saldoDisponible = saldo;
                }

                if(numerotarjeta.length() == 0 || cantidadTransferencia.length() == 0 || motivoTransferencia.length() == 0){
                    builderAlert = new AlertDialog.Builder(TransferenciasDetailActivity.this);
                    builderAlert.setMessage("Verifique que los campos esten completos");
                    builderAlert.setCancelable(true);
                    builderAlert.setPositiveButton(
                            R.string.message_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    Alert = builderAlert.create();
                    Alert.show();
                    return;
                }
                else if(saldoDisponible < Integer.parseInt(cantidadTransferencia)){
                    builderAlert = new AlertDialog.Builder(TransferenciasDetailActivity.this);
                    builderAlert.setMessage("Saldo insuficiente");
                    builderAlert.setCancelable(true);
                    builderAlert.setPositiveButton(
                            R.string.message_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    Alert = builderAlert.create();
                    Alert.show();
                    return;
                }

                else{
                    SolicitudTransferencia solicitudTransferencia = new SolicitudTransferencia(numTarjeta, numerotarjeta, Double.parseDouble(cantidadTransferencia), pagosTerceros, motivoTransferencia);

                    Gson gson;

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                    gson = gsonBuilder.create();

                    String jsonString = "";

                    JSONObject jsonObject = null;
                    String post = gson.toJson(solicitudTransferencia);

                    try{
                        try{
                            jsonString = new MyHttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://189.209.218.117:60000/api/Movimiento/Transferencia",post).get();
                        }
                        catch (Exception e){ }
                        if(jsonString != null || jsonString == ""){
                            jsonObject = new JSONObject(jsonString);
                            resultadoOperacion rsp = gson.fromJson(jsonObject.getJSONObject("resultadoOperacion").toString(), resultadoOperacion.class);

                            if(rsp.getTipo() == 0){
                                builderAlert = new AlertDialog.Builder(TransferenciasDetailActivity.this);
                                builderAlert.setMessage("Transaccion completada");
                                builderAlert.setCancelable(true);
                                builderAlert.setPositiveButton(
                                        R.string.message_ok,
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                                Intent intentBase = new Intent(TransferenciasDetailActivity.this, TarjetasActivity.class);
                                                intentBase.putExtra("USER", usuario);
                                                startActivity(intentBase);
                                            }
                                        });
                                Alert = builderAlert.create();
                                Alert.show();
                                return;
                            }
                            else{
                                builderAlert = new AlertDialog.Builder(TransferenciasDetailActivity.this);
                                builderAlert.setMessage(rsp.getDetalle());
                                builderAlert.setCancelable(true);
                                builderAlert.setPositiveButton(
                                        R.string.message_ok,
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                                Alert = builderAlert.create();
                                Alert.show();
                                return;
                            }
                        }

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
