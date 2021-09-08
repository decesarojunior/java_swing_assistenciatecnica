
package br.edu.ifsul.lpoo.assistenciatecnica.model.dao;

import br.edu.ifsul.lpoo.assistenciatecnica.model.Cidade;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Perfil;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Produto;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Usuario;
import java.util.List;

/**
 *
 * @author Telmo Junior
 * 
 * interface java para a definiçao dos metodos que deverão ser implementados nas classes que 
 * irão realizar a persistencia dos dados no PostGresql (JPA e JDBC).
 */

public interface InterfacePersistencia {

    public Boolean conexaoAberta();
    
    public void fecharConexao();
    
    public Object find(Class c, Object id) throws Exception;
    
    public void persist(Object o) throws Exception;
    
    public void remover(Object o) throws Exception;
    
    public List<Perfil> getPerfis() throws Exception;
    
    public List<Produto> getProdutos() throws Exception;
    
    public Usuario doLogin(String cpf, String senha) throws Exception;
    
    public List<Cidade> getCidades(Cidade c) throws Exception;
    
    public List<Usuario> getUsuarios() throws Exception;
}
