
package br.edu.ifsul.lpoo.assistenciatecnica.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Prof. Telmo Junior
 */

@Entity
@Table(name = "tb_contareceber")
public class ContaReceber implements Serializable {

    @EmbeddedId
    private ContaReceberID id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar dataVencimento;

    @Column(nullable = false, precision = 2)
    private Float valor;

    @Column(precision = 2)
    private Float valorPago;

    @Column
    @Temporal(TemporalType.DATE)
    private Calendar dataPagamento;

    public ContaReceber(){

    }

    public ContaReceberID getId() {
        return id;
    }

    public void setId(ContaReceberID id) {
        this.id = id;
    }

    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Float getValorPago() {
        return valorPago;
    }

    public void setValorPago(Float valorPago) {
        this.valorPago = valorPago;
    }

    public Calendar getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}
