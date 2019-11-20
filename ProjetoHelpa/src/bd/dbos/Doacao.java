package bd.dbos;
import java.util.Date;

public class Doacao implements Cloneable, Comparable<Doacao>
{
	private int codigo;
    private int codEntidade;
    private int codPessoa;
    private String produto;
    private Date data = new Date();

  public void setCodigo (int cod) throws Exception
  {
      if (cod <= 0)
          throw new Exception ("Codigo invalido");

      this.codigo = cod;
  } 
  
  public void setCodEntidade (int cod) throws Exception
  {
      if (cod <= 0)
          throw new Exception ("Codigo invalido");

      this.codigo = cod;
  } 
  
  public void setCodPessoa (int cod) throws Exception
  {
      if (cod <= 0)
          throw new Exception ("Codigo invalido");

      this.codigo = cod;
  } 
  
  public void setData (Date dat) throws Exception
  {
      if (dat == null)
          throw new Exception ("Data invalida");

      this.data = dat;
  } 
  
  public void setProduto (String prod) throws Exception
  {
      if (prod == null || prod.equals(""))
          throw new Exception ("Data invalida");

      this.produto = prod;
  } 
  
  public String getProduto ()
  {
         return this.produto;
  }

 public int getCodigo ()
 {
        return this.codigo;
 }
 
 public int getCodEntidade ()
 {
     return this.codEntidade;
 }
 
 public int getCodPessoa ()
 {
     return this.codPessoa;
 }
 
 public Date getData() 
 {
	 return this.data;
 }
	 
	
	public Doacao (int cod, int codEnt, int codPes, Date dat)throws Exception
	{
		this.setCodigo      (cod);
	    this.setCodEntidade (codEnt);
	    this.setCodPessoa   (codPes);
	    this.setData        (dat);
	}
	
	public String toString ()
    {
        String ret="";

        ret+="Codigo: "+this.codigo+"\n";
        ret+="CodPessoa: "+this.codPessoa  +"\n";
	    ret+="CodEntidade: "+this.codEntidade  +"\n";
	    ret+="Data: " + this.data.toString();
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
	   Doacao  doa = (Doacao) obj; 
	  if(this.codigo != doa.codigo || this.codPessoa != doa.codPessoa ||this.codEntidade != doa.codEntidade || !(this.data.equals(doa.data)))
	    return false;
	  
	  return true;
	}
	
	public int hashCode()
	{
	  int ret = 1;
	  ret = ret * 5 + new Integer(this.codigo).hashCode();
	  ret = ret * 5 + new Integer(this.codPessoa).hashCode();
	  ret = ret * 5 + new Integer(this.codEntidade).hashCode();
	  //ret = ret * 5 + new Date   (this.data).hashCode();
	
	  return ret;
	}
	
	public Doacao(Doacao modelo)throws Exception
	{
	  if(modelo == null)
	    throw new Exception();
	  this.codigo = modelo.codigo;
	  this.codPessoa = modelo.codPessoa;
	  this.codEntidade = modelo.codEntidade;
	  this.data = modelo.data;
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
	
	public int compareTo(Doacao outro)
	{
		if(this.codigo>outro.codigo)
			return 1;
		if(this.codigo<outro.codigo)
			return -1;
		return 0;
	}
}
