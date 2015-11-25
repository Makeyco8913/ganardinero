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
import java.util.List;

public class Main extends AppCompatActivity {
    Firebase ref;
    final String fireUrl = "https://ganardinero.firebaseio.com";
    List<String> array = new ArrayList<String>();
    ListView lista;
    ArrayAdapter<String> adaptador;
    TextView texto;

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
                setContentView(R.layout.listar_basic);
                listarCliente();
            }
        });
        findViewById(R.id.listarProducto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.listar_basic);
                listarProducto();
            }
        });
        findViewById(R.id.listarUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.listar_basic);
                listarUsuario();
            }
        });
    }

    public void listarUsuario(){
        ref = new Firebase(fireUrl).child("Usuario");
        array.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Usuario u = postSnapshot.getValue(Usuario.class);
                    array.add(u.getNombre());
                    listalista(array);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                array.add("The read failed: " + firebaseError.getMessage());
            }
        });
        texto = (TextView) findViewById(R.id.texto);
        texto.setText("Cargando");
    }

    public void listarCliente(){
        ref = new Firebase(fireUrl).child("cliente");
        array.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Cliente u = postSnapshot.getValue(Cliente.class);
                    array.add(u.getNombre());
                    listalista(array);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                array.add("The read failed: " + firebaseError.getMessage());
            }
        });
        texto = (TextView) findViewById(R.id.texto);
        texto.setText("Cargando");
    }

    public void listarProducto(){
        ref = new Firebase(fireUrl).child("producto");
        array.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Producto u = postSnapshot.getValue(Producto.class);
                    array.add(u.getDescripcion());
                    listalista(array);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                array.add("The read failed: " + firebaseError.getMessage());
            }
        });
        texto = (TextView) findViewById(R.id.texto);
        texto.setText("Cargando");

    }

    public void listalista(List<String> listisima){
        lista = (ListView)findViewById(R.id.lista);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listisima);
        lista.setAdapter(adaptador);
        texto = (TextView) findViewById(R.id.texto);
        texto.setText("lista completa");

        findViewById(R.id.volver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });
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
