package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Doacoes {
	
	public static boolean cadastrado(int id) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HDOACOES " +
                  "WHERE ID = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, id);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao procurar doacao");
        }

        return retorno;
    }
    
    public static boolean existe(int id) 
    {
        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HDOACOES " +
                  "WHERE ID = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);
            BDSQLServer.COMANDO.setInt(1, id);
            BDSQLServer.COMANDO.executeQuery();
        }
        catch (SQLException erro)
        {
            return false;
        }
        return true;
    }

    public static void incluir(Doacao doacao) throws Exception
    {
        if (doacao==null)
            throw new Exception("Doacao não fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO HDOACOES " +
                  "(ID, CODPESSOA, PRODUTO, CODENTIDADE, DATA, ENTREGUE, QUANTIDADE) " +
                  "VALUES " +
                  "(?,?,?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement(sql);
            
            BDSQLServer.COMANDO.setInt    (1, doacao.getId());
            BDSQLServer.COMANDO.setInt    (2, doacao.getCodPessoa());
            BDSQLServer.COMANDO.setString (3, doacao.getProduto());
            BDSQLServer.COMANDO.setInt    (4, doacao.getCodEntidade());
            //BDSQLServer.COMANDO.setDate   (5, doacao.getData());
            //BDSQLServer.COMANDO.setChar   (6, doacao.getEntregue());
		    BDSQLServer.COMANDO.setString (7, doacao.getQuantidade());


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

    public static void excluir(int id) throws Exception
    {
        if (!cadastrado(id))
            throw new Exception("Nao cadastrada");

        try
        {
            String sql;

            sql = "DELETE FROM HDOACOES " +
                  "WHERE ID=?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, id);

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit       ();        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao excluir doacao");
        }
    }

    public static void alterar(Pessoa pessoa) throws Exception
    {
        if (pessoa==null)
            throw new Exception ("Doacao nao fornecida");

        if (!cadastrado(pessoa.getCodigo()))
            throw new Exception("Nao cadastrada");

        try
        {
            String sql;

            sql = "UPDATE HDOACOES " +
                  "SET CODPESSOA=? " +
                  "SET PRODUTO=? " +
				  "SET CODENTIDADE=?" +
				  "SET DATA=?" +
				  "SET ENTREGUE=?" +
				  "SET QUANTIDADE=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, pessoa.getCodigo());
            BDSQLServer.COMANDO.setString (2, pessoa.getNome());
		    BDSQLServer.COMANDO.setString (3, pessoa.getEmail());
		    BDSQLServer.COMANDO.setString (4, pessoa.getCpf());
		    BDSQLServer.COMANDO.setString (5, pessoa.getConta());
		    BDSQLServer.COMANDO.setString (6, pessoa.getAgencia());
		    BDSQLServer.COMANDO.setString (7, pessoa.getEndereco());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit       ();
        }
        catch (SQLException erro)
        {
            throw new Exception("Erro ao atualizar dados de pessoa");
        }
    }

    public static Pessoa getPessoa(int codigo) throws Exception
    {
        Pessoa pessoa = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HPESSOAS " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); // n é mais para executar update, mas sim uma query (consulta) / ñ é void
            // uma tabelinha po
            if (!resultado.first()) // .last()/ .next()/ .previous()/ .absolute(10) --> retornam boolean
                throw new Exception ("Nao cadastrado");

            pessoa = new Pessoa(resultado.getInt   ("CODIGO"),
                               resultado.getString("NOME"), // como q ele sb qual ie string eh?
                               resultado.getString ("EMAIL"),
						       resultado.getString("CPF"),
						       resultado.getString("CONTA"),
						       resultado.getString("AGENCIA"),
						       resultado.getString("ENDERECO"),
						       resultado.getString("USUARIO"),
						       resultado.getString("SENHA"),
						       resultado.getString("TELEFONE"),
						       resultado.getString("CIDADE"),
						       resultado.getString("UF")
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
                  "FROM HPESSOAS";

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
    
    public static MeuResultSet getPessoasDoa () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "select p.codigo as 'Codigo', p.nome as 'Nome', count(d.codPessoa) as 'Vezes' from HPessoas p, HDoacoes d where p.codigo = d.codPessoa group by p.codigo, p.nome order by Vezes desc ";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar rank de doações");
        }

        return resultado;
    }
}
