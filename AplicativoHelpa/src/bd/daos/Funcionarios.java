package bd.daos;
import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

/**A classe Funcionarios é uma DAO e serve para executar ações na
tabela HFuncionarios, como saber se o funcionario esta cadastrado,
excluir um funcionario, etc.
Nela encontramos vários metodos que modificam/verificam informações
na tabela HFuncionarios
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@since 2019.*/

public class Funcionarios {
	/**
		  Verificador de cadastramento
		  Verifica se o codigo passado no parametro já existe na
		  tabela HFuncionarios, se sim, retorna true, se não achar nenhum, retorna false
		  @return se achar true, se não, false
		  @param int codigo que será usado na pesquisa
     	  @throws Exception caso não ache um funcionario com o codigo do parametro
	 */
	public static boolean cadastrado (int CODIGO) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HFuncionarios " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, CODIGO);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar funcionário");
        }

        return retorno;
    }

    /**
			  Verificador de cadastramento por usuario
			  Verifica se o usuario passado no parametro já existe na
			  tabela HFuncionarios, se sim, retorna true, se não achar nenhum, retorna false
			  @return se achar true, se não, false
			  @param usuário a ser procurado
    	      @throws Exception caso não ache um funcionario com o usuario do parametro
	 */
    public static boolean cadastrado (String usuario) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HFuncionarios " +
                  "WHERE USUARIO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString(1, usuario);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

        }
        catch (SQLException erro)
        {erro.printStackTrace();
            throw new Exception ("Erro ao procurar funcionário");
        }

        return retorno;
    }

    /**
		  Inclui funcionarios
		  Verifica se o funcionario passado não é nulo, e em seguida
		  inserta ele na tabela.
		  @param funiconario que será incluido
    	  @throws Exception caso o parametro seja null
	 */
    public static void incluir (Funcionario funcionario) throws Exception
    {
        if (funcionario==null)
            throw new Exception ("Funcionario nao fornecido");

        try //aqui tem um try catch, pq se der merda no meio do try, ele para e da rollback(pq ele quer TUDO ou NADA)
        {
            String sql;

            sql = "INSERT INTO HFUNCIONARIOS " +
                  "(CODIGO,NOME,CPF,EMAIL,SALARIO,TELEFONE,CARGO,CONTA,AGENCIA,ENDERECO,USUARIO,SENHA) " +
                  "VALUES " +
                  "(?,?,?,?,?,?,?,?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, funcionario.getCodigo ());
            BDSQLServer.COMANDO.setString (2, funcionario.getNome ());
            BDSQLServer.COMANDO.setString (3, funcionario.getCpf ());
            BDSQLServer.COMANDO.setString (4, funcionario.getEmail ());
            BDSQLServer.COMANDO.setFloat  (5, funcionario.getSalario ());
            BDSQLServer.COMANDO.setString (6, funcionario.getTelefone ());
            BDSQLServer.COMANDO.setString (7, funcionario.getCargo ());
            BDSQLServer.COMANDO.setString (8, funcionario.getConta ());
            BDSQLServer.COMANDO.setString (9, funcionario.getAgencia ());
            BDSQLServer.COMANDO.setString (10, funcionario.getEndereco ());
            BDSQLServer.COMANDO.setString (11, funcionario.getUsuario ());
            BDSQLServer.COMANDO.setString (12, funcionario.getSenha ());

            BDSQLServer.COMANDO.executeUpdate ();  //TODOS OS COMANDOS MENOS O SELECT É EXECUTEUPDATE
            BDSQLServer.COMANDO.commit        ();  //DPS DE INSERT, DELETE E UPDATE VC USA COMMIT
        }
        catch (SQLException erro)
        {
      //  BDSQLServer.COMANDO.rollback();               		//se for varios comandos e 1 delete só:
            throw new Exception ("Erro ao inserir funcionario");
        }
    }


	/**
		  Exclui funcionarios pelo codigo
		  Verifica se o codigo pertence a algum funcionario, e sem seguida, caso exista,
		  exclui ele da tabela.
		  @param o codigo a ser excluido
    	  @throws Exception caso não ache codigo cadastrado
	 */
    public static void excluir (int CODIGO) throws Exception
    {
        if (!cadastrado(CODIGO))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM HFuncionarios " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, CODIGO);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir funcionario");
        }
    }

	/**
		  Altera funcionarios
		  É passado um Funcionario no parâmetro, que depois de verificar se ele é null ou se não foi
		  cadastrado ainda,e da um update na tabela HFuncionarios nesse funcionario
		  @param funcionario a ser alterado
    	  @throws Exception caso não ache um funcionario, ou seja null
	 */
    public static void alterar (Funcionario funcionario) throws Exception
    {
        if (funcionario==null)
            throw new Exception ("Funcionario nao fornecido");

        if (!cadastrado (funcionario.getCodigo()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE HFuncionarios " +
                  "SET NOME=?, " +
                  "CPF=?, " +
                  "EMAIL=?, " +
                  "SALARIO=?, " +
                  "TELEFONE=?, " +
                  "CARGO=?, " +
                  "CONTA=?, " +
                  "AGENCIA=?, " +
                  "ENDERECO=?, " +
                  "USUARIO=?, " +
                  "SENHA=? " +

                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);
            BDSQLServer.COMANDO.setString (1, funcionario.getNome ());
            BDSQLServer.COMANDO.setString (2, funcionario.getCpf ());
            BDSQLServer.COMANDO.setString (3, funcionario.getEmail ());
            BDSQLServer.COMANDO.setFloat  (4, funcionario.getSalario ());
            BDSQLServer.COMANDO.setString (5, funcionario.getTelefone ());
            BDSQLServer.COMANDO.setString (6, funcionario.getCargo ());
            BDSQLServer.COMANDO.setString (7, funcionario.getConta ());
            BDSQLServer.COMANDO.setString (8, funcionario.getAgencia ());
            BDSQLServer.COMANDO.setString (9, funcionario.getEndereco ());
            BDSQLServer.COMANDO.setString (10, funcionario.getUsuario ());
            BDSQLServer.COMANDO.setString (11, funcionario.getSenha ());
            BDSQLServer.COMANDO.setInt    (12, funcionario.getCodigo ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          throw new Exception ("Erro ao atualizar dados de funcionarios");
        }
    }

	/**
		  Pega um funcionario pelo código
		  Seleciona na tabela tudo sobre o funcionario pelo codigo que foi passado no parâmetro.
		  @return o funcionario encontrado
		  @param codigo a ser procurado
    	  @throws Exception caso não ache um funcionario cadastrado com o código passado
	 */
    public static Funcionario getFuncionarioByCod (int Codigo) throws Exception
    {
        Funcionario funcionario = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HFuncionarios " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, Codigo);

            MeuResultSet resultado = (MeuResultSet)	    BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first()) //last(), next(), previous(), absolute(), first() --> vai para linha da tabela RESULTANTE DO SELECT//nesse caso é pq NAO tem a 1 linha, ent se faz esse boolean
                throw new Exception ("Nao cadastrado");

            funcionario = new Funcionario(resultado.getInt("CODIGO"),
                               resultado.getString("NOME"),
                               resultado.getString("CPF"),
                               resultado.getString("EMAIL"),
                               resultado.getFloat ("SALARIO"),
                               resultado.getString("TELEFONE"),
                               resultado.getString("CARGO"),
                               resultado.getString("CONTA"),
                               resultado.getString("AGENCIA"),
                               resultado.getString("ENDERECO"),
                               resultado.getString("USUARIO"),
                               resultado.getString("SENHA"));
        }
        catch(SQLException erro)
        {
            throw new Exception ("Erro ao procurar funcionário");
        }

        return funcionario;
    }
	/**
		  Pega um funcionario pelo usuario
		  Seleciona na tabela tudo sobre o funcionario pelo usuario que foi passado no parâmetro.
		  @return o funcionario encontrado
		  @param usuario a ser procurado
    	  @throws Exception caso não ache um funcionario
	 */
    public static Funcionario getFuncionarioByUsuario (String usuario) throws Exception
    {
        Funcionario funcionario = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HFuncionarios " +
                  "WHERE USUARIO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, usuario);

            MeuResultSet resultado = (MeuResultSet)	    BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first()) //last(), next(), previous(), absolute(), first() --> vai para linha da tabela RESULTANTE DO SELECT//nesse caso é pq NAO tem a 1 linha, ent se faz esse boolean
                throw new Exception ("Nao cadastrado");

            funcionario = new Funcionario(resultado.getInt("CODIGO"),
		                                  resultado.getString("NOME"),
		                                  resultado.getString("CPF"),
		                                  resultado.getString("EMAIL"),
		                                  resultado.getFloat ("SALARIO"),
		                                  resultado.getString("TELEFONE"),
		                                  resultado.getString("CARGO"),
		                                  resultado.getString("CONTA"),
		                                  resultado.getString("AGENCIA"),
		                                  resultado.getString("ENDERECO"),
		                                  resultado.getString("USUARIO"),
		                                  resultado.getString("SENHA"));
        }
        catch(SQLException erro)
        {
            throw new Exception ("Erro ao procurar funcionário");
        }

        return funcionario;
    }

  /*  public static int getCODIGOigo (String usuario) throws Exception
    {
    	int CODIGO;
        try
        {
            String sql;

            sql = "SELECT CODIGO " +
                  "FROM HFuncionarios " +
                  "WHERE USUARIO = ?";
            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, usuario);

            MeuResultSet resultado = (MeuResultSet)	BDSQLServer.COMANDO.executeQuery ();
            CODIGO = resultado.getInt("CODIGO");
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar funcionario");
        }

        return CODIGO;
    }*/

	/**
		  Pega todos os funcionarios
		  Seleciona todos os funcionarios e armazena em um ResultSet.
		  @return resultset de funcionarios
    	  @throws Exception caso não consiga recuperar os funcionários
	 */
    public static MeuResultSet getFuncionarios () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HFUNCIONARIOS";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar funcionarios");
        }

        return resultado;
    }

	/**
		  Pega o primeiro funcionario
		  Seleciona na tabela tudo sobre o primeiro funcionario.
		  @return o funcionario encontrado
		  @throws Exception caso não consiga recuperar o funcionário
	 */
    public static Funcionario getPrimeiroRegistro() throws Exception
    {
    	Funcionario funcionario = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HFuncionarios";

            BDSQLServer.COMANDO.prepareStatement(sql);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); // n é mais para executar update, mas sim uma query (consulta) / ñ é void
            // uma tabelinha po
            if (!resultado.first()) // .last()/ .next()/ .previous()/ .absolute(10) --> retornam boolean
                throw new Exception ("Nao há nada registrado na tabela");

            funcionario = new Funcionario(resultado.getInt("CODIGO"),
					                      resultado.getString("NOME"),
					                      resultado.getString("CPF"),
					                      resultado.getString("EMAIL"),
					                      resultado.getFloat ("SALARIO"),
					                      resultado.getString("TELEFONE"),
					                      resultado.getString("CARGO"),
					                      resultado.getString("CONTA"),
					                      resultado.getString("AGENCIA"),
					                      resultado.getString("ENDERECO"),
					                      resultado.getString("USUARIO"),
					                      resultado.getString("SENHA"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar registro");
        }

        return funcionario;
    }
}
