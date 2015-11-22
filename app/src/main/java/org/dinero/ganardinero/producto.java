package org.dinero.ganardinero;

/**
 * Created by tekor on 22/11/2015.
 */
public class producto {
    private String descripcion;
    private Integer precio;
    private Integer existencia;
    private Integer id;

    public producto(){
    }

    public producto(String d, Integer p, Integer e, Integer i){
        descripcion = d;
        precio = p;
        existencia = e;
        id = i;
    }

    public String getDescripcion(){
        return descripcion;
    }
    public Integer getPrecio(){
        return precio;
    }
    public Integer getExistencia(){
        return existencia;
    }
    public Integer getId(){
        return id;
    }
    public boolean CalcularExistencia(Integer x){
        if(existencia-x>0)
            return true;
        return false;
    }
}
