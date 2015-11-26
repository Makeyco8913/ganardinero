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
    ArrayList<Generico> array = new ArrayList<Generico>();
    TextView texto;
    Pedido pido;
    Detalle deta;
    Cliente clien;
    ArrayList<Cliente> cli = new ArrayList<Cliente>();
    ArrayList<Producto> pro = new ArrayList<Producto>();
    ArrayList<Detalle> arrayDeta = new ArrayList<Detalle>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        menu();
    }

    public void menu(){
        setContentView(R.layout.main);
        cli.clear();
        pro.clear();
        arrayDeta.clear();
        pido = new Pedido();
        deta = new Detalle();
        findViewById(R.id.listarDatos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listar();
            }
        });
        findViewById(R.id.anadirDatos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crear();
            }
        });
        findViewById(R.id.tomarPedido).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomarPedido();
            }
        });
        findViewById(R.id.listar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarPedido();
            }
        });

    }

    public void crear(){
        setContentView(R.layout.anadir);
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
        setContentView(R.layout.listar);
        findViewById(R.id.listarCliente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.listar_basic);
                findViewById(R.id.volver).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        menu();
                    }
                });
                listarCliente();
            }
        });
        findViewById(R.id.listarProducto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.listar_basic);
                findViewById(R.id.volver).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        menu();
                    }
                });
                listarProducto();
            }
        });
        findViewById(R.id.listarUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.listar_basic);
                findViewById(R.id.volver).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        menu();
                    }
                });
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
                    array.add(u);
                    listalista(array);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

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
                    array.add(u);
                    listalista(array);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

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
                    array.add(u);
                    listalista(array);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        texto = (TextView) findViewById(R.id.texto);
        texto.setText("Cargando");

    }

    public void listalista(ArrayList<?> listisima){
        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new listaAdaptador(this, R.layout.itemlista, listisima) {
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textTitulo);
                texto_superior_entrada.setText(((Generico) entrada).Tit());

                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textSubTitulo);
                texto_inferior_entrada.setText(((Generico) entrada).Sub());


            }
        });
        texto = (TextView) findViewById(R.id.texto);
        texto.setText("lista completa");
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

    public void tomarPedido(){
        setContentView(R.layout.tomar_pedido);
        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = new Firebase(fireUrl).child("cliente");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            Cliente u = postSnapshot.getValue(Cliente.class);
                            cli.add(u);
                            if (buscarCli()) {
                                findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        tomarProducto();
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
            }
        });
    }

    public boolean buscarCli(){
        TextView titulo = (TextView) findViewById(R.id.titulo);
        TextView subtitulo = (TextView) findViewById(R.id.subtitulo);
        EditText hint = (EditText) findViewById(R.id.hint);
        String hi = hint.getText().toString();
        for (int i=0; i<cli.size();i++) {
            titulo.setText(cli.get(i).getNombre());
            subtitulo.setText("buscando");

            if (cli.get(i).getNombre().equals(hi) || cli.get(i).getRuc().equals(hi)) {
                titulo.setText(cli.get(i).Tit());
                subtitulo.setText(cli.get(i).Sub());
                clien = cli.get(i);
                pido = new Pedido(1, cli.get(i).getId(), 0);
                arrayDeta.clear();
                return true;
            }
        }
        titulo.setText("Error");
        subtitulo.setText("No encontrado");
        return false;
    }

    public void tomarProducto(){
        setContentView(R.layout.tomar_pedido_1);
        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = new Firebase(fireUrl).child("producto");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            Producto u = postSnapshot.getValue(Producto.class);
                            pro.add(u);
                            if (buscarPro()) {
                                findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        tomarCantidad();
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
            }
        });
    }

    public boolean buscarPro(){
        TextView titulo = (TextView) findViewById(R.id.titulo);
        TextView subtitulo = (TextView) findViewById(R.id.subtitulo);
        EditText hint = (EditText) findViewById(R.id.hint);
        String hi = hint.getText().toString();
        for (int i=0; i<pro.size();i++) {
            titulo.setText(pro.get(i).getDescripcion());
            subtitulo.setText("buscando");

            if (pro.get(i).getDescripcion().equals(hi) || Integer.toString(pro.get(i).getId()).equals(hi)) {
                titulo.setText(pro.get(i).Tit());
                subtitulo.setText(pro.get(i).Sub());

                deta = new Detalle(pro.get(i).getId(),pro.get(i).getDescripcion() ,0,pro.get(i).getPrecio());
                return true;
            }
        }
        titulo.setText("Error");
        subtitulo.setText("No encontrado");
        return false;
    }

    public void tomarCantidad(){
        setContentView(R.layout.tomar_pedido_2);


        findViewById(R.id.redo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText c = (EditText) findViewById(R.id.hint);
                deta.setCantidad(Integer.parseInt(c.getText().toString()));
                arrayDeta.add(deta);
                tomarProducto();
            }
        });

        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText c = (EditText) findViewById(R.id.hint);
                deta.setCantidad(Integer.parseInt(c.getText().toString()));
                arrayDeta.add(deta);
                confirmar();
            }
        });
    }

    public void confirmar(){
        setContentView(R.layout.tomar_pedido_f);

        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu();
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText hi = (EditText) findViewById(R.id.hint);
                TextView t = (TextView) findViewById(R.id.subir);
                if(clien.confirm(hi.getText().toString())) {
                    subirDatos();

                    t.setText("Subiendo Datos, Por favor espere");
                }
                else
                    t.setText("Contraseña incorrecta ");
            }
        });
    }

    public void subirDatos(){
        ref = new Firebase(fireUrl).child("pedido").child(Integer.toString(pido.getIdCliente())).child(Integer.toString(pido.getId()));
        ref.setValue(pido);
        for (int i=0;i<arrayDeta.size();i++){
            ref = new Firebase(fireUrl).child("detalle").child(Integer.toString(pido.getId())).child(Integer.toString(arrayDeta.get(i).getId()));
            ref.setValue(arrayDeta.get(i));
        }
        menu();
    }

}
