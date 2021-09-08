/*
 * 
 */
package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Prof. Telmo Junior
 */

@Entity
@Table(name = "tb_auditoriaacesso")
public class AuditoriaAcesso implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_auditoriaacesso", sequenceName = "seq_auditoriaacesso_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_auditoriaacesso", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dtAcao;
        
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Componente componente;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Acao acao;
        
    @ManyToOne //um usuario pode ser referenciado por muitas AuditoriaAcesso
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    public AuditoriaAcesso(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDtAcao() {
        return dtAcao;
    }

    public void setDtAcao(Calendar dtAcao) {
        this.dtAcao = dtAcao;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
 
}

