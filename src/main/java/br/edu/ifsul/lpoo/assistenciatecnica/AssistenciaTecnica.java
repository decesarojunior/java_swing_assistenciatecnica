/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package br.edu.ifsul.lpoo.assistenciatecnica;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author telmo junior
 */
public class AssistenciaTecnica {
    
            private Controle controle;
    
           public AssistenciaTecnica(){
                         
                         //esse try faz o tratamento de uma possivel excessao gerada no método conectarBD da classe PersistenciaJDBC, pois, contem o throws Exception. 
                        try {
                                            controle = new Controle();//cria a instancia e atribui para o atributo controle.

                                            if(controle.conectarBD()){

                                                controle.initComponents();

                                            }else{

                                                JOptionPane.showMessageDialog(null, "Não conectou no Banco de Dados!", "Banco de Dados", JOptionPane.ERROR_MESSAGE);
                                            }

                        } catch (Exception ex) {

                                            JOptionPane.showMessageDialog(null, "Erro ao tentar conectar no Banco de Dados: "+ex.getLocalizedMessage(), "Banco de Dados", JOptionPane.ERROR_MESSAGE);
                                            ex.printStackTrace();
                        }

           }
    
            public static void main(String[] args){
                       //cria a instancia e automaticamente executa o construtor.
                        new AssistenciaTecnica();
            }
}
