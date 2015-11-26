package org.dinero.master;

/**
 * Created by tekor on 24/11/2015.
 */
public class Cliente extends Generico {
    private Integer id;
    private String nombre;
    private String ruc;
    private Integer pass;

    public Cliente(){super();}

    public Cliente(String n, String r, String p){
        super();
        id=r.hashCode();
        nombre = n;
        ruc = r;
        pass = p.hashCode();
    }
    public Cliente(int i,String n, String r, int p){
        super();
        id = i;
        nombre = n;
        ruc = r;
        pass = p;
    }

    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getRuc(){return ruc;}
    public int getPass(){return pass;}
    public Boolean confirm(String p){return pass==p.hashCode();}

    public String Tit(){return "Nombre: "+nombre;}
    public String Sub(){return "RUC: "+ruc;}
}
