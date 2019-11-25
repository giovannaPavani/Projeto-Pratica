package bd.dbos;
import java.util.Date;
/**
	A classe Doacao é uma classe feita para armazenar dados de
	doação (que é uma tabela do banco de dados).
	Nela encontramos setters e getters, além do construtor e métodos obrigatórios.
	@author Giovanna Pavani Martelli - 19173 e Maria Luiza Sperancin Mancebo - 19186
	@since 2019.
*/
public class Doacao implements Cloneable, Comparable<Doacao>
{
	/**Armazena o id da doacao*/
	private int id;
	/**Armazena o codigo da pessoa que realizou a doação*/
    private int codPessoa;
    /**Armazena o produto que foi doado*/
    private String produto;
   /**Armazena o codigo da entidade que recebeu a doação*/
    private int codEntidade;
   /**Armazena a data da doação*/
    private Date data;
    /**Armazena se ela foi entregue ou não*/
    private char entregue;
    /**Armazena a quantidade da doação*/
    private String quantidade;


/**
	  Atribui valor ao ID
	  Atribui ao atributo id um número inteiro passado por parâmetro.
	  @param o numero que será o id
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
	  Atribui ao atributo codPessoa um número inteiro passado por parâmetro.
	  @param o numero que será o codigo da pessoa
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
	  Atribui ao atributo protudo uma String passada por parâmetro.
	  @param A string q será atribuida
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
	  Atribui ao atributo codEntidade um número inteiro passado por parâmetro.
	  @param o numero que será o codigo da entidade
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
	  Atribui ao atributo data uma Data passado por parâmetro.
	  @param a data que será atribuida
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
	  Atribui ao atributo codPessoa um número inteiro passado por parâmetro.
	  @param o numero que será o codigo da pessoa
	  @throws Exception caso o cod for menor que zero
	 */
  public void setEntregue (char entreg) throws Exception
  {
      if (entreg!='S' && entreg!='N')
          throw new Exception ("Campo 'Entregue?' inválido");

      this.entregue = entreg;
  }

/**
	  Atribui valor a quantidade da doação
	  Atribui ao atributo quantidade uma String passada por parâmetro.
	  @param a quantidade a ser atribuida
	  @throws Exception caso for nula ou vazia
	 */
  public void setQuantidade (String qtd) throws Exception
  {
      if (qtd == null || qtd.equals(""))
          throw new Exception ("Quantidade invalida");

      this.quantidade = qtd;
  }

/**
	   Retorna atributo produto
       Retorna o atributo ptoduto da instância à qual este método for aplicado.
	   @return o produto
	*/
  public String getProduto ()
  {
         return this.produto;
  }

/**
	   Retorna id
       Retorna o atributo id da instância à qual este método for aplicado.
	   @return o id
	*/
 public int getId ()
 {
        return this.id;
 }

/**
	   Retorna código da entidade
       Retorna o atributo codEntidade da instância à qual este método for aplicado.
	   @return o codigo da entidade
	*/
 public int getCodEntidade ()
 {
     return this.codEntidade;
 }

/**
	   Retorna código da pessoa
       Retorna o atributo codPessoa da instância à qual este método for aplicado.
	   @return o codigo da pessoa
	*/
 public int getCodPessoa ()
 {
     return this.codPessoa;
 }

/**
	   Retorna atributo data
       Retorna o atributo data da instância à qual este método for aplicado.
	   @return a data
	*/
 public Date getData()
 {
	 return this.data;
 }

/**
	   Retorna atributo entregue
       Retorna o atributo entregue da instância à qual este método for aplicado.
	   @return o entregue
	*/
 public char getEntregue ()
 {
	 return this.entregue;
 }

/**
	   Retorna atributo quantidade
       Retorna o atributo quantidade da instância à qual este método for aplicado.
	   @return a quantidade
	*/
 public String getQuantidade ()
 {
        return this.quantidade;
 }

/**
	   Constrói uma nova instância da classe Doacao
	   Ele apenas instancia todos os atributos usando os setters
	   @throws Exception se algo for nulo ou vazio
	*/
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

  /**
	    Gera um String com toda a informação presente na classe Doacao.
	    É feito um String que recebe todos os valores presentes na doacao
	    @return um String com todos os dados.
	 */
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

/**
	   Método que retorna se o this é igual ao parametro obj do tipo objeto
	   Verifica se o Object fornecido como parâmetro é a mesma Doacao da instância, resultando true em caso afirmativo,
       ou false, caso não forem iguais.
	   @param obj do tipo Object é  o objeto com o qual this será comparado
	   @return boolean se this é igual a obj
	*/
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

/**
	   Método que retorna o hash code da instância da classe
	   Calcula o hashcode da Doacao representada pela instância à qual o método for aplicado.
	   @return int hash code que engloba o this.qtd e cada valor de equacoes
	*/
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

  /**
	       Constroi uma cópia da instância da classe Doacao.
	       Deve ser passado no parâmetro uma instância de Doacao para ser
		   usada como modelo para criar uma nova.
	       @param modelo instância de Doacao que será usada como molde.
	       @throws Exception caso o molde for nulo.
    */

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

	/**
	   Clona Matriz
	   Produz e retorna uma cópia da instância this de Doacao.
	   @return a cópia do this
	 */
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
