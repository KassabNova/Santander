package com.ds.dummysantander;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SucursalActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locManager;
    public String jsonString = "";
    private MyHttpRequestTask conection;
    public List<Sucursales> sucursales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double mylatitude;
        double mylongitude;
        String tituloUbicacion;

        ObtenerSucursales sucursales = new ObtenerSucursales();
        sucursales.execute();

        mylatitude = 20.694083;
        mylongitude = -103.419842;
        tituloUbicacion = "UAG";


        LatLng defaultUbicacacion = new LatLng(mylatitude, mylongitude);
        CameraPosition posicionCamara = new CameraPosition.Builder()
                .target(defaultUbicacacion)
                .zoom(14)
                .build();

        mMap.addMarker(new MarkerOptions().position(defaultUbicacacion).title(tituloUbicacion));

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(posicionCamara));
    }


    private class ObtenerSucursales extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            Gson gson;

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
            gson = gsonBuilder.create();
            JSONObject jsonObject = null;

            try{
                try{
                    jsonString = new MyHttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://189.209.218.117:60000/api/principal/sucursales","").get();
                    jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("sucursales");

                    if(jsonArray.length()>0){
                        sucursales = Arrays.asList(gson.fromJson(jsonArray.toString(), Sucursales[].class));
                    }
                }
                catch (Exception e){ }
                if(jsonString != null || jsonString == ""){
                    jsonObject = new JSONObject(jsonString);

                    resultadoOperacion rsp = gson.fromJson(jsonObject.getJSONObject("resultadoOperacion").toString(), resultadoOperacion.class);

                    if(rsp.getTipo() == 0){
                        return true;
                    }
                    else{
                        return false;
                    }

                }
                else{
                    return false;
                }

            }
            catch (Exception e){
                e.printStackTrace();
                return false;
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {


        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Sucursales sucursal = null;
                LatLng posicion = null;
                //Marker marker;
                for(int counter = 0; counter < sucursales.size(); ++counter) {
                    sucursal = sucursales.get(counter);
                    posicion = new LatLng(Double.valueOf(sucursal.getLatitud()), Double.valueOf(sucursal.getLongitud()));
                    //marker = mMap.addMarker(new MarkerOptions().position(posicion).title(sucursal.getNombre()));
                    mMap.addMarker(new MarkerOptions().position(posicion).title(sucursal.getNombre()));
                }
            }
            else{
                Toast.makeText(SucursalActivity.this, "No se ha podido cargar las sucursales." +
                        " Intentelo más tarde", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled(){

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
