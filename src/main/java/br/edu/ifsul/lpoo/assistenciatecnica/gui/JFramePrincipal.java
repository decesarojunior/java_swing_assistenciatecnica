/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package br.edu.ifsul.lpoo.assistenciatecnica.gui;

import br.edu.ifsul.lpoo.assistenciatecnica.Controle;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author telmo
 */
public class JFramePrincipal  extends JFrame implements WindowListener {

            public Controle controle;
            public CardLayout cardLayout;
            public JPanel painel;
            
            public JFramePrincipal(Controle controle){
                        this.controle = controle;        
                        initComponents();
            }
    
            private void initComponents(){
            
                        this.setTitle("Assistência Técnica de Equipamentos Eletrônicos");//seta o título
                        
                        this.setMinimumSize(new Dimension(600,600)); //tamanho minimo quando for reduzido.

                        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // por padrão abre maximizado.        
                        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );// finaliza o processo quando o frame é fechado.            
                        this.addWindowListener(this);//adiciona o listener no frame
        
                        cardLayout = new CardLayout();//iniciando o gerenciador de layout para esta JFrame
                        painel = new JPanel();//inicializacao
                        painel.setLayout(cardLayout);//definindo o cardLayout para o paineldeFundo

                        this.add(painel);  //adiciona no JFrame o paineldeFundo
                
             }
            
            public void addTela(JPanel p, String nome){   
                        painel.add(p, nome);
            }

            public void showTela(String nome){
                        cardLayout.show(painel, nome);
            }
    
            @Override
            public void windowOpened(WindowEvent we) {

            }

            @Override
            public void windowClosing(WindowEvent we) {        
            }

            @Override
            public void windowClosed(WindowEvent we) {        
            }

            @Override
            public void windowIconified(WindowEvent we) {        
            }

            @Override
            public void windowDeiconified(WindowEvent we) {        
            }

            @Override
            public void windowActivated(WindowEvent we) {        
            }

            @Override
            public void windowDeactivated(WindowEvent we) {        
            }
    
    
    
}
