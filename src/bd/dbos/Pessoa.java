package bd.dbos;

public class Pessoa implements Cloneable, Comparable<Pessoa>
{
	 private int codigo;
	 private String nome;
	 private String email;
	 private String cpf;
	 private String conta;
	 private String agencia;
	 private String endereco;
	 private String usuario;
	 private String senha;
	 private String telefone;
	 
	 public void setCodigo(int codigo) throws Exception
	 {
	    if (codigo <= 0)
	       throw new Exception("Codigo invalido");
	
	       this.codigo = codigo;
	 }  
	 
	 public void setNome(String nome) throws Exception
	 {
	     if (nome==null || nome.equals(""))
	         throw new Exception ("Nome n�o fornecido");
	
	     this.nome = nome;
	 }
	
	 public void setTelefone(String telefone) throws Exception
	 {
	     if (telefone==null || telefone.equals(""))
	         throw new Exception ("Telefone n�o fornecido");
	
	     this.telefone = telefone;
	 }
	
	 public void setEmail(String email) throws Exception
	 {
	     if (email==null || email.equals(""))
	         throw new Exception ("Email n�o fornecido");
	
	     this.email = email;
	 }
	
	 public void setCpf(String cpf) throws Exception // fzr q nem no sql?
	 {
	     if (cpf==null || cpf.equals(""))
	         throw new Exception ("Cpf n�o fornecido");
	
	     this.cpf = cpf;
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
	
	 public int getCodigo ()
	 {
	     return this.codigo;
	 }
	
	 public String getNome ()
	 {
	     return this.nome;
	 }
	
	 public String getTelefone ()
	 {
	     return this.telefone;
	 }
	
	 public String getEmail ()
	 {
	     return this.email;
	 }
	 
	 public String getCpf ()
	 {
	     return this.cpf;
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
	
	 public Pessoa (int codigo, String nome, String email, String cpf, String conta, String agencia, String endereco, String usuario, String senha, String telefone) throws Exception
	 {
	    this.setCodigo  (codigo);
	    this.setNome    (nome);
	    this.setEmail   (email);
	    this.setCpf     (cpf);
	    this.setConta   (conta);
	    this.setAgencia (agencia);
	    this.setEndereco(endereco);
	    this.setUsuario (usuario);
	    this.setSenha   (senha);
	    this.setTelefone(telefone);
	 }
	
	 public String toString ()
	 {
	     String ret="";
	
	     ret+="Codigo: "+this.codigo+"\n";
	     ret+="Nome: "+this.nome  +"\n";
	     ret+="Email: "+this.email + "\n";
	     ret+="CPF: "+this.cpf + "\n";
	     ret+="Conta: "+this.conta + "\n";
	     ret+="Ag�ncia: "+this.agencia + "\n";
	     ret+="Telefone: "+this.telefone + "\n";
	     ret+="Endere�o: "+this.endereco + "\n";
	     ret+="Usu�rio: "+this.usuario + "\n";
	     ret+="Senha: "+this.senha + "\n";
	
	     return ret;
	 }
	
	public int hashCode()
	{
		int ret = 1;
		ret = ret * 5 + new Integer (this.codigo).hashCode();
		ret = ret * 5 + this.nome.hashCode();
		ret = ret * 5 + this.email.hashCode();
		ret = ret * 5 + this.cpf.hashCode();
		ret = ret * 5 + this.telefone.hashCode();
		ret = ret * 5 + this.conta.hashCode();
		ret = ret * 5 + this.agencia.hashCode();
		ret = ret * 5 + this.endereco.hashCode();
		ret = ret * 5 + this.usuario.hashCode();
		ret = ret * 5 + this.senha.hashCode();
		
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
		
		Pessoa pes = (Pessoa)obj;
		
		if(pes.codigo != this.codigo || pes.nome != this.nome || pes.email != this.email || pes.cpf != this.cpf || pes.telefone != this.telefone || pes.conta != this.conta || pes.agencia != this.agencia || pes.endereco != this.endereco || pes.usuario != this.usuario || pes.senha != this.senha)
			return false;
		
			return true;
	}
		
	public Object clone()
	{
		Pessoa ret = null;
		try
		{
			ret = new Pessoa (this);
		}
		catch(Exception erro)
		{} 
		return ret;
	}
	
	public Pessoa(Pessoa modelo) throws Exception
	{
		if(modelo == null)
			throw new Exception();
		this.codigo = modelo.codigo;
		this.nome = modelo.nome;
		this.email = modelo.email;
		this.cpf = modelo.cpf;
		this.conta = modelo.conta;
		this.agencia = modelo.agencia;
		this.endereco = modelo.endereco;
		this.usuario = modelo.usuario;
		this.senha = modelo.senha;
		this.telefone = modelo.telefone;
	}
	
	public int compareTo(Pessoa outro)
	{
		// n tem throw exception???
		if(this.codigo>outro.codigo)
			return 1;
		if(this.codigo<outro.codigo)
			return -1;
		return 0;
	}
}