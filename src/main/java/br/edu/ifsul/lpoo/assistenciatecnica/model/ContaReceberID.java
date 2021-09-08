/*
 * 
 */
package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Prof. Telmo Junior
 */

@Embeddable
public class ContaReceberID implements Serializable{
    
    @Column(name = "numeroparcela", nullable = false)
    private Integer numeroParcela;
    
    @ManyToOne
    @JoinColumn(name = "ordem_id", nullable = false)
    private OrdemServico ordem;
    
    public ContaReceberID(){
        
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public OrdemServico getOrdem() {
        return ordem;
    }

    public void setOrdem(OrdemServico ordem) {
        this.ordem = ordem;
    }
    
}
