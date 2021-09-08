package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Telmo
 */

@Entity
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)//alterar a estratÃ©gia para JOINED
@DiscriminatorColumn(name = "tipoPessoa")
public class Pessoa implements Serializable{
    
    @Id
    private String cpf;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(length = 8)
    private String cep;
    
    @Column(length = 200)
    private String complemento;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dtCadastro;
        
    @ManyToOne    
    @JoinColumn(name = "cidade_id", nullable = true)
    private Cidade cidade;//referencia a classa Cidade
    
    @Transient
    private String tipoPessoa;
    
    
    public Pessoa(){
        
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Calendar getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Calendar dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    } 
    
    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    
    @Override
    public String toString(){        
        return cpf;
    }
    
}
