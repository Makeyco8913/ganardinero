package org.dinero.ganardinero;

/**
 * Created by tekor on 22/11/2015.
 */
public class cliente {
    String nombre;
    Integer id;
    String ruc;

    public cliente(){
    }

    public cliente(String n, String r, Integer i){
        nombre = n;
        id = i;
        ruc = r;
    }
    public String getNombre(){
        return nombre;
    }
    public String getRuc(){
        return ruc;
    }
    public Integer getId(){
        return id;
    }

}
