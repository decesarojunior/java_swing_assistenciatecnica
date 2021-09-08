
package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Telmo Junior
 * @data 24/06/2021
 */

@Entity
@Table(name = "tb_pais")
public class Pais implements Serializable {
    
    //atributos da instancia.
    @Id
    @SequenceGenerator(name = "seq_pais", sequenceName = "seq_pais_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_pais", strategy = GenerationType.SEQUENCE)
    private Integer id;
        
    @Column(nullable = false, length = 200)
    private String nome;
    
    
    //construtor público e sem parâmetros
    public Pais(){        
        
    }
    
    public Integer getId(){        
        return this.id;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setId(Integer paramId){            
        this.id = paramId;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
}
