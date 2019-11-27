package model.bean;

/**
 *
 * @author gianr
 */
public class ModeloTabelaItensEntradaProduto {

    private String id;
    private String descricao;
    private String estoque;
    private String quantidade;
    private String estoqueFinal;

    public ModeloTabelaItensEntradaProduto(String id, String descricao, String estoque, String quantidade, String estoqueFinal) {
        this.id = id;
        this.descricao = descricao;
        this.estoque = estoque;
        this.quantidade = quantidade;
        this.estoqueFinal = estoqueFinal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getEstoqueFinal() {
        return estoqueFinal;
    }

    public void setEstoqueFinal(String estoqueFinal) {
        this.estoqueFinal = estoqueFinal;
    }


   

    
    

    
}
