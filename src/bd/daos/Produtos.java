package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Produtos
{
    public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HPRODUTOS" +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar produto");
        }
        									
        return retorno;
    }


    public static void incluir (Produto produto) throws Exception
    {
        if (produto==null)
            throw new Exception ("Produto nao fornecido");

        try //aqui tem um try catch, pq se der merda no meio do try, ele para e da rollback(pq ele quer TUDO ou NADA)
        {
            String sql;

            sql = "INSERT INTO HProdutos " +
                  "(CODIGO,NOME,PRECO,TIPO) " +
                  "VALUES " +
                  "(?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, produto.getCodigo ());  //troca as interrogacoes por valores
            BDSQLServer.COMANDO.setString (2, produto.getNome ());
            BDSQLServer.COMANDO.setFloat  (3, produto.getPreco ());
            BDSQLServer.COMANDO.setString (4, produto.getTipo ());

            BDSQLServer.COMANDO.executeUpdate ();  //TODOS OS COMANDOS MENOS O SELECT � EXECUTEUPDATE
            BDSQLServer.COMANDO.commit        ();  //DPS DE INSERT, DELETE E UPDATE VC USA COMMIT
        }
        catch (SQLException erro)
        {
      //  BDSQLServer.COMANDO.rollback();               		//se for varios comandos e 1 delete s�:
            throw new Exception ("Erro ao inserir produto");
        }
    }

    public static void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM HPRODUTOS " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir produto");
        }
    }

    public static void alterar (Produto produto) throws Exception
    {
        if (produto==null)
            throw new Exception ("Produto nao fornecido");

        if (!cadastrado (produto.getCodigo()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE HPRODUTOS " +
                  "SET NOME=? " +
                  "SET PRECO=? " +
                  "SET TIPO=? " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, produto.getNome ());
            BDSQLServer.COMANDO.setFloat  (2, produto.getPreco ());
            BDSQLServer.COMANDO.setString (3, produto.getTipo ());
            BDSQLServer.COMANDO.setInt    (4, produto.getCodigo ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          throw new Exception ("Erro ao atualizar dados de produto");
        }
    }

    public static Produto getProduto (int codigo) throws Exception
    {
        Produto produto = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HProdutos " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first()) //last(), next(), previous(), absolute(), first() --> vai para linha da tabela RESULTANTE DO SELECT//nesse caso � pq NAO tem a 1 linha, ent se faz esse boolean
                throw new Exception ("Nao cadastrado");

            produto = new Produto (resultado.getInt   ("CODIGO"),
                               resultado.getString("NOME"),
                               resultado.getFloat ("PRECO"),
			       resultado.getString("TIPO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar produto");
        }

        return produto;
    }

    public static MeuResultSet getProdutos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM HProdutos";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar produtos");
        }

        return resultado;
    }
}