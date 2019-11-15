package bd.dbos;

public class Entidade implements Cloneable, Comparable<Entidade>
{
	 private int codigo;
	 private String nome;
	 private String email;
	 private String cnpj;
	 private String telefone;
	 private String conta;
	 private String agencia;
	 private String endereco;
	 private String usuario;
	 private String senha;
	 private int visualizacoes;
	 private String descricao;
	 private String site;
	
	 public void setCodigo(int codigo) throws Exception
	 {
	    if (codigo <= 0)
	       throw new Exception("Codigo invalido");
	
	       this.codigo = codigo;
	 }  
	 public void setVisualizacoes(int visualizacoes) throws Exception
	 {
	    if (codigo < 0)
	       throw new Exception("Codigo invalido");
	
	       this.visualizacoes = visualizacoes;
	 }  
	 
	 public void setNome(String nome) throws Exception
	 {
	     if (nome==null || nome.equals(""))
	         throw new Exception ("Nome n�o fornecido");
	
	     this.nome = nome;
	 }
	
	 public void setEmail(String email) throws Exception
	 {
	     if (email==null || email.equals(""))
	         throw new Exception ("Email n�o fornecido");
	
	     this.email = email;
	 }
	
	 public void setCnpj(String cnpj) throws Exception
	 {
	     if (cnpj==null || cnpj.equals(""))
	         throw new Exception ("CNPJ n�o fornecido");
	
	     this.cnpj = cnpj;
	 }
	
	 public void setConta(String conta) throws Exception
	 {
	     if (conta==null || conta.equals(""))
	         throw new Exception ("Conta n�o fornecida");
	
	     this.conta = conta;
	 }
	
	 public void setAgencia(String agencia) throws Exception
	 {
	     if (agencia==null || agencia.equals(""))
	         throw new Exception ("Ag�ncia n�o fornecida");
	
	     this.agencia = agencia;
	 }
	
	 public void setEndereco(String endereco) throws Exception
	 {
	     if (endereco==null || endereco.equals(""))
	         throw new Exception ("Endere�o n�o fornecido");
	
	     this.endereco = endereco;
	 }
	
	 public void setUsuario(String usuario) throws Exception
	 {
	     if (usuario==null || usuario.equals(""))
	         throw new Exception ("Usu�rio n�o fornecido");
	
	     this.usuario = usuario;
	 }
	
	 public void setSenha(String senha) throws Exception
	 {
	     if (senha==null || senha.equals(""))
	         throw new Exception ("Senha n�o fornecida");
	
	     this.senha = senha;
	 }
	 
	 public void setTelefone(String telefone) throws Exception
	 {
	     if (telefone==null || telefone.equals("")) // formata��o
	         throw new Exception ("Telefone n�o fornecido");
	
	     this.telefone = telefone;
	 }
	 
	 public void setSite(String site) throws Exception
	 {
	     if (site==null || site.equals(""))
	         throw new Exception ("Site n�o fornecido");
	
	     this.site = site;
	 }
	 
	 public void setDescricao(String desc) throws Exception
	 {
	     if (desc==null || desc.equals(""))
	         throw new Exception ("Descri��o n�o fornecida");
	
	     this.descricao = desc;
	 }
	
	 public int getCodigo ()
	 {
	     return this.codigo;
	 }
	
	 public String getNome ()
	 {
	     return this.nome;
	 }
	
	 public int getVisualizacoes ()
	 {
	     return this.visualizacoes;
	 }
	
	 public String getEmail ()
	 {
	     return this.email;
	 }
	 
	 public String getTelefone ()
	 {
	     return this.telefone;
	 }
	
	 public String getCnpj ()
	 {
	     return this.cnpj;
	 }
	
	 public String getConta ()
	 {
	     return this.conta;
	 }
	
	 public String getAgencia ()
	 {
	     return this.agencia;
	 }
	
	 public String getEndereco ()
	 {
	     return this.endereco;
	 }
	
	 public String getUsuario ()
	 {
	     return this.usuario;
	 }
	
	 public String getSenha ()
	 {
	     return this.senha;
	 }
	 
	 public String getSite ()
	 {
	     return this.site;
	 }
	 
	 public String getDescricao ()
	 {
	     return this.descricao;
	 }
	 
