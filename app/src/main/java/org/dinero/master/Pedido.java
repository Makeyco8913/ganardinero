package org.dinero.master;

import java.util.Calendar;

/**
 * Created by tekor on 24/11/2015.
 */
public class Pedido extends Generico {
    Integer id;
    Integer idUsuario;
    Integer idCliente;
    Integer fecha; //formato aaaammdd
    Integer total;

    public Pedido(){super();}

    public Pedido(Integer u, Integer c, Integer t){
        super();
        String x;
        fecha = Calendar.DATE + (Calendar.MONTH * 100) + (Calendar.YEAR * 10000);
        idUsuario = u;
        idCliente = c;
        int z=fecha+c;
        x =Integer.toString(z)+Integer.toString((int)(Math.random()*1000 + 1));
        id=Integer.parseInt(x);
        total = t;
    }
    public Pedido(int i,Integer u, Integer c,Integer f,Integer t){
        super();
        id = i;
        idUsuario = u;
        idCliente = c;
        fecha = f;
        total = t;
    }

    public int getId(){return id;}
    public Integer getIdUsuario(){return idUsuario;}
    public Integer getIdCliente(){return idCliente;}
    public Integer getFecha(){return fecha;}
    public Integer getTotal(){return total;}
    public void setTotal(int t){total = t;}
    public String convertirFecha(){
        Integer y = fecha/10000;
        Integer m = (fecha-y*10000)/100;
        Integer d = (fecha-y*10000)-(m*100);
        return Integer.toString(d)+"/"+Integer.toString(m)+"/"+Integer.toString(y);
    }

    public String Tit(){return convertirFecha();}
    public String Sub(){return Integer.toString(total);}
}
