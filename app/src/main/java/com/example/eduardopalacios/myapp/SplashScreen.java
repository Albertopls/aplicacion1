package com.example.eduardopalacios.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SplashScreen extends Activity {
    private GifImageView gifimageview;

    login valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        Context context = this;
        SharedPreferences obtenerDatos = PreferenceManager.getDefaultSharedPreferences(this);
        final String email=obtenerDatos.getString("email","No hay datos");
        final String contrasenia=obtenerDatos.getString("contrasenia","No hay datos");
        Log.d("Creation","es"+contrasenia);




            Toast toast=Toast.makeText(context,email,Toast.LENGTH_SHORT);
            toast.show();



        gifimageview=(GifImageView)findViewById(R.id.gifimageview);



        try{
            InputStream inputstream=getAssets().open("prueba2.gif");
            byte[] bytes= IOUtils.toByteArray(inputstream);
            gifimageview.setBytes(bytes);
            gifimageview.startAnimation();
        }
        catch(IOException e){

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(email.equals("No hay datos") ||  contrasenia.equals("No hay datos"))
                {

                    SplashScreen.this.startActivity(new Intent(SplashScreen.this, login.class));
                    SplashScreen.this.finish();





                }
                else  {



                  ObetenerUsuario(email,contrasenia);

                }




            }
        },5200);

    }


    public boolean cargar_verdadero()
    {
        boolean verdadero=false;
        try {
            FileInputStream fis=openFileInput("textFile.txt");
            InputStreamReader isr= new InputStreamReader(fis);
            char[]inputBuffer= new char[100];
            String s="";
            int charRead;
            while ((charRead=isr.read(inputBuffer))>0)
            {
                String readString=String.copyValueOf(inputBuffer,0,charRead);
                s+=readString;
                inputBuffer=new char[100];
            }
            isr.close();

            verdadero=Boolean.parseBoolean(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return verdadero;
    }

    public boolean cargar_falso()
    {
        boolean verdadero=false;
        try {
            FileInputStream fis=openFileInput("textFile.txt");
            InputStreamReader isr= new InputStreamReader(fis);
            char[]inputBuffer= new char[100];
            String s="";
            int charRead;
            while ((charRead=isr.read(inputBuffer))>0)
            {
                String readString=String.copyValueOf(inputBuffer,0,charRead);
                s+=readString;
                inputBuffer=new char[100];
            }
            isr.close();

            verdadero=Boolean.parseBoolean(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return verdadero;
    }


    public void ObetenerUsuario(String correo,String contrasenia)
    {

        Response.Listener<String> responseListener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                boolean exito=false;

                try {


                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");


                    if (success) {
                        String first_name = jsonResponse.getString("first_name");
                        String last_name = jsonResponse.getString("last_name");



                        Intent intent = new Intent(SplashScreen.this, Navigationdrawer.class);

                        intent.putExtra("identificador", first_name);
                        intent.putExtra("identificador2", last_name);


                        SplashScreen.this.startActivity(intent);





                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SplashScreen.this);
                        builder.setMessage("Login Failed").setNegativeButton("Retry", null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }




            }

        };


        LoginRequest loginRequest = new LoginRequest(correo, contrasenia, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SplashScreen.this);
        queue.add(loginRequest);

    }




}

