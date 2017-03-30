package com.example.eduardopalacios.myapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register_Activity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText first_n=(EditText)findViewById(R.id.first_name);
        final EditText last_n=(EditText)findViewById(R.id.last_name);
        final EditText emaiil=(EditText)findViewById(R.id.email_address_register);
        final EditText password_register=(EditText)findViewById(R.id.password_register);
        final EditText confrim_password=(EditText)findViewById(R.id.confirm_password);
        final Button submit=(Button)findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String first_name = first_n.getText().toString();
                String last_name = last_n.getText().toString();
                String email = emaiil.getText().toString();
                String password = password_register.getText().toString();
                String password_c = confrim_password.getText().toString();
                int contador=0;

                Response.Listener<String> responseListener = new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {


                        try {


                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");




                            if (success && resultado()) {
                                    Intent intent = new Intent(Register_Activity.this, login.class);
                                    Register_Activity.this.startActivity(intent);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register_Activity.this);
                                builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }

                };


                if(is_empty()&& resultado()) {


                    first_name= first_name.substring(contador_espacios_firstname());
                    last_name= last_name.substring(contador_espacios_lastname());
                    email=email.substring(contador_espacios_email());
                    password= password.substring(contador_espacios_password());


                    RegisterRequest registerRequest = new RegisterRequest(first_name, last_name, email, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Register_Activity.this);
                    queue.add(registerRequest);
                }
                else {
                    Toast toast=Toast.makeText(getApplicationContext(),"error in confirm password",Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });




    }

    public boolean resultado()
    {
         EditText password_register=(EditText)findViewById(R.id.password_register);
         EditText confrim_password=(EditText)findViewById(R.id.confirm_password);
         String password=password_register.getText().toString();
         String password_c=confrim_password.getText().toString();
         boolean verdadero=false;

        int i=0;

        while(i<password.length() && password.length()==password_c.length()) {
            if (password.charAt(i) ==password_c.charAt(i)) {
                verdadero=true;
            }
            else
            {
                verdadero=false;
                break;

            }
            i++;
        }


        return verdadero;
    }


    public boolean is_empty()
    {
        EditText first_n=(EditText)findViewById(R.id.first_name);
        EditText last_n=(EditText)findViewById(R.id.last_name);
        EditText emaiil=(EditText)findViewById(R.id.email_address_register);
        EditText password_register=(EditText)findViewById(R.id.password_register);
        EditText confrim_password=(EditText)findViewById(R.id.confirm_password);

        boolean no_vacio=true;

        if(first_n.getText().toString().trim().length()==0)
        {
            no_vacio=false;
            Toast.makeText(this, "You did not enter a first name", Toast.LENGTH_SHORT).show();

        }

        if(last_n.getText().toString().trim().length()==0)
        {
            no_vacio=false;
            Toast.makeText(this, "You did not enter a last name", Toast.LENGTH_SHORT).show();
        }

        if(emaiil.getText().toString().trim().length()==0)
        {
            no_vacio=false;
            Toast.makeText(this, "You did not enter an email", Toast.LENGTH_SHORT).show();
        }


        if(password_register.getText().toString().trim().length()==0)
        {
            no_vacio=false;
            Toast.makeText(this, "You did not enter a password", Toast.LENGTH_SHORT).show();
        }

        if(confrim_password.getText().toString().trim().length()==0)
        {
            no_vacio=false;
            Toast.makeText(this, "You did not enter a confrim password", Toast.LENGTH_SHORT).show();
        }



        return no_vacio;
    }


    public int contador_espacios_firstname()
    {
        EditText first_n=(EditText)findViewById(R.id.first_name);
        int contador=0;
        for (int i=0;i<first_n.length();i++)
        {
            if (first_n.getText().toString().charAt(i)==' ')
            {

                contador++;
            }
        }
        return contador;
    }

    public int contador_espacios_lastname()
    {
        EditText last_n=(EditText)findViewById(R.id.last_name);
        int contador=0;
        for (int i=0;i<last_n.length();i++)
        {
            if (last_n.getText().toString().charAt(i)==' ')
            {

                contador++;
            }
        }
        return contador;
    }

    public int contador_espacios_email()
    {
        EditText email=(EditText)findViewById(R.id.email_address_register);
        int contador=0;
        for (int i=0;i<email.length();i++)
        {
            if (email.getText().toString().charAt(i)==' ')
            {

                contador++;
            }
        }
        return contador;
    }
    public int contador_espacios_password()
    {
        EditText password=(EditText)findViewById(R.id.password_register);
        int contador=0;
        for (int i=0;i<password.length();i++)
        {
            if (password.getText().toString().charAt(i)==' ')
            {

                contador++;
            }
        }
        return contador;
    }

}
