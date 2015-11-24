package org.dinero.master;

/**
 * Created by tekor on 24/11/2015.
 */
public class Producto {
    private Integer id;
    private String descripcion;
    private Integer cantidad;
    private Integer precio;

    public Producto(){}

    public Producto(String d, Integer c, Integer p){
        id = d.hashCode()+p.hashCode();
        descripcion = d;
        cantidad = c;
        precio = p;
    }

    public Producto(int i, String d, Integer c, Integer p){
        id = i;
        descripcion = d;
        cantidad = c;
        precio = p;
    }

    public int getId(){return id;}
    public String getDescripcion(){return descripcion;}
    public Integer getCantidad(){return cantidad;}
    public Integer getPrecio(){return precio;}
}
