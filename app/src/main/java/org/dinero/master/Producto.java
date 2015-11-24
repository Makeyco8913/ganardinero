package org.dinero.master;

/**
 * Created by tekor on 24/11/2015.
 */
public class Producto {
    private Integer id;
    private String descripcion;
    private Integer existencia;
    private Integer precio;

    public Producto(){}

    public Producto(String d, Integer p, Integer e){
        id = d.hashCode()+p.hashCode();
        descripcion = d;
        existencia = e;
        precio = p;
    }

    public Producto(int i, String d, Integer p, Integer e){
        id = i;
        descripcion = d;
        existencia = e;
        precio = p;
    }

    public int getId(){return id;}
    public String getDescripcion(){return descripcion;}
    public Integer getExistencia(){return existencia;}
    public Integer getPrecio(){return precio;}
}
