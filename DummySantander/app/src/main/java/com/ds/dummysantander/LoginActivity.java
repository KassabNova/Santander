package com.ds.dummysantander;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.ApplicationController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText txtuser, txtpassword;
    private Button btniniciosesion;
    private ImageView logo;
    private IniciarSesion iniciosesion;
    public String jsonString = "";


    public String user, password;

    AlertDialog.Builder builderAlert;
    AlertDialog Alert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        //getActionBar().hide();
        // Set up the login form.
        logo = (ImageView)findViewById(R.id.Logo);
        try{
            logo.setImageResource(R.drawable.logo_general);
        }
        catch(Exception e){
            logo.setImageResource(R.drawable.logo_titulo);
        }

        txtuser = (EditText)findViewById(R.id.User);
        txtpassword = (EditText)findViewById(R.id.Password);
        btniniciosesion = (Button) findViewById(R.id.IniciarSesion);



        btniniciosesion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                try{
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                catch (Exception e){ }

                user = txtuser.getEditableText().toString();
                password = txtpassword.getEditableText().toString();

                if(user.length() == 0 || password.length() == 0){
                    //Alert.dismiss();
                    builderAlert = new AlertDialog.Builder(LoginActivity.this);
                    builderAlert.setMessage(R.string.forgotten_parameters);
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
                    //Alert.dismiss();
                    return;
                }
                else{
                    iniciosesion = new IniciarSesion();
                    iniciosesion.execute();
                }

            }
        });

        //mLoginFormView = findViewById(R.id.login_form);
        //mProgressView = findViewById(R.id.login_progress);
    }

    public class IniciarSesion extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            //Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
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
                    //189.209.218.117
                    jsonString = new MyHttpRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://189.209.218.117:60000/api/login/",post).get();
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
            builderAlert = new AlertDialog.Builder(LoginActivity.this);
            builderAlert.setMessage("Cargando...");
            Alert = builderAlert.create();
            Alert.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Alert.dismiss();

                Intent intent = new Intent(LoginActivity.this, TarjetasActivity.class);
                //intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("USER", user);
                intent.putExtra("PASSWORD", password);
                startActivityForResult(intent, 0);
                finish();

            }
            else{
                Alert.dismiss();
                builderAlert = new AlertDialog.Builder(LoginActivity.this);
                builderAlert.setMessage(R.string.message_error_login);
                Alert = builderAlert.create();
                Alert.show();
            }
            //Toast.makeText(LoginActivity.this, "Fin de la tarea asincrona", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {

        }
    }

}

