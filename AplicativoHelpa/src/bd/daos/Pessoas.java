package bd.daos;
import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Pessoas 
{
	public static boolean cadastrado(int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HPESSOAS " +
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
    
    public static boolean existe(int codigo) 
    {
        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HPESSOAS " +
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

    public static void incluir(Pessoa pessoa) throws Exception
    {
        if (pessoa==null)
            throw new Exception("Pessoa não fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO HPESSOAS " +
                  "(CODIGO,NOME,EMAIL, CPF, ENDERECO, SENHA, TELEFONE, CIDADE, UF) " +
                  "VALUES " +
                  "(?,?,?,?,?,?,?)"; // guarda o lugar para dps a gnt colocar uma variavel

            BDSQLServer.COMANDO.prepareStatement(sql);
            
            //substituir as '?'
            BDSQLServer.COMANDO.setInt    (1, pessoa.getCodigo());
            BDSQLServer.COMANDO.setString (2, pessoa.getNome());
		    BDSQLServer.COMANDO.setString (3, pessoa.getEmail());
		    BDSQLServer.COMANDO.setString (4, pessoa.getCpf());
		    BDSQLServer.COMANDO.setString (5, pessoa.getEndereco());
		    BDSQLServer.COMANDO.setString (6, pessoa.getSenha());
		    BDSQLServer.COMANDO.setString (6, pessoa.getTelefone());
		    BDSQLServer.COMANDO.setString (6, pessoa.getCidade());
		    BDSQLServer.COMANDO.setString (6, pessoa.getUf());

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

            sql = "DELETE FROM HPESSOAS " +
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

    public static void alterar(Pessoa pessoa) throws Exception
    {
        if (pessoa==null)
            throw new Exception ("Pessoa nao fornecida");

        if (!cadastrado(pessoa.getCodigo()))
            throw new Exception("Nao cadastrada");

        try
        {
            String sql;

            sql = "UPDATE HPESSOAS " +
                  "SET NOME=? " +
                  "SET EMAIL=? " +
				  "SET CPF=?" +
				  "SET ENDERECO=?" +
				  "SET SENHA=?" +
				  "SET TELEFONE=?" +
				  "SET CIDADE=?" +
				  "SET UF=?" +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, pessoa.getNome());
		    BDSQLServer.COMANDO.setString (2, pessoa.getEmail());
		    BDSQLServer.COMANDO.setString (3, pessoa.getCpf());
		    BDSQLServer.COMANDO.setString (4, pessoa.getEndereco());
		    BDSQLServer.COMANDO.setString (5, pessoa.getSenha());
		    BDSQLServer.COMANDO.setString (6, pessoa.getTelefone());
		    BDSQLServer.COMANDO.setString (7, pessoa.getCidade());
		    BDSQLServer.COMANDO.setString (8, pessoa.getUf());
		    BDSQLServer.COMANDO.setInt    (9, pessoa.getCodigo());

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
                               resultado.getString ("NOME"), 
                               resultado.getString ("EMAIL"),
						       resultado.getString ("CPF"),
						       resultado.getString ("ENDERECO"),
						       resultado.getString ("SENHA"),
						       resultado.getString ("TELEFONE"),
						       resultado.getString ("CIDADE"),
						       resultado.getString ("UF")
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
    
    public static Pessoa getPrimeiroRegistro() throws Exception
    {
    	Pessoa pessoa = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HPESSOAS ";

            BDSQLServer.COMANDO.prepareStatement(sql);
            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); // n é mais para executar update, mas sim uma query (consulta) / ñ é void
            
            if (!resultado.first()) // .last()/ .next()/ .previous()/ .absolute(10) --> retornam boolean
                throw new Exception ();

            pessoa = new Pessoa(resultado.getInt   ("CODIGO"),
                               resultado.getString ("NOME"), // como q ele sb qual ie string eh?
                               resultado.getString ("EMAIL"),
						       resultado.getString ("CPF"),
						       resultado.getString ("ENDERECO"),
						       resultado.getString ("SENHA"),
						       resultado.getString ("TELEFONE"),
						       resultado.getString ("CIDADE"),
						       resultado.getString ("UF")
            				   );
        }
        catch (SQLException erro)
        {
            throw new Exception ("Tabela vazia");
        }

        return pessoa;
    }
}
