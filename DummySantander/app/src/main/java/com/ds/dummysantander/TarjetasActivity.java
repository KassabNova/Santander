package com.ds.dummysantander;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TarjetasActivity extends AppCompatActivity {

    String jsonString = "";
    String user, password;
    AlertDialog.Builder builderAlert;
    AlertDialog Alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjetas);

        final ArrayList<Tarjetas> tarjetas;
        //Tarjetas tarjetas1 = new Tarjetas("123", 456, 1985, "Debito", "2000");
        //Tarjetas tarjetas2 = new Tarjetas("000", 987, 1000, "Credito", "5000");

        //tarjetas.add(tarjetas1);
        //tarjetas.add(tarjetas2);

        Intent intent = getIntent();
        user = intent.getStringExtra("USER");
        password = intent.getStringExtra("PASSWORD");

        Gson gson;

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        JSONObject jsonObject = null;

        String post = "{\n" +
                "\t\t\"usuario\": \""+user+"\""+",\n"+
                "\t\t\"password\": \""+password+"\""+"\n"+
                "}";

        try{
            try{
                jsonString = new MyHttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://192.168.15.150:50000/api/principal/principal",post).get();
            }
            catch (Exception e){ }
            if(jsonString != null || jsonString == ""){
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("tarjetas");

                if(jsonArray.length()>0){
                    tarjetas = new ArrayList<>(Arrays.asList(gson.fromJson(jsonArray.toString(), Tarjetas[].class)));
                    ListView tarjetasListview = (ListView) findViewById(R.id.listView1);

                    CustomCardsAdapter cardsAdapter = new CustomCardsAdapter(this, tarjetas);
                    tarjetasListview.setAdapter(cardsAdapter);

                    //final ArrayAdapter<Tarjetas> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarjetas);


                }
                else{
                    builderAlert = new AlertDialog.Builder(TarjetasActivity.this);
                    builderAlert.setMessage("Usted no tiene tarjetas registradas");
                    builderAlert.setCancelable(false);
                    builderAlert.setPositiveButton(
                            R.string.message_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Intent intent = new Intent(TarjetasActivity.this, LoginActivity.class);
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
