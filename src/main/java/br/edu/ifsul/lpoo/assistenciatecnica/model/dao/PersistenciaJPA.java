/*
 * 
 */
package br.edu.ifsul.lpoo.assistenciatecnica.model.dao;

import br.edu.ifsul.lpoo.assistenciatecnica.model.Cidade;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Perfil;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Produto;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Usuario;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Telmo
 */
public class PersistenciaJPA  implements InterfacePersistencia {
    
    public EntityManagerFactory factory;    //fabrica de gerenciadores de entidades
    public EntityManager entity;            //gerenciador de entidades JPA
        
    public PersistenciaJPA(){
        
        //parametro: é o nome da unidade de persistencia (Persistence Unit)
        factory = Persistence.createEntityManagerFactory("pu_db_projetoassitenciatecnica_2021_1");
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        
        return entity.isOpen();        
    }

    @Override
    public void fecharConexao() {
        
        entity.close();        
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        
       return entity.find(c, id);//encontra um determinado registro
        
    }

    @Override
    public void persist(Object o) throws Exception {
        
        entity.getTransaction().begin();// abrir a transacao.
        entity.persist(o); //realiza o insert ou update.
        entity.getTransaction().commit(); //comita a transacao (comando sql)
    }

    @Override
    public void remover(Object o) throws Exception {
        
        entity.getTransaction().begin();// abrir a transacao.
        entity.remove(o); //realiza o delete
        entity.getTransaction().commit(); //comita a transacao (comando sql)
        
    }

    @Override
    public List<Perfil> getPerfis() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produto> getProdutos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario doLogin(String cpf, String senha) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cidade> getCidades(Cidade c) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> getUsuarios() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
