
package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Prof. Telmo Junior
 */

@Entity
@Table(name = "tb_permissao")
public class Permissao implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_permissao", sequenceName = "seq_permissao_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_permissao", strategy = GenerationType.SEQUENCE)  
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(nullable = false) 
    @Enumerated(EnumType.STRING)
    private Acao acao;
    
    @Column(nullable = false) 
    @Enumerated(EnumType.STRING)
    private Componente componente;
    
    public Permissao(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }
    
}
