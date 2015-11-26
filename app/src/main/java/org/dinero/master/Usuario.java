package org.dinero.master;

/**
 * Created by tekor on 24/11/2015.
 */
public class Usuario extends Generico {
    Integer id;
    String nombre;
    int pass;

    public Usuario(){super();}

    public Usuario(String n, String p){
        super();
        id = n.hashCode();
        nombre = n;
        pass = p.hashCode();
    }

    public Usuario(int i, String n, int p){
        super();
        id = i;
        nombre = n;
        pass = p;
    }

    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public int getPass(){return pass;}
    public Boolean confirm(String p){return pass==p.hashCode();}

    public String Tit(){return nombre;}
    public String Sub(){return Integer.toString(id);}
}
