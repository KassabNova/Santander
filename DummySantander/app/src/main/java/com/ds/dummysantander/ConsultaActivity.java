package com.ds.dummysantander;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ConsultaActivity extends AppCompatActivity {

    String numTarjeta;
    AlertDialog.Builder builderAlert;
    AlertDialog Alert;
    String jsonString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        final ArrayList<Consulta> consultas;

        Intent intent = getIntent();
        numTarjeta = intent.getStringExtra("numTarjeta");

        Gson gson;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        JSONObject jsonObject = null;

        String post = "{\n" +
                "\t\t\"NumTarjeta\": \""+numTarjeta+"\""+"\n"+
                "}";

        try{
            try{
                jsonString = new MyHttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://189.209.218.117:60000/api/movimiento/movimientos",post).get();
            }
            catch (Exception e){ }
            if(jsonString != null || jsonString == ""){
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("movimientos");

                if(jsonArray.length()>0){
                    consultas = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), Consulta[].class)));
                    ListView consultaView = (ListView) findViewById(R.id.LstConsultas);

                    ConsultaAdapter cardsAdapter = new ConsultaAdapter(this, consultas);
                    consultaView.setAdapter(cardsAdapter);

                    //final ArrayAdapter<Tarjetass> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarjetas);

                }
                else{
                    builderAlert = new AlertDialog.Builder(ConsultaActivity.this);
                    builderAlert.setMessage("No hay movimientos registrados en la tarjeta");
                    builderAlert.setCancelable(false);
                    builderAlert.setPositiveButton(
                            R.string.message_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Intent intent = new Intent(ConsultaActivity.this, LoginActivity.class);
                                    startActivityForResult(intent, 0);
                                }
                            });
                    Alert = builderAlert.create();
                    Alert.show();
                }

                resultadoOperacion rsp = gson.fromJson(jsonObject.getJSONObject("resultadoOperacion").toString(), resultadoOperacion.class);

                if(rsp.getTipo() == 0){
                }
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
