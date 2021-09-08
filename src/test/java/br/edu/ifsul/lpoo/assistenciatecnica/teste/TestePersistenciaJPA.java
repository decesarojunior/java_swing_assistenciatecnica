package br.edu.ifsul.lpoo.assistenciatecnica.teste;

import br.edu.ifsul.lpoo.assistenciatecnica.model.Pais;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Produto;
import br.edu.ifsul.lpoo.assistenciatecnica.model.dao.PersistenciaJPA;
import org.junit.Test;

/**
 *
 * @author Telmo
 */
public class TestePersistenciaJPA {
    
    //@Test
    public void testarConexao() throws Exception {
        
        PersistenciaJPA persistencia = new PersistenciaJPA();
        
        if(persistencia.conexaoAberta()){
            
            System.out.println("Conexao com o BD aberta utilizando JPA");
            persistencia.fecharConexao();
            
        }else{
            
            System.out.println("Não abriu conexao via jpa");
        }        
    }
    
    //@Test
    public void testarInsercaoPais() throws Exception {
        
        PersistenciaJPA persistencia = new PersistenciaJPA();
        
        if(persistencia.conexaoAberta()){
            
            //vai realizar um select na tabela tb_pais com o where id = 2
            Pais p = (Pais) persistencia.find(Pais.class, new Integer(2));
            
            if(p != null){
                
                //quando o p não for nulo, o que significa ?
                System.out.println("Encontrou o Pais "+p.getId()+ " com o nome "+p.getNome());
                
                //p.setNome("Uruguai"); //alterando o nome do objeto                
                //persistencia.persist(p); // alterando o nome no registro.
                
                persistencia.remover(p); // realiza o update.
                
            }else{
                
                //quando o p for nulo, o que significa ?
                
                p = new Pais();
            
                p.setNome("Argentina");

                persistencia.persist(p);//inserto into

                System.out.println("Inseriu o Pais "+p.getId()+ " com o nome "+p.getNome());
                
            }
            
            System.out.println("Conexao com o BD aberta utilizando JPA");
            

            
            persistencia.fecharConexao();
            
        }else{
            
            System.out.println("Não abriu conexao via jpa");
        }        
    }
        
    //@Test
    public void testarPersistenciaProduto() throws Exception {
        
        PersistenciaJPA persistencia = new PersistenciaJPA();
        
        if(persistencia.conexaoAberta()){
            //para recuperacao de listas de objetos  com JPA, assistir a seguinte videoaula
            //Videoaula 3 - Consultas com NamedQueries e Criteria
            
            Produto p = (Produto) persistencia.find(Produto.class, new Integer(1));
            if(p == null){
                
                //significa que não existe o registro com chave primaria = 1.
                p = new Produto();
                p.setNome("Placa Mae");
                
                persistencia.persist(p);// insert into.
                
                System.out.println("Inseriu o produto: "+p.getId());                                                
            }else{
                
                //existe.                
                System.out.println(" Encontrou o produto "+p.getNome());
                
                p.setNome("Placa Mae Asus");
                
                persistencia.persist(p); //update.
                
                persistencia.remover(p); // delete.
            }
                        
            persistencia.fecharConexao();
        }else{
            System.out.println("Não conectou no BD!!");
        }
        
        
        
    }
    
}
