package org.dinero.master;

import java.util.Calendar;

/**
 * Created by tekor on 24/11/2015.
 */
public class Pedido {
    Integer id;
    Integer idUsuario;
    Integer idCliente;
    Integer fecha; //formato aaaammdd

    public Pedido(){}

    public Pedido(Integer u, Integer c){
        idUsuario = u;
        idCliente = c;
        fecha = Calendar.DATE + (Calendar.MONTH * 100) + (Calendar.YEAR * 10000);
        id = u.hashCode()+c.hashCode()+fecha.hashCode();
    }
    public Pedido(int i,Integer u, Integer c,Integer f){
        id = i;
        idUsuario = u;
        idCliente = c;
        fecha = f;
    }

    public int getId(){return id;}
    public Integer getIdUsuario(){return idUsuario;}
    public Integer getIdCliente(){return idCliente;}
    public Integer getFecha(){return fecha;}
}
