/*
 * 
 */
package br.edu.ifsul.lpoo.assistenciatecnica.model;

/**
 *
 * @author Telmo
 */
public enum Acao {
    
    INCLUIR, ALTERAR, EXCLUIR, EDITAR, LISTAR, AUTENTICAR;
    
    public static Acao getAcao(String nameEnum){
        
        if(nameEnum.equals(Acao.INCLUIR.toString()))
            
            return Acao.INCLUIR;
        
        else if(nameEnum.equals(Acao.ALTERAR.toString())){
            
            return Acao.ALTERAR;
            
        }else if(nameEnum.equals(Acao.EXCLUIR.toString())){
            
            return Acao.EXCLUIR;
            
        }else if(nameEnum.equals(Acao.EDITAR.toString())){
            
            return Acao.EDITAR;
            
        }else if(nameEnum.equals(Acao.LISTAR.toString())){
            
            return Acao.LISTAR;
            
        }else if(nameEnum.equals(Acao.AUTENTICAR.toString())){
            
            return Acao.AUTENTICAR;
            
        }else{
            return null;
        }
    }
    
}
