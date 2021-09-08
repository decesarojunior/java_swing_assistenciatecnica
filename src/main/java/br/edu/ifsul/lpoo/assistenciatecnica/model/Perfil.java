
package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Telmo
 */

/*
  Tipos de relacionamento: agregação.

  Utilização-se agregação quanto tempos um N:N

*/

@Entity
@Table(name = "tb_perfil")
public class Perfil implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_perfil", sequenceName = "seq_perfil_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_perfil", strategy = GenerationType.SEQUENCE)      
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nome;
        
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_permissoes", joinColumns = {@JoinColumn(name = "perfil_id")}, 
                                       inverseJoinColumns = {@JoinColumn(name = "permissao_id")})
    private List<Permissao> permissoes;//agregação
        
    public Perfil(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    public void setPermissao(Permissao permissao) {
        if (this.permissoes == null)
            this.permissoes = new ArrayList();        
        this.permissoes.add(permissao);
    }
    
    
}
