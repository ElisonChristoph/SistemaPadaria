/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Elison Christoph
 */
public class Pedido {

    private int id;
    private int codCliente;
    private Date data;
    private Date dataFim;
    private List<Produto> prudutos;
    private boolean finalizado;

    public Pedido() {

    }

    public Pedido(int id, int codCliente, Date data, Date dataFim, List<Produto> prudutos, boolean finalizado) {
        this.id = id;
        this.codCliente = codCliente;
        this.data = data;
        this.dataFim = dataFim;
        this.prudutos = prudutos;
        this.finalizado = finalizado;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
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

    public List<Produto> getProdutos() {
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
