package org.dinero.ganardinero;

/**
 * Created by tekor on 22/11/2015.
 */
public class detalle {
    private Integer id;
    private Integer idProducto;
    private Integer precio;
    private Integer cantidad;

    public detalle(Integer i, Integer ip, Integer p, Integer c){
        id = i;
        idProducto = ip;
        precio = p;
        cantidad = c;
    }
    public Integer getId(){
        return id;
    }
    public Integer getIdProducto(){
        return idProducto;
    }
    public Integer getPrecio(){
        return precio;
    }
    public Integer getCantidad(){
        return cantidad;
    }
}
