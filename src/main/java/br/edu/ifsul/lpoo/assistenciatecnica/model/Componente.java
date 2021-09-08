/*
 * 
 */
package br.edu.ifsul.lpoo.assistenciatecnica.model;

/**
 *
 * @author Telmo
 */
public enum Componente {
    
    TELALOGIN, TELACIDADE, TELAESTADO, TELAUSUARIO, TELAPESSOAJURIDICA, TELAPERMISSAO;
    
    public static Componente getComponente(String nameEnum){
        
        if(nameEnum.equals(Componente.TELALOGIN.toString()))
            
            return Componente.TELALOGIN;
        
        else if(nameEnum.equals(Componente.TELACIDADE.toString())){
            
            return Componente.TELACIDADE;
            
        }else if(nameEnum.equals(Componente.TELAESTADO.toString())){
            
            return Componente.TELAESTADO;
            
        }else if(nameEnum.equals(Componente.TELAUSUARIO.toString())){
            
            return Componente.TELAUSUARIO;
            
        }else if(nameEnum.equals(Componente.TELAPESSOAJURIDICA.toString())){
            
            return Componente.TELAPESSOAJURIDICA;
            
        }else if(nameEnum.equals(Componente.TELAPERMISSAO.toString())){
            
            return Componente.TELAPERMISSAO;
            
        }else{
            return null;
        }
    }
}