	 public Entidade(int codigo) throws Exception
	 {
	    this.setCodigo  (codigo);
	 }
	
	 public Entidade(int codigo, String nome, String email, String cnpj, String conta, String agencia, String endereco, String usuario, String senha, String telefone, int visualizacoes, String descricao, String site) throws Exception
	 {
	    this.setCodigo       (codigo);
	    this.setNome         (nome);
	    this.setEmail        (email);
	    this.setConta        (conta);
	    this.setAgencia      (agencia);
	    this.setEndereco     (endereco);
	    this.setUsuario      (usuario);
	    this.setSenha        (senha);
	    this.setCnpj         (cnpj);
	    this.setVisualizacoes(visualizacoes);
	    this.setSite         (site);
	    this.setDescricao    (descricao);
	 }

	 public String toString ()
	 {
	     String ret="";
	
		 ret+="Codigo: "+this.codigo+"\n";
		 ret+="Nome: "+this.nome  +"\n";
		 ret+="Email: "+this.email + "\n";
		 ret+= "CPNJ: " + this.cnpj +"\n";
		 ret+="Conta: "+this.conta + "\n";
		 ret+="Ag�ncia: "+this.agencia + "\n";
		 ret+="Endere�o: "+this.endereco + "\n";
		 ret+="Usu�rio: "+this.usuario + "\n";
		 ret+="Senha: "+this.senha + "\n";
		 ret+="Telefone: "+this.telefone +"\n";
		 ret+="Visualizacoes" + this.visualizacoes + "\n";
		 ret+="Descri��o: "+this.descricao + "\n";
		 ret+="Site: "+this.site + "\n";
		 
		 return ret;
	 }
	
	public int hashCode()
	{
		int ret = 1;
		ret = ret * 5 + new Integer (this.codigo).hashCode();
		ret = ret * 5 + this.nome.hashCode();
		ret = ret * 5 + this.email.hashCode();
		ret = ret * 5 + this.cnpj.hashCode();
		ret = ret * 5 + this.telefone.hashCode();
		ret = ret * 5 + this.conta.hashCode();
		ret = ret * 5 + this.agencia.hashCode();
		ret = ret * 5 + this.endereco.hashCode();
		ret = ret * 5 + this.usuario.hashCode();
		ret = ret * 5 + this.senha.hashCode();
		ret = ret * 5 + this.site.hashCode();
		ret = ret * 5 + this.descricao.hashCode();
		ret = ret * 5 + new Integer (this.visualizacoes).hashCode();

		if(ret < 0)
			ret = -ret;
		
		return ret;
	}

	public boolean Equals(Object obj)
	{
		if(obj == this)
			return true;
		
		if(obj == null)
			return false;
		
		if(this.getClass() != obj.getClass())
			return false;
		
		Entidade ent = (Entidade)obj;
	
		if(ent.codigo != this.codigo || ent.nome != this.nome || ent.email != this.email || ent.cnpj != this.cnpj || ent.telefone != this.telefone || ent.conta != this.conta || ent.agencia != this.agencia || ent.endereco != this.endereco || ent.usuario != this.usuario || ent.senha != this.senha || ent.visualizacoes != this.visualizacoes || ent.site != this.site || ent.descricao != this.descricao)
			return false;
	
		return true;
	}
	
	public Object clone()
	{
		Entidade ret = null;
		try
		{
			ret = new Entidade (this);
		}
		catch(Exception erro)
		{} 
		return ret;
	}
	
	public Entidade(Entidade molde) throws Exception
	{
		if(molde == null)
			throw new Exception();
		
		this.codigo = molde.codigo;
		this.nome = molde.nome;
		this.email = molde.email;
		this.cnpj = molde.cnpj;
		this.telefone = molde.telefone;
		this.conta = molde.conta;
		this.agencia = molde.agencia;
		this.endereco = molde.endereco;
		this.usuario = molde.usuario;
		this.senha = molde.senha;
		this.visualizacoes = molde.visualizacoes;
		this.site = molde.site;
		this.descricao = molde.descricao;
	}
	
	public int compareTo(Entidade outro)
	{
		// n tem throw exception???
		if(this.codigo>outro.codigo)
			return 1;
		if(this.codigo<outro.codigo)
			return -1;
		return 0;
	}
}