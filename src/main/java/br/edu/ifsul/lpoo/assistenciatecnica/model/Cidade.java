
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
 *
 * @author Prof. Telmo Junior
 */
@Entity
@Table(name = "tb_cidade")
public class Cidade implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cidade", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    public Cidade(){

    }
    //segundo construtor com uma nova assinatura.
    public Cidade(Integer id, String nome){
        this.id = id;
        this.nome = nome;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
        @Override
    public String toString(){
        return nome;
    }

    @Override
    public boolean equals(Object o){

        if(o == null){
            return false;

        }else if(!(o instanceof Cidade)){
            return false;

        }else{
            Cidade c = (Cidade) o;
            if (c.getId().intValue() == this.getId().intValue()){
                return true;
            }else{
                return false;
            }
        }
    }


}
