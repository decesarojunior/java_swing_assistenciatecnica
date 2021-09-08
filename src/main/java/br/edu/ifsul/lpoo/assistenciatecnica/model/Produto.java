/*
 * 
 */
package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import java.util.List;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 *
 * @author Telmo
 */

/*
 Tipo de relacionamento: agregação por composição.

  Utiliza-se quando tempos uma entidade forte e uma entidade fraca.
*/

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)    
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    @Column(precision = 2, nullable = false)
    private Float preco;
    
    @OneToMany(mappedBy = "produto") // o nome "produto" referencia o atributo produto na classe Foto
    private List<Foto> fotos;//agregação por composicao.
    
    public Produto (){
        
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

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
    
    
    
    
}
