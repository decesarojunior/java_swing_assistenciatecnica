
package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tb_itemproduto")
public class ItemProduto implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_itemproduto", sequenceName = "seq_itemproduto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_itemproduto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false)
    private Float quantidade;
    
    @Column
    private Float valorTotal;
    
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false) 
    private Produto produto;
        
    @ManyToOne
    @JoinColumn(name = "ordemservico_id", nullable = false) 
    private OrdemServico ordem; //referencia a entidade forte
    
    public ItemProduto(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public OrdemServico getOrdem() {
        return ordem;
    }

    public void setOrdem(OrdemServico ordem) {
        this.ordem = ordem;
    }
    
    
}
