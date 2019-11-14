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
public class EntradaProduto {

    private int id;
    private Date data;
    private List<Produto> prudutos;

    public EntradaProduto() {

    }

    public EntradaProduto(int id, Date data, List<Produto> prudutos) {
        this.id = id;
        this.data = data;
        this.prudutos = prudutos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
