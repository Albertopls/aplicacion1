package com.example.eduardopalacios.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView Nombre=(TextView) findViewById(R.id.nombre);
        final TextView Apellido=(TextView) findViewById(R.id.apellido);
        final TextView exito=(TextView)findViewById(R.id.exito);

        Intent intent=getIntent();
        String first_name=intent.getStringExtra("identificador");
        String last_name=intent.getStringExtra("identificador2");
        exito.setText("Sign-in success");
        Nombre.setText(first_name);
        Apellido.setText(last_name);
    }
}
