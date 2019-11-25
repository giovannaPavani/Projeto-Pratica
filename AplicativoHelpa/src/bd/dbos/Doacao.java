package bd.dbos;
import java.util.Date;
/**
	A classe Doacao � uma classe feita para armazenar dados de
	doa��o (que � uma tabela do banco de dados).
	Nela encontramos setters e getters, al�m do construtor e m�todos obrigat�rios.
	@author Giovanna Pavani Martelli - 19173 e Maria Luiza Sperancin Mancebo - 19186
	@since 2019.
*/
public class Doacao implements Cloneable, Comparable<Doacao>
{
	/**Armazena o id da doacao*/
	private int id;
	/**Armazena o codigo da pessoa que realizou a doa��o*/
    private int codPessoa;
    /**Armazena o produto que foi doado*/
    private String produto;
   /**Armazena o codigo da entidade que recebeu a doa��o*/
    private int codEntidade;
   /**Armazena a data da doa��o*/
    private Date data;
    /**Armazena se ela foi entregue ou n�o*/
    private char entregue;
    /**Armazena a quantidade da doa��o*/
    private String quantidade;


/**
	  Atribui valor ao ID
	  Atribui ao atributo id um n�mero inteiro passado por par�metro.
	  @param o numero que ser� o id
	  @throws Exception caso o id for menor que zero
	 */
  public void setId (int id) throws Exception
  {
      if (id <= 0)
          throw new Exception ("Codigo invalido");

      this.id = id;
  }

/**
	  Atribui valor ao codigo da pessoa
	  Atribui ao atributo codPessoa um n�mero inteiro passado por par�metro.
	  @param o numero que ser� o codigo da pessoa
	  @throws Exception caso o cod for menor que zero
	 */
  public void setCodPessoa (int cod) throws Exception
  {
      if (cod <= 0)
          throw new Exception ("Codigo pessoa invalido");

      this.codPessoa = cod;
  }

/**
	  Atribui valor ao produto
	  Atribui ao atributo protudo uma String passada por par�metro.
	  @param A string q ser� atribuida
	  @throws Exception caso seja null ou vazio
	 */
  public void setProduto (String prod) throws Exception
  {
      if (prod == null || prod.equals(""))
          throw new Exception ("Data invalida");

      this.produto = prod;
  }

/**
	  Atribui valor ao codigo da entidade
	  Atribui ao atributo codEntidade um n�mero inteiro passado por par�metro.
	  @param o numero que ser� o codigo da entidade
	  @throws Exception caso o cod for menor que zero
	 */
  public void setCodEntidade (int cod) throws Exception
  {
      if (cod <= 0)
          throw new Exception ("Codigo entidade invalido");

      this.codEntidade = cod;
  }

/**
	  Atribui valor a data
	  Atribui ao atributo data uma Data passado por par�metro.
	  @param a data que ser� atribuida
	  @throws Exception caso a data for nula
	 */
  public void setData (Date dat) throws Exception
  {
      if (dat == null)
          throw new Exception ("Data invalida");

      this.data = dat;
  }

/**
	  Atribui valor ao codigo da pessoa
	  Atribui ao atributo codPessoa um n�mero inteiro passado por par�metro.
	  @param o numero que ser� o codigo da pessoa
	  @throws Exception caso o cod for menor que zero
	 */
  public void setEntregue (char entreg) throws Exception
  {
      if (entreg!='S' && entreg!='N')
          throw new Exception ("Campo 'Entregue?' inv�lido");

      this.entregue = entreg;
  }

  public void setQuantidade (String qtd) throws Exception
  {
      if (qtd == null || qtd.equals(""))
          throw new Exception ("Data invalida");

      this.quantidade = qtd;
  }

  public String getProduto ()
  {
         return this.produto;
  }

 public int getId ()
 {
        return this.id;
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

 public char getEntregue ()
 {
	 return this.entregue;
 }

 public String getQuantidade ()
 {
        return this.quantidade;
 }


	public Doacao (int id, int codPes, String produto, int codEnt, Date dat, char entreg, String qtd)throws Exception
	{
		this.setId          (id);
	    this.setCodEntidade (codEnt);
	    this.setProduto     (produto);
	    this.setCodPessoa   (codPes);
	    this.setData        (dat);
	    this.setEntregue    (entreg);
	    this.setQuantidade  (qtd);
	}

	public String toString ()
    {
        String ret="";

        ret+="Id: "+this.id+"\n";
        ret+="CodPessoa: "+this.codPessoa  +"\n";
	    ret+="CodEntidade: "+this.codEntidade  +"\n";
	    ret+="Data: " + this.data.toString() + "\n";
	    ret+="Entregue? " + this.entregue+ "\n";
	    ret+="Quantidade: " + this.quantidade;
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
	  if(this.id != doa.id || this.codPessoa != doa.codPessoa ||this.codEntidade != doa.codEntidade || !(this.data.equals(doa.data)) || !(this.quantidade.equals(doa.quantidade)) || this.entregue != doa.entregue)
	    return false;

	  return true;
	}

	public int hashCode()
	{
	  int ret = 1;
	  ret = ret * 5 + new Integer(this.id).hashCode();
	  ret = ret * 5 + new Integer(this.codPessoa).hashCode();
	  ret = ret * 5 + new Integer(this.codEntidade).hashCode();
	  ret = ret * 5 + this.data.hashCode();
	  ret = ret * 5 + this.quantidade.hashCode();
	  ret = ret * 5 + this.produto.hashCode();
	  ret = ret * 5 + new Character (this.entregue).hashCode();

	  return ret;
	}

	public Doacao(Doacao modelo)throws Exception
	{
	  if(modelo == null)
	    throw new Exception();
	  this.id = modelo.id;
	  this.codPessoa = modelo.codPessoa;
	  this.codEntidade = modelo.codEntidade;
	  this.data = modelo.data;
	  this.produto = modelo.produto;
	  this.entregue = modelo.entregue;
	  this.quantidade = modelo.quantidade;
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
		if(this.id>outro.id)
			return 1;
		if(this.id<outro.id)
			return -1;
		return 0;
	}
}
