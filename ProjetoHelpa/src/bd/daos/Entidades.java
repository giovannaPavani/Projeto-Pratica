package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Entidades 
{
	public static boolean cadastrado(int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HENTIDADES " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao procurar entidade");
        }

        return retorno;
    }
    
    public static boolean cadastrado(String usuario) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HENTIDADES " +
                  "WHERE USUARIO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, usuario);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao procurar entidade");
        }

        return retorno;
    }
    
    public static boolean existe(int codigo) 
    {
        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HENTIDADES " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setInt(1, codigo);
            BDSQLServer.COMANDO.executeQuery();
        }
        catch (SQLException erro)
        {
            return false;
        }
        return true;
    }

    public static void incluir(Entidade entidade) throws Exception
    {
        if (entidade==null)
            throw new Exception("Entidade não fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO HENTIDADES " +
                  "(CODIGO, NOME, CPNJ, ENDERECO, EMAIL, TELEFONE, CONTA, AGENCIA,USUARIO, SENHA, VISUALIZACOES, SITE, IMAGEM) " +
                  "VALUES " +
                  "(?,?,?,?,?,?,?,?,?,?,?)"; // guarda o lugar para dps a gnt colocar uma variavel

            BDSQLServer.COMANDO.prepareStatement(sql);
            
            //substituir as '?'
            BDSQLServer.COMANDO.setInt    (1, entidade.getCodigo());
            BDSQLServer.COMANDO.setString (2, entidade.getNome());
		    BDSQLServer.COMANDO.setString (4, entidade.getCnpj());
		    BDSQLServer.COMANDO.setString (7, entidade.getEndereco());
		    BDSQLServer.COMANDO.setString (3, entidade.getEmail());
		    BDSQLServer.COMANDO.setString (3, entidade.getTelefone());
		    BDSQLServer.COMANDO.setString (5, entidade.getConta());
		    BDSQLServer.COMANDO.setString (6, entidade.getAgencia());
		    BDSQLServer.COMANDO.setString (8, entidade.getUsuario());
		    BDSQLServer.COMANDO.setString (9, entidade.getSenha());
		    BDSQLServer.COMANDO.setInt    (10, entidade.getVisualizacoes());
		    BDSQLServer.COMANDO.setString (11, entidade.getSite());
		    BDSQLServer.COMANDO.setString (12, entidade.getImagem());

            BDSQLServer.COMANDO.executeUpdate(); // executa o comando, todos são executados como "update" - atualiza o banco, menos select / tipo uma função void
            BDSQLServer.COMANDO.commit       (); // USAR APENAS se for insert, delete e update --> O RESTO N PRECISA // efetiva ex: funcionario e dependentes - transação, se n, o banco n fica consistente - tudo ou nada
        }
        catch (SQLException erro) 
        {
        	//se for um monte de comandos (tudo ou nd) e um der errado, tem q excluir td
        	//BDSQLServer.COMANDO.rollback(); --> desfaz o commit / oposto do commit
        	// transação = conjunto de comandos q são tudo ou nada
            throw new Exception ("Erro ao inserir entidade");
        }
    }

    public static void excluir(int codigo) throws Exception // fazer trigger, ao deletar, tirar os cod da entidade da tabela necessidades
    {
        if (!cadastrado(codigo))
            throw new Exception("Nao cadastrada");

        try
        {
            String sql;

            sql = "DELETE FROM HENTIDADES " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, codigo);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit       ();        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao excluir entidade");
        }
    }

    public static void alterar(Entidade entidade) throws Exception
    {
        if (entidade==null)
            throw new Exception ("Entidade nao fornecida");

        if (!cadastrado(entidade.getCodigo()))
            throw new Exception("Nao cadastrada");

        try
        {
            String sql;

            sql = "UPDATE HENTIDADES " +
                  "SET NOME=? " +
                  "SET CPNJ=?" +
                  "SET ENDERECO=?" +
                  "SET EMAIL=? " +
                  "SET TELEFONE=? " +
                  "SET CONTA=?" +
                  "SET AGENCIA=?" +
                  "SET USUARIO=?" +
                  "SET SENHA=?" +
                  "SET VISUALIZACOES= ?" +
                  "SET SITE= ?"+
                  "SET DESCRICAO= ?" +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, entidade.getCodigo());
            BDSQLServer.COMANDO.setString (2, entidade.getNome());
		    BDSQLServer.COMANDO.setString     (3, entidade.getEmail());
		    BDSQLServer.COMANDO.setString     (4, entidade.getCnpj());
		    BDSQLServer.COMANDO.setString     (5, entidade.getConta());
		    BDSQLServer.COMANDO.setString     (6, entidade.getAgencia());
		    BDSQLServer.COMANDO.setString     (7, entidade.getEndereco());
		    BDSQLServer.COMANDO.setString     (8, entidade.getUsuario());
		    BDSQLServer.COMANDO.setString     (9, entidade.getSenha());
		    BDSQLServer.COMANDO.setInt        (10, entidade.getVisualizacoes());
		    BDSQLServer.COMANDO.setString     (11, entidade.getSite());
		    BDSQLServer.COMANDO.setString     (12, entidade.getDescricao());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit       ();
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao atualizar dados de entidade");
        }
    }

    public static Entidade getEntidadeByCod(int codigo) throws Exception
    {
        Entidade entidade = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HENTIDADES " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); // n é mais para executar update, mas sim uma query (consulta) / ñ é void
            // uma tabelinha po
            if (!resultado.first()) // .last()/ .next()/ .previous()/ .absolute(10) --> retornam boolean
                throw new Exception ("Nao cadastrado");

            entidade = new Entidade(resultado.getInt  ("CODIGO"),
	                               resultado.getString("NOME"), // como q ele sb qual ie string eh?
	                               resultado.getString("EMAIL"),
							       resultado.getString("CNPJ"),
							       resultado.getString("CONTA"),
							       resultado.getString("AGENCIA"),
							       resultado.getString("ENDERECO"),
							       resultado.getString("USUARIO"),
							       resultado.getString("SENHA"),
							       resultado.getString("TELEFONE"),
							       resultado.getInt("VISUALIZACOES"),
							       resultado.getString("DESCRICAO"),
							       resultado.getString("SITE"),
							       resultado.getString("IMAGEM"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar entidade");
        }

        return entidade;
    }
    
    public static Entidade getPrimeiroRegistro() throws Exception
    {
        Entidade entidade = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HENTIDADES";

            BDSQLServer.COMANDO.prepareStatement(sql);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); // n é mais para executar update, mas sim uma query (consulta) / ñ é void
            // uma tabelinha po
            if (!resultado.first()) // .last()/ .next()/ .previous()/ .absolute(10) --> retornam boolean
                throw new Exception ("Nao há nada registrado na tabela");

            entidade = new Entidade(resultado.getInt  ("CODIGO"),
	                               resultado.getString("NOME"), 
	                               resultado.getString("EMAIL"),
							       resultado.getString("CNPJ"),
							       resultado.getString("CONTA"),
							       resultado.getString("AGENCIA"),
							       resultado.getString("ENDERECO"),
							       resultado.getString("USUARIO"),
							       resultado.getString("SENHA"),
							       resultado.getString("TELEFONE"),
							       resultado.getInt("VISUALIZACOES"),
							       resultado.getString("DESCRICAO"),
							       resultado.getString("SITE"),
							       resultado.getString("IMAGEM"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar registro");
        }

        return entidade;
    }
    
    public static Entidade getEntidadeByUsuario(String usuario) throws Exception
    {
        Entidade entidade = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HENTIDADES " +
                  "WHERE USUARIO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, usuario);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); // n é mais para executar update, mas sim uma query (consulta) / ñ é void
            // uma tabelinha po
            if (!resultado.first()) // .last()/ .next()/ .previous()/ .absolute(10) --> retornam boolean
                throw new Exception ("Nao cadastrado");

            entidade = new Entidade(resultado.getInt  ("CODIGO"),
	                               resultado.getString("NOME"), // como q ele sb qual ie string eh?
	                               resultado.getString("EMAIL"),
							       resultado.getString("CNPJ"),
							       resultado.getString("CONTA"),
							       resultado.getString("AGENCIA"),
							       resultado.getString("ENDERECO"),
							       resultado.getString("USUARIO"),
							       resultado.getString("SENHA"),
							       resultado.getString("TELEFONE"),
							       resultado.getInt("VISUALIZACOES"),
							       resultado.getString("DESCRICAO"),
							       resultado.getString("SITE"),
							       resultado.getString("IMAGEM"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar entidade");
        }

        return entidade;
    }
    
    public static Entidade getEntidadeById(int id) throws Exception
    {
        Entidade entidade = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HENTIDADES " +
                  "WHERE ID = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, id);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); // n é mais para executar update, mas sim uma query (consulta) / ñ é void
            // uma tabelinha po
            if (!resultado.first()) // .last()/ .next()/ .previous()/ .absolute(10) --> retornam boolean
                throw new Exception ("Nao cadastrado");

            entidade = new Entidade(resultado.getInt  ("CODIGO"),
	                               resultado.getString("NOME"), // como q ele sb qual ie string eh?
	                               resultado.getString("EMAIL"),
							       resultado.getString("CNPJ"),
							       resultado.getString("CONTA"),
							       resultado.getString("AGENCIA"),
							       resultado.getString("ENDERECO"),
							       resultado.getString("USUARIO"),
							       resultado.getString("SENHA"),
							       resultado.getString("TELEFONE"),
							       resultado.getInt("VISUALIZACOES"),
							       resultado.getString("DESCRICAO"),
							       resultado.getString("SITE"),
							       resultado.getString("IMAGEM"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar entidade");
        }

        return entidade;
    }
    
    public static MeuResultSet getNecessidades(int codigo) throws Exception
    {
    	MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "selectNecessidades_sp = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, codigo);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); // n é mais para executar update, mas sim uma query (consulta) / ñ é void

            if (!resultado.first()) // .last()/ .next()/ .previous()/ .absolute(10) --> retornam boolean
                throw new Exception ("Nao cadastrado");
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar necessidades da entidade");
        }

        return resultado;
    }

    public static MeuResultSet getEntidades() throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HENTIDADES";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar entidades");
        }

        return resultado;
    }
    
    public static MeuResultSet getEntidadesVisu() throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HENTIDADES ORDER BY VISUALIZACOES";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar entidades");
        }

        return resultado;
    }
}
