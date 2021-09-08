
package br.edu.ifsul.lpoo.assistenciatecnica.model.dao;

import br.edu.ifsul.lpoo.assistenciatecnica.model.Cidade;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Estado;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Pais;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Perfil;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Permissao;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Produto;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Acao;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Componente;
import br.edu.ifsul.lpoo.assistenciatecnica.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Telmo
 */
public class PersistenciaJDBC implements InterfacePersistencia {
    
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "123456";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_projetoassistenciatecnica2021_01";
    
    private Connection con = null;
        
    public PersistenciaJDBC() throws Exception {
        
            Class.forName(DRIVER); //carregamento do driver postgresql em tempo de execução
            System.out.println("Tentando estabelecer conexao JDBC com : "+URL+" ...");
            
            this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);                                                  
    }

    @Override
    public Boolean conexaoAberta() {
        
        try {
            if(con != null)
                return !con.isClosed();//verifica se a conexao está aberta
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return false;
        
    }

    @Override
    public void fecharConexao() {
       
        try{                               
            this.con.close();//fecha a conexao.
            System.out.println("Fechou conexao JDBC");
        }catch(SQLException e){            
            e.printStackTrace();//gera uma pilha de erro na saida.
        } 
        
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        //passo 1: identificar a classe alvo para a execução do comando de selecao.
        
        if(c == Pais.class){            
            //montar/preparar o comando de selecao.
            PreparedStatement ps = this.con.prepareStatement("select id, nome from tb_pais where id = ? ");
            ps.setInt(1, Integer.parseInt(id.toString()));//substitui o primeiro ? no comando sql.
            
            ResultSet rs = ps.executeQuery();//resultSet é uma matriz com um ponteiro que inicialmente está apontando para a posicao -1.
            
            if(rs.next()){//retorna verdadeiro se existir a proxima linha.
                
                Pais p = new Pais();
                p.setId(rs.getInt("id"));//id representa o nome da coluna.
                p.setNome(rs.getString("nome"));
                
                ps.close();//fecha o cursor.
                
                return p;
            }
            
        }else if (c == Estado.class){
            
        }else if (c == Cidade.class ){
            
        }else if (c == Produto.class){
            
        }else if (c == Perfil.class){
            
            // recuperar/selecionar um determinado perfil
            //em seguida recuperar as permissoes desse perfil.
            Perfil perfil = null;
            
            // executar um select em tb_perfil
            PreparedStatement ps = 
            this.con.prepareStatement("select p.id, p.nome "
                                                 + " from tb_perfil p "
                                                 + " where p.id = ? ");
            ps.setInt(1, new Integer(id.toString()));
            ResultSet rs = ps.executeQuery();//executa a query
            
            if(rs.next()){
                
                perfil = new Perfil();//inicialização do objeto que será retornado.
                perfil.setId(rs.getInt("id"));
                perfil.setNome(rs.getString("nome"));
                
                ps.close();//fecha o cursor
                
                // executar um select envolvendo tb_permissoes e tb_permissao.
                ps = this.con.prepareStatement("select pm.id, pm.acao, pm.componente, pm.descricao "
                                                 + " from tb_permissoes ps, tb_permissao pm "
                                                 + " where ps.permissao_id=pm.id and  ps.perfil_id = ? ");  
                ps.setInt(1, perfil.getId());
                
                rs = ps.executeQuery();
                while(rs.next()){
                    
                    Permissao p = new Permissao();
                    p.setId(rs.getInt("id"));
                    p.setAcao(Acao.getAcao(rs.getString("acao")));
                    p.setComponente(Componente.getComponente(rs.getString("componente")));
                    p.setDescricao(rs.getString("descricao"));
                    
                    perfil.setPermissao(p);//adiciona no perfil a permissao                    
                }
                rs.close();//fecha o cursor
                ps.close();//fecha
            }
                    
            return perfil;            
            
            
        }else if (c == Permissao.class){
            
            PreparedStatement ps = 
            this.con.prepareStatement("select p.id, p.descricao, p.acao, p.componente "
                                        + "from tb_permissao p "
                                         + "where id = ? ");
                        
            ps.setInt(1, Integer.parseInt(id.toString()));
            
            ResultSet rs = ps.executeQuery();//o ponteiro do REsultSet inicialmente está na linha -1
            
            if(rs.next()){//se a matriz (ResultSet) tem uma linha
                
                Permissao p = new Permissao();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));                
                p.setAcao(Acao.getAcao(rs.getString("acao")));
                p.setComponente(Componente.getComponente(rs.getString("componente")));
                                                
                return p;
            }            
            
        }
                
        return null;
        
    }

    @Override
    public void persist(Object o) throws Exception {
                
        if(o instanceof Pais){
            
            Pais p = (Pais) o;//conversao
            
            if(p.getId() == null){
                
                //prepara a instrução.
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pais (id, nome) values (nextval('seq_pais_id'), ? )"); 
                ps.setString(1, p.getNome());
                ps.execute();                
                
            }else{
                
                PreparedStatement ps = this.con.prepareStatement("update tb_pais set nome = ? where id = ? "); //prepara a instrução.
                ps.setString(1, p.getNome());
                ps.setInt(2, p.getId());
                ps.execute();                
            }
            
            
        }else if( o instanceof Estado){
                        
            
            Estado e = (Estado) o;//conversao
            
            if(e.getId() == null){
                
                //prepara a instrução.
                PreparedStatement ps = this.con.prepareStatement("insert into tb_estado (id, nome, uf, pais_id) values (nextval('seq_pais_id'), ?,?,?)"); 
                ps.setString(1, e.getNome());
                ps.setString(2, e.getUf());                                
                ps.setInt(3, e.getPais().getId());
                ps.execute();                
                
            }else{
                
                PreparedStatement ps = this.con.prepareStatement("update tb_estado set nome = ?, uf=?, pais_id = ? where id = ? "); //prepara a instrução.
                
                ps.setString(1, e.getNome());
                ps.setString(2, e.getUf());
                ps.setInt(3, e.getPais().getId());
                ps.setInt(4, e.getId());
                ps.execute();                
            }
                                                
        }else if(o instanceof Perfil){
            
            Perfil perfil = (Perfil) o;
            
            //descobrir se é insert ou update
            if(perfil.getId() == null){
                
                //prepara a instrução.
                PreparedStatement ps = this.con.prepareStatement("insert into tb_perfil (id, nome) values (nextval('seq_perfil_id'), ?) returning id; "); 
                ps.setString(1, perfil.getNome());
                
                //insert em tb_perfil
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    
                    perfil.setId(rs.getInt("id"));//seta o id gerado pela sequence.
                    
                    if(perfil.getPermissoes() != null && perfil.getPermissoes().size() > 0){
                        
                        for(Permissao p : perfil.getPermissoes()){
                            
                            ps = this.con.prepareStatement("insert into tb_permissoes (perfil_id, permissao_id) values (?, ?)");
                            ps.setInt(1, perfil.getId());
                            ps.setInt(2, p.getId());
                            
                            //insert em tb_permissoes
                            ps.execute();
                        }
                    }                                        
                }
                rs.close();//fecha o cursor
                ps.close();
                
            }else{
                
                //estratégia para alteração: remove tudo e depois insere novamente.
                
                PreparedStatement ps = this.con.prepareStatement("update tb_perfil set nome = ? where id = ?"); 
                ps.setString(1, perfil.getNome());
                ps.execute();
                
                ps = this.con.prepareStatement("delete from tb_permissoes where perfil_id = ? ");
                ps.setInt(1, perfil.getId());
                ps.execute();
                
                if(perfil.getPermissoes() != null && perfil.getPermissoes().size() > 0){

                    for(Permissao p : perfil.getPermissoes()){

                        ps = this.con.prepareStatement("insert into tb_permissoes (perfil_id, permissao_id) values (?, ?)");
                        ps.setInt(1, perfil.getId());
                        ps.setInt(2, p.getId());

                        //insert em tb_permissoes
                        ps.execute();
                    }
                    
                    ps.close();
                }                                                
            }            
        }else if(o instanceof Permissao){
            
            Permissao permissao = (Permissao) o;
            if(permissao.getId() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_permissao (id, acao, componente, descricao) values (nextval('seq_permissao_id'),?,?,?); "); 
                ps.setString(1, permissao.getAcao().toString());
                ps.setString(2, permissao.getComponente().toString());
                ps.setString(3, permissao.getDescricao());
                ps.execute();
                
                ps.close();
            }else{
                
                //update em tb_permissao
            }
        }else if(o instanceof Usuario){
            
            Usuario u = (Usuario) o;
            if(u.getTipoPessoa() == null){ // aqui não é possivel usar o cpf pois ele não é incrementado pelo BD
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa (cpf, nome, dtCadastro, cidade_id, tipoPessoa) values (?,?,?,?,?)");
                ps.setString(1, u.getCpf());
                ps.setString(2, u.getNome());
                ps.setDate(3, new java.sql.Date(u.getDtCadastro().getTimeInMillis()));                
                ps.setInt(4, u.getCidade().getId());
                ps.setString(5, "US");
                
                if(!ps.execute()){//quando retorna false é a indicaçao que o comando sql foi executado com exito, portanto, é invertido.
                    System.out.println("criando usu ");
                    ps = this.con.prepareStatement("insert into tb_usuario (cpf, senha) values (?,?)");
                    ps.setString(1, u.getCpf());
                    ps.setString(2, u.getSenha());                    
                    ps.execute();
                }
                
            }else{

                    PreparedStatement ps = this.con.prepareStatement("update tb_pessoa set "
                                                                                + "nome = ?, "
                                                                                + "cidade_id = ? "
                                                                                + "where cpf = ? ");
                    ps.setString(1, u.getNome());
                    ps.setInt(2, u.getCidade().getId());
                    ps.setString(3, u.getCpf());
                    
                    if(!ps.execute()){

                        ps = this.con.prepareStatement("update tb_usuario set senha = ? where cpf = ?");
                        ps.setString(1, u.getSenha());
                        ps.setString(2, u.getCpf());                        
                        ps.execute();
                    }
            }
                           
        }                 
        
    }

    @Override
    public void remover(Object o) throws Exception {
        
        if(o instanceof Pais){
            
            Pais p = (Pais) o;//conversao
            
            PreparedStatement ps = this.con.prepareStatement("delete from tb_pais where id = ? ");
            ps.setInt(1, p.getId());
            ps.execute();
            
        }else if( o instanceof Estado){
               
            Estado e = (Estado) o;//conversao
            
            PreparedStatement ps = this.con.prepareStatement("delete from tb_estado where id = ? ");
            ps.setInt(1, e.getId());
            ps.execute();
            
        }else if(o instanceof Perfil){
            
            //delete em tb_permissoes
            
            Perfil perfil = (Perfil) o;
            PreparedStatement ps = this.con.prepareStatement("delete from tb_permissoes where perfil_id = ? ");
            ps.setInt(1, perfil.getId());
            ps.execute();

            //delete em tb_perfil.
            ps = this.con.prepareStatement("delete from tb_perfil where id = ? ");
            ps.setInt(1, perfil.getId());
            ps.execute();
                            
            ps.close();
            
        }else if(o instanceof Usuario){             
            
            Usuario u = (Usuario) o;//conversao para Pais, pois o tem uma instancia de Pais
            
            PreparedStatement ps = this.con.prepareStatement("delete from tb_usuario where cpf = ? ");            
            ps.setString(1, u.getCpf()); //substituição            
            ps.execute(); //executa a instrução sql para a remoção
            
            ps = this.con.prepareStatement("delete from tb_pessoa where cpf = ? ");            
            ps.setString(1, u.getCpf()); //substituição            
            ps.execute(); //executa a instrução sql para a remoção
            
            ps.close();
            
        }
        
    }

    @Override
    public List<Perfil> getPerfis() throws Exception {
       
        List<Perfil> lista = null;
        
        // executar um select em tb_perfil
        PreparedStatement ps = 
        this.con.prepareStatement("select p.id, p.nome "
                                             + " from tb_perfil p "
                                             + " order by p.id asc ");        
        ResultSet rs = ps.executeQuery();//executa a query
        
        lista = new ArrayList();
        
        while(rs.next()){

            Perfil perfil = new Perfil();//inicialização do objeto que será retornado.
            perfil.setId(rs.getInt("id"));
            perfil.setNome(rs.getString("nome"));            

            // executar um select envolvendo tb_permissoes e tb_permissao.
            PreparedStatement ps2 = this.con.prepareStatement("select pm.id, pm.acao, pm.componente, pm.descricao "
                                             + " from tb_permissoes ps, tb_permissao pm "
                                             + " where ps.permissao_id=pm.id and  ps.perfil_id = ? ");  
            ps2.setInt(1, perfil.getId());

            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){

                Permissao p = new Permissao();
                p.setId(rs2.getInt("id"));
                p.setAcao(Acao.getAcao(rs2.getString("acao")));
                p.setComponente(Componente.getComponente(rs2.getString("componente")));
                p.setDescricao(rs2.getString("descricao"));

                perfil.setPermissao(p);//adiciona no perfil a permissao                    
            }
            rs2.close();//fecha o cursor
            ps2.close();//fecha
            
            lista.add(perfil);
        }
        
        rs.close();
        ps.close();//fecha o cursor
        
        return lista;        
                
    }

    @Override
    public List<Produto> getProdutos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Usuario doLogin(String cpf, String senha) throws Exception {
        
        //insert into tb_pessoa (tipopessoa, cpf, dtcadastro, nome) values ('US', '00001347088', now(), 'teste');
        //insert into tb_usuario (senha, cpf) values ('1234' , '00001347088')
        //insert into tb_pais (id, nome) values (nextval('seq_pais_id'), 'Brasil');
        //insert into tb_estado(id, nome, uf, pais_id) values (nextval('seq_estado_id'), 'Rio Grande do Sul', 'RS', 2);
        //insert into tb_cidade(id, nome, estado_id) values (nextval('seq_estado_id'), 'Passo Fundo', 1), (nextval('seq_estado_id'), 'Gentil', 1), (nextval('seq_estado_id'), 'Marau', 1);
        
        
        
        Usuario usuario = null;
        
         PreparedStatement ps = 
            this.con.prepareStatement("select p.nome, u.cpf, u.senha from tb_usuario u, tb_pessoa p where u.cpf=p.cpf and u.cpf = ? and u.senha = ? ");
                        
            ps.setString(1, cpf);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();//o ponteiro do REsultSet inicialmente está na linha -1
            
            if(rs.next()){//se a matriz (ResultSet) tem uma linha

                usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
            }
        
            ps.close();
            return usuario;
        
    }
    
        @Override
    public List<Cidade> getCidades(Cidade c) throws Exception {
        
        List<Cidade> listaRetorno = null;
        
        try{            
            PreparedStatement ps = this.con.prepareStatement("select id, nome from tb_cidade order by id asc");            
            ResultSet rs = ps.executeQuery();
            listaRetorno = new ArrayList();            
            while(rs.next()){ //se o retorno (matriz) contiver linhas
                    Cidade cid= new Cidade();
                    cid.setId(rs.getInt("id")); //recupera a informação da coluna id na primeira linha
                    cid.setNome(rs.getString("nome")); //recupera a informação da coluna nome na primeira linha
                    listaRetorno.add(cid);                    
            }
            
        }catch(Exception e){
            
            e.printStackTrace();
        }
        
        return listaRetorno;
        
        
    }
    
        @Override
    public List<Usuario> getUsuarios() throws Exception {
        
        List<Usuario> listaRetorno = null;
        
                    
            PreparedStatement ps = this.con.prepareStatement("select p.cpf, p.nome, p.dtCadastro, p.tipoPessoa, "
                                                           + "c.id as idCidade, c.nome as nomeCidade, u.dtultimaacesso, u.senha "
                                                           + "from tb_pessoa p "
                                                           + "left join tb_cidade c on (c.id=p.cidade_id), tb_usuario u "
                                                           + "left join tb_perfil pf on (u.perfil_id=pf.id) "
                                                           + "where u.cpf=p.cpf "
                                                           + "order by p.nome asc");            
            ResultSet rs = ps.executeQuery();
            listaRetorno = new ArrayList();            
            while(rs.next()){ //se o retorno (matriz) contiver linhas
                    Usuario u= new Usuario();
                    u.setCpf(rs.getString("cpf"));
                    u.setNome(rs.getString("nome"));
                    Calendar dtCad = Calendar.getInstance();
                    dtCad.setTime(rs.getDate("dtCadastro"));
                    u.setDtCadastro(dtCad);
                    if(rs.getString("idCidade") != null){
                        u.setCidade(new Cidade(rs.getInt("idCidade"), rs.getString("nomeCidade")));
                    }                                        
                    if(rs.getDate("dtultimaacesso") != null){
                        Calendar dtUA = Calendar.getInstance();
                        dtUA.setTime(rs.getDate("dtultimaacesso"));
                        u.setDtultimaacesso(dtUA);
                    }
                    u.setSenha(rs.getString("senha"));
                    u.setTipoPessoa(rs.getString("tipoPessoa"));
                    
                    listaRetorno.add(u);                    
            }
                   
        return listaRetorno;        
        
    }
    
    
}
