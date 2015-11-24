package org.dinero.master;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

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
        findViewById(R.id.crearProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.anadir_producto);
                crearProducto();
            }
        });
        findViewById(R.id.crearUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.anadir_usuario);
                crearUsuario();
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

    public void crearProducto(){

        findViewById(R.id.anadirProducto).setOnClickListener(new View.OnClickListener() {
            Producto pro;
            String des;
            int pre;
            int exi;
            TextView Error = (TextView) findViewById(R.id.Error);
            EditText desText = (EditText) findViewById(R.id.descripcion);
            EditText preText = (EditText) findViewById(R.id.precio);
            EditText exiText = (EditText) findViewById(R.id.existencia);

            @Override
            public void onClick(View view) {
                des = desText.getText().toString();
                pre = Integer.parseInt(preText.getText().toString());
                exi = Integer.parseInt(exiText.getText().toString());
                if (exi>0) {
                    Error.setText("satisfactorio");
                    pro = new Producto(des, pre, exi);
                    ref = new Firebase(fireUrl).child("producto").child(Integer.toString(pro.getId()));
                    ref.setValue(pro);
                    menu();
                } else
                    Error.setText("tiene que haber al menos 1");
            }

        });
    }

    public void crearUsuario(){

        findViewById(R.id.anadirUsuario).setOnClickListener(new View.OnClickListener() {
            Usuario usr;
            String name;
            String pass1;
            String pass2;
            TextView passError = (TextView) findViewById(R.id.passError);
            EditText nameText = (EditText) findViewById(R.id.name);
            EditText pass1Text = (EditText) findViewById(R.id.pass1);
            EditText pass2Text = (EditText) findViewById(R.id.pass2);

            @Override
            public void onClick(View view) {
                name = nameText.getText().toString();
                pass1 = pass1Text.getText().toString();
                pass2 = pass2Text.getText().toString();
                if (pass1.equals(pass2)) {
                    passError.setText("satisfactorio");
                    usr = new Usuario(name, pass1);
                    ref = new Firebase(fireUrl).child("Usuario").child(Integer.toString(usr.getId()));
                    ref.setValue(usr);
                    menu();
                } else
                    passError.setText("las contraseñas no coinciden");
            }

        });
    }

}
