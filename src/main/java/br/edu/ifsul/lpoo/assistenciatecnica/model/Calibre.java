/*
 * 
 */
package br.edu.ifsul.lpoo.assistenciatecnica.model;

/**
 *
 * @author Telmo
 */
public enum Calibre {
    
    MM03(new Float(0.3)), MM05(new Float(0.5));
    
    private float valor;
    
    Calibre(float valor) {
        this.valor = valor;
    }
    
    public float getValor() {
        return valor;
    }
    
}
