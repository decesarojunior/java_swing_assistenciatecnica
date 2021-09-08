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

/**
 * @author Telmo
 * 
 *  Tipos de relacionamento no diagrama de classe: associação
 * 
 *  Exemplo: Estado referencia (associa) Pais.
 * 
 */
@Entity
@Table(name = "tb_estado")
public class Estado implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_estado", strategy = GenerationType.SEQUENCE)
    private Integer id;
        
    @Column(nullable = false, length = 200) // torna o nome obrigatório e com tamanho de 200.
    private String nome;
    
    @Column(nullable = false, length = 2)
    private String uf;
    
    @ManyToOne // utiliza-se essa anotação em relacionamento do tipo associação / 1:N
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais; // esse atributo da instância é uma referencia para outra classe (chave estrangeira)
    
    
    public Estado(){         
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
}
