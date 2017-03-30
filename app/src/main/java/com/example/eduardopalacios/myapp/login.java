package com.example.eduardopalacios.myapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        final EditText email_login=(EditText)findViewById(R.id.Email_address);
        final EditText password_login=(EditText)findViewById(R.id.password);
        final Button login_button=(Button)findViewById(R.id.login);
        final TextView register_here=(TextView)findViewById(R.id.register_here);
        final CheckBox recordar=(CheckBox) findViewById(R.id.checkBox);
        final boolean[] recordareyc = {false};


        recordar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              recordareyc[0] =true;
            }
        });





        register_here.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                login.this.startActivity(new Intent(login.this,Register_Activity.class));
            }

        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String email = email_login.getText().toString();
                final String password = password_login.getText().toString();
                final String[] nombre = new String[1];





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
                                nombre[0] =first_name;

                                Intent intent = new Intent(login.this, Navigationdrawer.class);

                                intent.putExtra("identificador", first_name);
                                intent.putExtra("identificador2", last_name);


                                login.this.startActivity(intent);




                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                                builder.setMessage("Login Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                        if(recordareyc[0])
                        {
                            cargar_archivo();

                            Intent intent2 = new Intent(login.this, Navigationdrawer.class);
                            intent2.putExtra("identificador",nombre[0]);


                        }


                    }

                };


                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(login.this);
                queue.add(loginRequest);
            }
        });

    }


    public void cargar_archivo()
    {

            String opcion = "true";
            try {

                FileOutputStream fos = openFileOutput("textFile.txt", MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                osw.write(opcion);
                osw.flush();
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



    }

}

