package com.example.eduardopalacios.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SplashScreen extends Activity {
    private GifImageView gifimageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);


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

                if(cargar_verdadero())
                {
                    SplashScreen.this.startActivity(new Intent(SplashScreen.this, Navigationdrawer.class));
                    SplashScreen.this.finish();



                }
                if(!cargar_falso()) {

                    SplashScreen.this.startActivity(new Intent(SplashScreen.this, login.class));
                    SplashScreen.this.finish();
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




}

