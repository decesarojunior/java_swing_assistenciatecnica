
package br.edu.ifsul.lpoo.assistenciatecnica.teste;

import br.edu.ifsul.lpoo.assistenciatecnica.model.Pais;
import org.junit.Test;

import br.edu.ifsul.lpoo.assistenciatecnica.model.dao.PersistenciaJDBC;

/**
 *
 * @author Telmo Junior
 */
public class TestePersistenciaJDBC {
    
    //@Test
    public void testarPersistenciaPaisJDBC() throws Exception{
        
        //aqui vai executar o construtor da classe PersistenciaJDBC
        //ou seja, vai tentar abrir a conexao via JDBC.
        PersistenciaJDBC conexaoJDBC = new PersistenciaJDBC();
        
        if(conexaoJDBC.conexaoAberta()){
            
            Pais p = (Pais) conexaoJDBC.find(Pais.class, 1);                                    
            
            if(p == null){
                System.out.println("Não encontrou o pais id=1");
            }else{
                System.out.println("Econtrou o pais : "+p.getNome());
            }
            
            System.out.println("Conexao Aberta com sucesso JDBC");
            
            conexaoJDBC.fecharConexao();
        }else{
            
            System.out.println("Nao abriu a conexao JDBC");
        }
        
    }
    
    
    //@Test
    public void testarConexaoJDBC() throws Exception{
        
        //aqui vai executar o construtor da classe PersistenciaJDBC
        //ou seja, vai tentar abrir a conexao via JDBC.
        PersistenciaJDBC conexaoJDBC = new PersistenciaJDBC();
        
        if(conexaoJDBC.conexaoAberta()){
            
            System.out.println("Conexao Aberta com sucesso JDBC");
            
            conexaoJDBC.fecharConexao();
        }else{
            
            System.out.println("Nao abriu a conexao JDBC");
        }
        
    }
}
