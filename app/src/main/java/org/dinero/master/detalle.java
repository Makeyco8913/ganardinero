package org.dinero.master;

import java.util.Calendar;

/**
 * Created by tekor on 24/11/2015.
 */
public class detalle {
    int id;
    Integer idProducto;
    Integer cantidad;
    Integer precio;

    public detalle(){}

    public detalle(Integer ip, Integer c, Integer p){
        id= ip.hashCode();
        idProducto = ip;
        cantidad = c;
        precio = p;
    }

    public detalle(int i,Integer ip, Integer c, Integer p){
        id= i;
        idProducto = ip;
        cantidad = c;
        precio = p;
    }

    public int getId(){return id;}
    public Integer getIdProducto(){return idProducto;}
    public Integer getCantidad(){return cantidad;}
    public Integer getPrecio(){return precio;}
}
