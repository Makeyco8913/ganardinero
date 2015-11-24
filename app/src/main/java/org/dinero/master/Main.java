package org.dinero.master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
    Firebase ref;
    final String fireUrl = "https://ganardinero.firebaseio.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        menu();
    }

    public void menu(){
        setContentView(R.layout.main);
        findViewById(R.id.listarDatos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.listar);
                listar();

            }
        });
        findViewById(R.id.anadirDatos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.anadir);
                crear();
            }
        });

    }

    public void crear(){
        findViewById(R.id.crearCliente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.anadir_cliente);
                crearCliente();
            }
        });
    }
    public void listar(){
        findViewById(R.id.listarCliente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.listar_cli);
                listarCliente();
            }
        });
    }

    public void listarCliente(){
        Firebase ref = new Firebase(fireUrl).child("cliente");

    }

    public void crearCliente(){

        findViewById(R.id.anadirCliente).setOnClickListener(new View.OnClickListener() {
            Cliente cli;
            String name;
            String ru;
            String pass1;
            String pass2;
            TextView passError = (TextView) findViewById(R.id.passError);
            EditText nameText = (EditText) findViewById(R.id.name);
            EditText rucText = (EditText) findViewById(R.id.ruc);
            EditText pass1Text = (EditText) findViewById(R.id.pass1);
            EditText pass2Text = (EditText) findViewById(R.id.pass2);

            @Override
            public void onClick(View view) {
                name = nameText.getText().toString();
                pass1 = pass1Text.getText().toString();
                pass2 = pass2Text.getText().toString();
                ru = rucText.getText().toString();
                if (pass1.equals(pass2)) {
                    passError.setText("satisfactorio");
                    cli = new Cliente(name, ru, pass1);
                    ref = new Firebase(fireUrl).child("cliente").child(Integer.toString(cli.getId()));
                    ref.setValue(cli);
                    menu();
                } else
                    passError.setText("las contraseñas no coinciden");
            }

        });
    }

}
