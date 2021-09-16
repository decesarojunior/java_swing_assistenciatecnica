/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package br.edu.ifsul.lpoo.assistenciatecnica;

import br.edu.ifsul.lpoo.assistenciatecnica.gui.JFramePrincipal;
import br.edu.ifsul.lpoo.assistenciatecnica.gui.JMenuBarHome;
import br.edu.ifsul.lpoo.assistenciatecnica.gui.JPanelHome;
import br.edu.ifsul.lpoo.assistenciatecnica.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.lpoo.assistenciatecnica.gui.usuario.JPanelUsuario;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Usuario;
import br.edu.ifsul.lpoo.assistenciatecnica.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;

/**
 *
 * @author telmo
 * 
 *          MVC: Model - Controle - View  
 */
public class Controle {
    
            private PersistenciaJDBC conexaoJDBC;
            
            private JFramePrincipal frame; // frame principal da minha aplicação gráfica
            
            private JPanelAutenticacao pnlAutenticacao; //painel para a autenticacao do Jogador.
            
            private JMenuBarHome menuBar; //menu principal
            
            private JPanelHome pnlHome; // paine de boas vindas (home)
                        
            private JPanelUsuario pnlUsuario;  // tela de CRUD para Usuario.
    
            public Controle(){
                        
            }
            
            public boolean conectarBD() throws Exception {
        
                        conexaoJDBC = new PersistenciaJDBC();
        
                        if(conexaoJDBC!= null){

                                    return conexaoJDBC.conexaoAberta();
                        }
        
                        return false;
            }
    
            public void fecharBD(){

                    System.out.println("Fechando conexao com o Banco de Dados");
                    conexaoJDBC.fecharConexao();

            }
            
            public void initComponents(){
                
                        frame = new JFramePrincipal(this);      
                        
                        pnlAutenticacao = new JPanelAutenticacao(this);
                        menuBar = new JMenuBarHome(this);
                        pnlHome = new JPanelHome(this);
                        pnlUsuario = new JPanelUsuario(this);
                        
                        frame.addTela(pnlAutenticacao, "tela_autenticacao");//carta 1
                        frame.addTela(pnlHome, "tela_home");//carta 2
                        frame.addTela(pnlUsuario, "tela_usuario");//carta 3
                        
                        frame.showTela("tela_autenticacao"); // apreseta a carta cujo nome é "tela_autenticacao"
                        
                        frame.setVisible(true); // torna visível o jframe
            }
            
            public void autenticar(String cpf, String senha) {
        
                        try{

                            Usuario u =  getConexaoJDBC().doLogin(cpf, senha);
                            if(u != null){

                                JOptionPane.showMessageDialog(pnlAutenticacao, "Usuario "+u.getNome()+" autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                                frame.setJMenuBar(menuBar);//adiciona o menu de barra no frame
                                frame.showTela("tela_home");//muda a tela para o painel de boas vindas (home)

                            }else{

                                JOptionPane.showMessageDialog(pnlAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }catch(Exception e){

                            JOptionPane.showMessageDialog(pnlAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
                        }
        
            }
            
            public void showTela(String nomeTela){
        
                        if(nomeTela.equals("tela_usuario")){

                                    pnlUsuario.getTelaListagem().populaTable();
                        }

                        frame.showTela(nomeTela);
            }
            
            public PersistenciaJDBC getConexaoJDBC() {
                        return conexaoJDBC;
            }
    
}
