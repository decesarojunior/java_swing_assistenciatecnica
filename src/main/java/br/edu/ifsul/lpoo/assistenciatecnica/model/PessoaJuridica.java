package br.edu.ifsul.lpoo.assistenciatecnica.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Prof. Telmo Junior
 */

@Entity
@Table(name = "tb_pessoajuridica")
@DiscriminatorValue("PJ")
public class PessoaJuridica extends Pessoa{
    
    @Column(length = 11, nullable = false)
    private String cnpj;
    
    @Column(length = 11)
    private String ie;
    
    @Column(length = 200, nullable = false)
    private String razao;
    
    public PessoaJuridica(){
        
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }
    
}
