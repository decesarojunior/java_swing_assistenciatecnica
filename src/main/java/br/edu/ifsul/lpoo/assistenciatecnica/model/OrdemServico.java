
package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Prof. Telmo Junior
 */

@Entity
@Table(name = "tb_ordemservico")
public class OrdemServico implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_ordemservico", sequenceName = "seq_ordemservico_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_ordemservico", strategy = GenerationType.SEQUENCE)    
    private Integer id;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataAbertura;
    
    @Column(length = 600)
    private String descricao;
    
    @Column(length = 600)
    private String resolucao;
    
    @Column(precision = 2)
    private Float valorProdutos;
    
    @Column(precision = 2)
    private Float valorTotal;
    
    @Column
    private Integer quatidadeParcelas;
    
    @OneToMany(mappedBy = "ordem")
    private List<ItemProduto> produtos;
    
    @OneToMany(mappedBy = "id.ordem")
    private Set<ContaReceber> parcelas;    
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FormaPagamento forma;
        
    @ManyToOne
    @JoinColumn(name = "pessoajuridica_id", nullable = false)
    private PessoaJuridica cliente;
        
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
        
    public OrdemServico(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Calendar dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public Float getValorProdutos() {
        return valorProdutos;
    }

    public void setValorProdutos(Float valorProdutos) {
        this.valorProdutos = valorProdutos;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuatidadeParcelas() {
        return quatidadeParcelas;
    }

    public void setQuatidadeParcelas(Integer quatidadeParcelas) {
        this.quatidadeParcelas = quatidadeParcelas;
    }

    public List<ItemProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ItemProduto> produtos) {
        this.produtos = produtos;
    }

    public Set<ContaReceber> getParcelas() {
        return parcelas;
    }

    public void setParcelas(Set<ContaReceber> parcelas) {
        this.parcelas = parcelas;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public FormaPagamento getForma() {
        return forma;
    }

    public void setForma(FormaPagamento forma) {
        this.forma = forma;
    }

    public PessoaJuridica getCliente() {
        return cliente;
    }

    public void setCliente(PessoaJuridica cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }    
    
}
