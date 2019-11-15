package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class daoPessoa
{
    public static boolean cadastrado(int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PESSOAS " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao procurar pessoa");
        }

        return retorno;
    }

    public static void incluir(bdoPessoa pessoa) throws Exception
    {
        if (pessoa==null)
            throw new Exception("Pessoa não fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO PESSOAS " +
                  "(CODIGO,NOME,EMAIL, CPF, CONTA, AGENCIA, ENDERECO, USUARIO, SENHA) " +
                  "VALUES " +
                  "(?,?,?,?,?,?,?,?,?)"; // guarda o lugar para dps a gnt colocar uma variavel

            BDSQLServer.COMANDO.prepareStatement(sql);
            
            //substituir as '?'
            BDSQLServer.COMANDO.setInt    (1, pessoa.getCodigo());
            BDSQLServer.COMANDO.setString (2, pessoa.getNome());
	    BDSQLServer.COMANDO.setString (3, pessoa.getEmail());
	    BDSQLServer.COMANDO.setString (4, pessoa.getCpf());
	    BDSQLServer.COMANDO.setString (5, pessoa.getConta());
	    BDSQLServer.COMANDO.setString (6, pessoa.getAgencia());
	    BDSQLServer.COMANDO.setString (7, pessoa.getEndereco());
	    BDSQLServer.COMANDO.setString (8, pessoa.getUsuario());
	    BDSQLServer.COMANDO.setString (9, pessoa.getSenha());


            BDSQLServer.COMANDO.executeUpdate(); // executa o comando, todos são executados como "update" - atualiza o banco, menos select / tipo uma função void
            BDSQLServer.COMANDO.commit       (); // USAR APENAS se for insert, delete e update --> O RESTO N PRECISA // efetiva ex: funcionario e dependentes - transação, se n, o banco n fica consistente - tudo ou nada
        }
        catch (SQLException erro) 
        {
        	//se for um monte de comandos (tudo ou nd) e um der errado, tem q excluir td
        	//BDSQLServer.COMANDO.rollback(); --> desfaz o commit / oposto do commit
        	// transação = conjunto de comandos q são tudo ou nada
            throw new Exception ("Erro ao inserir pessoa");
        }
    }

    public static void excluir(int codigo) throws Exception
    {
        if (!cadastrado(codigo))
            throw new Exception("Nao cadastrada");

        try
        {
            String sql;

            sql = "DELETE FROM PESSOAS " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, codigo);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit       ();        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao excluir pessoa");
        }
    }

    public static void alterar(bdoPessoa pessoa) throws Exception
    {
        if (pessoa==null)
            throw new Exception ("Pessoa nao fornecida");

        if (!cadastrado(pessoa.getCodigo()))
            throw new Exception("Nao cadastrada");

        try
        {
            String sql;

            sql = "UPDATE PESSOAS " +
                  "SET NOME=? " +
                  "SET EMAIL=? " +
		  "SET CPF=?" +
		  "SET CONTA=?" +
		  "SET AGENCIA=?" +
		  "SET ENDERECO=?" +
		  "SET USUARIO=?" +
		  "SET SENHA=?" +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, pessoa.getCodigo());
            BDSQLServer.COMANDO.setString (2, pessoa.getNome());
	    BDSQLServer.COMANDO.setString (3, pessoa.getEmail());
	    BDSQLServer.COMANDO.setString (4, pessoa.getCpf());
	    BDSQLServer.COMANDO.setString (5, pessoa.getConta());
	    BDSQLServer.COMANDO.setString (6, pessoa.getAgencia());
	    BDSQLServer.COMANDO.setString (7, pessoa.getEndereco());
	    BDSQLServer.COMANDO.setString (8, pessoa.getUsuario());
	    BDSQLServer.COMANDO.setString (9, pessoa.getSenha());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit       ();
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao atualizar dados de pessoa");
        }
    }

    public static bdoPessoa getPessoa(int codigo) throws Exception
    {
        bdoPessoa pessoa = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PESSOAS " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); // n é mais para executar update, mas sim uma query (consulta) / ñ é void
            // uma tabelinha po
            if (!resultado.first()) // .last()/ .next()/ .previous()/ .absolute(10) --> retornam boolean
                throw new Exception ("Nao cadastrado");

            pessoa = new bdoPessoa(resultado.getInt   ("CODIGO"),
                               resultado.getString("NOME"), // como q ele sb qual ie string eh?
                               resultado.getString ("EMAIL"),
			       resultado.getString("CPF"),
			       resultado.getString("CONTA"),
			       resultado.getString("AGENCIA"),
			       resultado.getString("ENDERECO"),
			       resultado.getString("USUARIO"),
			       resultado.getString("SENHA"),
			       resultado.getString("TELEFONE")
);
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar pessoa");
        }

        return pessoa;
    }

    public static MeuResultSet getPessoas () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PESSOAS";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar pessoas");
        }

        return resultado;
    }
    
    public static MeuResultSet getDoacoes (int cod) throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "select * from Pessoas p, Doacoes d where d.codPessoa = p.codPessoa";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar doacoes de pessoaa");
        }

        return resultado;
    }
}