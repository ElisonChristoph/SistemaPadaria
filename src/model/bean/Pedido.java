/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Elison Christoph
 */
public class Pedido {

    private int id;
    private int codCliente;
    private Date data;
    private List<Produto> prudutos;
    private boolean finalizado;

    public Pedido() {

    }

    public Pedido(int id, int codCliente, Date data, List<Produto> prudutos, boolean finalizado) {
        this.id = id;
        this.codCliente = codCliente;
        this.data = data;
        this.prudutos = prudutos;
        this.finalizado = finalizado;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Produto> getPrudutos() {
        return prudutos;
    }

    public void setPrudutos(List<Produto> prudutos) {
        this.prudutos = prudutos;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }


}