package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Funcionarios
{
    public static boolean cadastrado (int cod) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HFuncionarios " +
                  "WHERE COD = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, cod);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar funcion�rio");
        }
        									
        return retorno;
    }
    
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
            System.out.print(usuario);
            BDSQLServer.COMANDO.setString(1, usuario);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

        }
        catch (SQLException erro)
        {erro.printStackTrace();
            throw new Exception ("Erro ao procurar funcion�rio");
        }
        									
        return retorno;
    }

    public static boolean existe(int codigo) 
    {
        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HFUNCIONARIOS " +
                  "WHERE COD = ?";

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

    public static void incluir (Funcionario funcionario) throws Exception
    {
        if (funcionario==null)
            throw new Exception ("Funcionario nao fornecido");

        try //aqui tem um try catch, pq se der merda no meio do try, ele 	      para e da rollback(pq ele quer TUDO ou NADA)
        {
            String sql;

            sql = "INSERT INTO HFUNCIONARIOS " +
                  "(COD,NOME,CPF,EMAIL,SALARIO,TELEFONE,CARGO,CONTA,AGENCIA,ENDERECO,USUARIO,SENHA) " +
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

            BDSQLServer.COMANDO.executeUpdate ();  //TODOS OS COMANDOS MENOS O SELECT � EXECUTEUPDATE
            BDSQLServer.COMANDO.commit        ();  //DPS DE INSERT, DELETE E UPDATE VC USA COMMIT
        }
        catch (SQLException erro)
        {
      //  BDSQLServer.COMANDO.rollback();               		//se for varios comandos e 1 delete s�:
            throw new Exception ("Erro ao inserir funcionario");
        }
    }


    public static void excluir (int cod) throws Exception
    {
        if (!cadastrado(cod))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM HFuncionarios " +
                  "WHERE COD=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, cod);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir funcionario");
        }
    }

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
                  "SET NOME=? " +
                  "SET CPF=? " +
                  "SET EMAIL=? " +
                  "SET SALARIO=? " +
                  "SET TELEFONE=? " +
                  "SET CARGO=? " +
                  "SET CONTA=? " +
                  "SET AGENCIA=? " +
                  "SET ENDERECO=? " +
                  "SET USUARIO=? " +
                  "SET SENHA=? " +

                  "WHERE COD = ?";

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

    public static Funcionario getFuncionario (int codigo) throws Exception
    {
        Funcionario funcionario = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HFuncionarios " +
                  "WHERE COD = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)	    BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first()) //last(), next(), previous(), absolute(), first() --> vai para linha da tabela RESULTANTE DO SELECT//nesse caso � pq NAO tem a 1 linha, ent se faz esse boolean
                throw new Exception ("Nao cadastrado");

            funcionario = new Funcionario(resultado.getInt("COD"),
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
            throw new Exception ("Erro ao procurar funcion�rio");
        }

        return funcionario;
    }
    
    public static Funcionario getFuncionario (String usuario) throws Exception
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
            System.out.print(sql);
            MeuResultSet resultado = (MeuResultSet)	    BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first()) //last(), next(), previous(), absolute(), first() --> vai para linha da tabela RESULTANTE DO SELECT//nesse caso � pq NAO tem a 1 linha, ent se faz esse boolean
                throw new Exception ("Nao cadastrado");

            funcionario = new Funcionario(resultado.getInt("COD"),
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
            throw new Exception ("Erro ao procurar funcion�rio");
        }

        return funcionario;
    }
        
    public static int getCodigo (String usuario) throws Exception
    {
    	int cod;
        try
        {
            String sql;

            sql = "SELECT COD " +
                  "FROM HFuncionarios " +
                  "WHERE USUARIO = ?";
            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, usuario);

            MeuResultSet resultado = (MeuResultSet)	BDSQLServer.COMANDO.executeQuery ();
            cod = resultado.getInt("COD");
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar funcionario");
        }

        return cod;
    }

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
}