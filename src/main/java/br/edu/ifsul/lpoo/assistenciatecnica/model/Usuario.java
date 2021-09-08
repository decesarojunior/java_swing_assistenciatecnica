package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Prof. Telmo Junior
 */

@Entity
@Table(name = "tb_usuario")
@DiscriminatorValue(value = "US")
public class Usuario extends Pessoa implements Serializable {
    
    @Column(length = 6, nullable = false)
    private String senha;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dtultimaacesso;
        
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    
    public Usuario(){
        
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Calendar getDtultimaacesso() {
        return dtultimaacesso;
    }

    public void setDtultimaacesso(Calendar dtultimaacesso) {
        this.dtultimaacesso = dtultimaacesso;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
}
