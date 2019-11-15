package bd.dbos;

public class Doacao implements Cloneable
{
    private int    cod;
    private String nome;
    private float  preco;

  public void setCod (int cod) throws Exception
    {
        if (cod <= 0)
            throw new Exception ("Codigo invalido");

        this.cod = cod;
    }   
public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }
public void setPreco (float preco) throws Exception
    {
        if (preco <= 0)
            throw new Exception ("Preco invalido");

        this.preco = preco;
    }

 public int getCod ()
    {
        return this.cod;
    }
 public String getNome ()
    {
        return this.nome;
    }
public float getPreco ()
    {
        return this.preco;
    }
public Livro (int cod, String nome, float preco)throws Exception
{
	this.setCodigo (cod);
        this.setNome   (nome);
	this.setPreco  (preco);
}
public String toString ()
    {
        String ret="";

        ret+="Codigo: "+this.codigo+"\n";
        ret+="Nome: "+this.nome  +"\n";
	ret+="Preco: "+this.preco  +"\n";
        return ret;
    }

public boolean Equals(Object obj)
{
  if(obj == null)
    return false;
  if(obj == this)
    return true;
  if(this.getClass() != obj.getClass())
    return false;
  Doacao doa = (Doacao) obj; 
  if(this.cod != doa.cod || this.nome != doa.nome ||
     this.preco != doa.preco)
  return false;
 
  return true;
}

public int hashCode()
{
  int ret = 1;
  ret = ret * 5 + new Interger(this.cod).hashCode();
  ret = ret * 5 + this.nome.hashCode();
  ret = ret * 5 + new Float(this.preco).hashCode();
  return ret;
}

public Doacao(Doacao modelo)throws Exception
{
  if(modelo == null)
    throw new Exception();
  this.cod = modelo.cod;
  this.nome = modelo.nome;
  this.preco = modelo.nome;
}

public Object clone()
{
  Doacao ret = null;
  try
  {
    ret = new Doacao(this);
  }
  catch(Exception erro){}
  return ret;
}

}