package org.dinero.ganardinero;

import java.util.ArrayList;

/**
 * Created by tekor on 22/11/2015.
 */
public class pedido {
    Integer id;
    Integer idUsuario;
    Integer idCliente;
    String fecha; //el problema con json que no maneja tipo date, no se si separar en dia mes y año
    ArrayList<Integer> arrayIdDetalles;

    public pedido(){
    }

    public pedido(Integer p, Integer u, Integer c, ArrayList<Integer> a){
        id = p;
        idUsuario = u;
        idCliente = c;
        arrayIdDetalles = a;
    }
    public Integer getId(){
        return id;
    }
    public Integer getIdUsuario(){
        return idUsuario;
    }
    public Integer getIdCliente(){
        return idCliente;
    }
    public ArrayList<Integer> getArrayIdDetalles(){
        return arrayIdDetalles;
    }

}
