package org.dinero.master;

import java.util.Calendar;

/**
 * Created by tekor on 24/11/2015.
 */
public class Detalle extends Generico {
    int id;
    Integer idProducto;
    String nombre;
    Integer cantidad;
    Integer precio;

    public Detalle(){super();}

    public Detalle(Integer ip, String n, Integer c, Integer p){
        super();
        id= ip.hashCode();
        nombre = n;
        idProducto = ip;
        cantidad = c;
        precio = p;
    }

    public Detalle(int i, String n, Integer ip, Integer c, Integer p){
        super();
        id= i;
        nombre = n;
        idProducto = ip;
        cantidad = c;
        precio = p;
    }

    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public Integer getIdProducto(){return idProducto;}
    public Integer getCantidad(){return cantidad;}
    public Integer getPrecio(){return precio;}
    public void setCantidad(int c){cantidad=c;}

    public String Tit(){return "Producto: "+nombre;}
    public String Sub(){return "Cantidad:"+Integer.toString(cantidad)+" Precio:"+Integer.toString(precio);}
}
