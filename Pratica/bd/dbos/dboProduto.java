package bd.dbos;

public class Produto implements Cloneable
{
    private int    codigo;
    private String nome;
    private float  preco;
    private String tipo;
  public void setCodigo (int codigo) throws Exception
    {
        if (codigo <= 0)
            throw new Exception ("Codigo invalido");

        this.codigo = codigo;
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
  public void setTipo (String tipo) throws Exception
    {
        if (tipo==null || tipo.equals(""))
            throw new Exception ("Tipo nao fornecido");

        this.tipo = tipo;
    }

    public int getCodigo ()
    {
        return this.codigo;
    }

    public String getNome ()
    {
        return this.nome;
    }

    public float getPreco ()
    {
        return this.preco;
    }
    public String getTipo ()
    {
   	return this.tipo;
    }
 public Produto (int codigo, String nome, float preco, String tipo) throws Exception
    {
        this.setCodigo (codigo);
        this.setNome   (nome);
        this.setPreco  (preco);
	this.setTipo  (tipo);
    }
  public String toString ()
    {
        String ret="";

        ret+="Codigo: "+this.codigo+"\n";
        ret+="Nome: "+this.nome  +"\n";
        ret+="Preco: "+this.preco  +"\n";
	ret+="Tipo: "+this.tipo;

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
  Produto prod = (Produto) obj; 
  if(this.cod != prod.cod || this.nome != prod.nome ||
     this.preco != prod.preco || this.tipo != prod.tipo)
  return false;

  return true;
}

public int hashCode()
{
  int ret = 1;
  ret = ret * 5 + new Interger(this.cod).hashCode();
  ret = ret * 5 + this.nome.hashCode();
  ret = ret * 5 + new Float(this.preco).hashCode();
  ret = ret * 5 + this.tipo.hashCode();
  return ret;
}

public Doacao(Doacao modelo)throws Exception
{
  if(modelo == null)
    throw new Exception();
  this.cod = modelo.cod;
  this.nome = modelo.nome;
  this.preco = modelo.nome;
  this.tipo = modelo.tipo
}

public Object clone()
{
  Doacao ret = null;
  try
  {
    ret = new Produto(this);
  }
  catch(Exception erro){}
  return ret;
}
}