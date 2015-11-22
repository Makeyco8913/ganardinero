package org.dinero.ganardinero;

import java.util.ArrayList;

/**
 * Created by tekor on 22/11/2015.
 */
public class pedido {
    Integer id;
    Integer idUsuario;
    Integer idCliente;
    ArrayList<Integer> arrayIdDetalles;

    public pedido(Integer p, Integer u, Integer c, ArrayList<Integer> a){
        id = p;
        idUsuario = u;
        idCliente = c;
        arrayIdDetalles = a;
    }
}
