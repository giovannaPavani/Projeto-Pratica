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
                  "FROM ENTIDADES " +
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

    public static void incluir(Entidade entidade) throws Exception
    {
        if (entidade==null)
            throw new Exception("Entidade não fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO ENTIDADES " +
                  "(CODIGO,NOME,CPNJ, ENDERECO, EMAIL, TELEFONE, CONTA, 
		    AGENCIA,USUARIO, SENHA, VISUALIZACOES) " +
                  "VALUES " +
                  "(?,?,?,?,?,?,?,?,?,?,?)"; // guarda o lugar para dps a gnt colocar uma variavel

            BDSQLServer.COMANDO.prepareStatement(sql);
            
            //substituir as '?'
            BDSQLServer.COMANDO.setInt    (1, pessoa.getCodigo());
            BDSQLServer.COMANDO.setString (2, pessoa.getNome());
	    BDSQLServer.COMANDO.setString (4, pessoa.getCpnj());
	    BDSQLServer.COMANDO.setString (7, pessoa.getendereco());
	    BDSQLServer.COMANDO.setString (3, pessoa.getEmail());
	    BDSQLServer.COMANDO.setString (3, pessoa.getTelefone());
	    BDSQLServer.COMANDO.setString (5, pessoa.getConta());
	    BDSQLServer.COMANDO.setString (6, pessoa.getAgencia());
	    BDSQLServer.COMANDO.setString (8, pessoa.getUsuario());
	    BDSQLServer.COMANDO.setString (9, pessoa.getSenha());
	    BDSQLServer.COMANDO.setInt (9, pessoa.getVisualizacoes());


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

    public static void excluir(int codigo) throws Exception
    {
        if (!cadastrado(codigo))
            throw new Exception("Nao cadastrada");

        try
        {
            String sql;

            sql = "DELETE FROM ENTIDADES " +
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

            sql = "UPDATE ENTIDADES " +
                  "SET NOME=? " +
		  "SET CPNJ=?" +
		  "SET ENDERECO=?" +
                  "SET EMAIL=? " +
                  "SET TELEFONE=? " +
		  "SET CONTA=?" +
		  "SET AGENCIA=?" +
		  "SET USUARIO=?" +
		  "SET SENHA=?" +
                  "WHERE CODIGO = ?";
                  "WHERE VISUALIZACOES= ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, pessoa.getCodigo());
            BDSQLServer.COMANDO.setString (2, pessoa.getNome());
	    BDSQLServer.COMANDO.setString (3, pessoa.getEmail());
	    BDSQLServer.COMANDO.setString (4, pessoa.getCpf());
	    BDSQLServer.COMANDO.setString (5, pessoa.getConta());
	    BDSQLServer.COMANDO.setString (6, pessoa.getAgencia());
	    BDSQLServer.COMANDO.setString (7, pessoa.getendereco());
	    BDSQLServer.COMANDO.setString (8, pessoa.getUsuario());
	    BDSQLServer.COMANDO.setString (9, pessoa.getSenha());
	    BDSQLServer.COMANDO.setString (9, pessoa.getVisuzalizacoes());


            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit       ();
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao atualizar dados de entidade");
        }
    }

    public static Entidade getEntidade(int codigo) throws Exception
    {
        Livro livro = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM ENTIDADES " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); // n é mais para executar update, mas sim uma query (consulta) / ñ é void
            // uma tabelinha po
            if (!resultado.first()) // .last()/ .next()/ .previous()/ .absolute(10) --> retornam boolean
                throw new Exception ("Nao cadastrado");

            pessoa = new Pessoa(resultado.getInt  ("CODIGO"),
                               resultado.getString("NOME"), // como q ele sb qual ie string eh?
                               resultado.getString("EMAIL"),
			       resultado.getString("CPF"),
			       resultado.getString("CONTA"),
			       resultado.getString("AGENCIA"),
			       resultado.getString("ENDERECO"),
			       resultado.getString("USUARIO"),
			       resultado.getString("SENHA"),
			       resultado.getInt("VISUALIZACOES"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar entidade");
        }

        return entidade;
    }

    public static MeuResultSet getEntidades() throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM ENTIDADES";

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